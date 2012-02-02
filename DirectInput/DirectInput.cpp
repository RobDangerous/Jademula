#include "stdafx.h"
#include "jademula_DirectInput.h"
#include <jawt.h>
#include <jawt_md.h>
#define DIRECTINPUT_VERSION 0x0800
#include <dinput.h>
#include <iostream>
#include <vector>

HMODULE module;

#ifdef _MANAGED
#pragma managed(push, off)
#endif

BOOL APIENTRY DllMain(HMODULE hModule, DWORD ul_reason_for_call, LPVOID lpReserved) {
	module = hModule;
    return TRUE;
}

#ifdef _MANAGED
#pragma managed(pop)
#endif

LPDIRECTINPUT8 dinput;
LPDIRECTINPUTDEVICE8W keyboard;
std::vector<LPDIRECTINPUTDEVICE8W> joysticks;
std::vector<DIJOYSTATE2> joyStates;

jint* axes;
jboolean* joybuttons;
jboolean* keybuttons;

BOOL CALLBACK EnumJoysticksCallback(const DIDEVICEINSTANCE* pdidInstance, VOID* pContext) {
	LPDIRECTINPUTDEVICE8W joystick;
	HRESULT hr = dinput->CreateDevice(pdidInstance->guidInstance, &joystick, NULL);
	joysticks.push_back(joystick);
    //if FAILED(hr) return DIENUM_CONTINUE;
    return DIENUM_CONTINUE;
}

BOOL CALLBACK EnumAxesCallback(LPCDIDEVICEOBJECTINSTANCE lpddoi, LPVOID pvRef) {
	int* count = reinterpret_cast<int*>(pvRef);
	DIPROPRANGE diprg; 
	diprg.diph.dwSize       = sizeof(DIPROPRANGE);
	diprg.diph.dwHeaderSize = sizeof(DIPROPHEADER);
	diprg.diph.dwHow        = DIPH_BYID;
	diprg.diph.dwObj        = lpddoi->dwType;
	diprg.lMin              = -1000;
	diprg.lMax              = +1000;
	joysticks[*count]->SetProperty(DIPROP_RANGE, &diprg.diph);
	//HRESULT hr = joystick->SetProperty(DIPROP_RANGE, &diprg.diph);
	//if FAILED(hr) return DIENUM_STOP;
	return DIENUM_CONTINUE;
}

JNIEXPORT jboolean JNICALL Java_jademula_DirectInput_init(JNIEnv* env, jobject, jobject canvas) {
	JAWT awt;
	awt.version = JAWT_VERSION_1_3;
	jboolean result = JAWT_GetAWT(env, &awt);
	if (result == JNI_FALSE) return false;
	JAWT_DrawingSurface* ds = awt.GetDrawingSurface(env, canvas);
	if (ds == NULL) return false;
	jint lock = ds->Lock(ds);
	if ((lock & JAWT_LOCK_ERROR) != 0) return false;
	JAWT_DrawingSurfaceInfo* dsi = ds->GetDrawingSurfaceInfo(ds);
	JAWT_Win32DrawingSurfaceInfo* dsi_win = (JAWT_Win32DrawingSurfaceInfo*)dsi->platformInfo;

	HRESULT hr = DirectInput8Create(module, DIRECTINPUT_VERSION, IID_IDirectInput8, reinterpret_cast<void**>(&dinput), NULL);
	if FAILED(hr) return false;

	hr = dinput->CreateDevice(GUID_SysKeyboard, &keyboard, NULL);
	if FAILED(hr) return false;
	hr = keyboard->SetDataFormat(&c_dfDIKeyboard);
	if FAILED(hr) return false;
	hr = keyboard->SetCooperativeLevel(dsi_win->hwnd, DISCL_NONEXCLUSIVE | DISCL_BACKGROUND);
	if FAILED(hr) return false;
	keyboard->Acquire();

	dinput->EnumDevices(DI8DEVCLASS_GAMECTRL, EnumJoysticksCallback, NULL, DIEDFL_ATTACHEDONLY);
	//hr = joystick->SetDataFormat(&c_dfDIJoystick2);
	//if FAILED(hr) return false;
	//hr = joystick->SetCooperativeLevel(dsi_win->hwnd, DISCL_EXCLUSIVE | DISCL_FOREGROUND);
	//if FAILED(hr) return false;

	//DIDEVCAPS caps;
	//caps.dwSize = sizeof(DIDEVCAPS);
	//hr = joystick->GetCapabilities(&caps);
	//if FAILED(hr) return false;

	for (unsigned i = 0; i < joysticks.size(); ++i) {
		joysticks[i]->SetDataFormat(&c_dfDIJoystick2);
		joysticks[i]->SetCooperativeLevel(dsi_win->hwnd, DISCL_EXCLUSIVE | DISCL_BACKGROUND);
		joysticks[i]->EnumObjects(EnumAxesCallback, &i, DIDFT_AXIS);
		joysticks[i]->Acquire();
	}
	joyStates.resize(joysticks.size());

	ds->FreeDrawingSurfaceInfo(dsi);
	ds->Unlock(ds);
	awt.FreeDrawingSurface(ds);

	axes = new jint[2];
	joybuttons = new jboolean[256];
	keybuttons = new jboolean[256];

	return true;
}

