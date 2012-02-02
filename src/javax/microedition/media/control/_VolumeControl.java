package javax.microedition.media.control;

public class _VolumeControl implements VolumeControl {
	boolean muted;
	int level;
	
	public void setMute(boolean mute) {
		muted = mute;
		System.out.println("javax.microedition.media.control._VolumeControl.setMute(boolean) not implemented");
	}
	
	public boolean isMuted() {
		return muted;
	}

	public int setLevel(int level) {
		this.level = level;
		System.out.println("javax.microedition.media.control._VolumeControl.setLevel(int) not implemented");
		return level;
	}
	
	public int getLevel() {
		return level;
	}
}