JNIEXPORT void JNICALL Java_jademula_DirectInput_close(JNIEnv *, jobject) {
	keyboard->Unacquire();
	keyboard->Release();
	for (unsigned i = 0; i < joysticks.size(); ++i) {
		joysticks[i]->Unacquire();
		joysticks[i]->Release();
	}
	dinput->Release();
}

char buffer[256];

JNIEXPORT void JNICALL Java_jademula_DirectInput_poll(JNIEnv *, jobject) {
	HRESULT hr;
	hr = keyboard->GetDeviceState(sizeof(buffer), (LPVOID)&buffer);
	if FAILED(hr) {
		keyboard->Acquire();
		hr = keyboard->GetDeviceState(sizeof(buffer), (LPVOID)&buffer);
    } 

	for (unsigned i = 0; i < joysticks.size(); ++i) {
		hr = joysticks[i]->Poll();
		if FAILED(hr) {
			joysticks[i]->Acquire();
			joysticks[i]->Poll();
		}
		joysticks[i]->GetDeviceState(sizeof(DIJOYSTATE2), &joyStates[i]);
	}
}

JNIEXPORT jintArray JNICALL Java_jademula_DirectInput_joyAxes(JNIEnv* env, jobject, jint num) {
	//jint* axes = new jint[2];
	axes[0] = joyStates[num].lX;
	axes[1] = joyStates[num].lY;
	jintArray a = env->NewIntArray(2);
	env->SetIntArrayRegion(a, 0, 2, axes);
	return a;
}

JNIEXPORT jbooleanArray JNICALL Java_jademula_DirectInput_joyButtons(JNIEnv* env, jobject, jint num) {
	//jboolean* joybuttons = new jboolean[256];
	for (int i = 0; i < 256; ++i) joybuttons[i] = joyStates[num].rgbButtons[i] != 0;
	jbooleanArray a = env->NewBooleanArray(256);
	env->SetBooleanArrayRegion(a, 0, 256, joybuttons);
	return a;
}

JNIEXPORT jint JNICALL Java_jademula_DirectInput_getJoyNum(JNIEnv *, jobject) {
	return static_cast<jint>(joysticks.size());
}

//#define KEYDOWN(name, key) (name[key] & 0x80) 
//if (KEYDOWN(buffer, DIK_UP)) ; 

JNIEXPORT jbooleanArray JNICALL Java_jademula_DirectInput_getKeys(JNIEnv* env, jobject) {
	//std::cerr << "getKeys()\n";
	//jboolean* keybuttons = new jboolean[256];
	for (int i = 0; i < 256; ++i) keybuttons[i] = (buffer[i] & 0x80) != 0;
	jbooleanArray a = env->NewBooleanArray(256);
	env->SetBooleanArrayRegion(a, 0, 256, keybuttons);
	return a;
}