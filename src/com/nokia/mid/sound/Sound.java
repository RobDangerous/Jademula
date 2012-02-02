package com.nokia.mid.sound;

import jademula.Midi;

class BitStream {
	byte[] data;
	int index, bitindex = 7;
	
	public BitStream(byte[] data) {
		this.data = data;
	}
	
	public int next(int num) {
		int ret = 0;
		for (int i = 0; i < num; ++i) {
			int bit = data[index] >> bitindex & 1;
			ret |= bit << 7 - i;
			--bitindex;
			if (bitindex < 0) {
				bitindex = 7;
				++index;
			}
		}
		return ret >> 8 - num;
	}
	
	public void align() {
		if (bitindex != 7) {
			bitindex = 7;
			++index;
		}
	}
}

public class Sound {
	public static final int FORMAT_TONE = 1;
	public static final int FORMAT_WAV = 5;
	public static final int SOUND_PLAYING = 0;
	public static final int SOUND_STOPPED = 1;
	public static final int SOUND_UNINITIALIZED = 3; 
    
	private int state = SOUND_STOPPED;
	private Midi midi;
	
	public Sound(int freq, long duration) {
		init(freq, duration);
	}
	
	public void init(int freq, long duration) {
		System.err.println("com.nokia.mid.sound.Sound.init() not implemented.");
	}
	
	public void setSoundListener(SoundListener listener) {
		System.err.println("com.nokia.mid.sound.Sound.setSoundListener() not implemented.");
	}
	
	public Sound(byte[] data, int type) {
		init(data, type);
	}
	
	public void init(byte[] data, int type) {
		if (type == FORMAT_TONE) {
			try {
				midi = new Midi();
				BitStream bits = new BitStream(data);
				for (;;) {
					int length = bits.next(8);
					if (length == 0) break;
					for (int i = 0; i < length; ++i) {
						parseCommand(bits);
						bits.align();
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				midi.prepare();
			}
		}
		else System.err.println("com.nokia.mid.sound.Sound: type not supported");
	}
	
	private void parseCommand(BitStream bits) {
		int command_part = bits.next(7);
		switch (command_part) {
		case 37: //<ringing-tone-programming>
			return;
		case 29: //<sound>
			parseSound(bits);
			break;
		default:
			throw new RuntimeException("<ringing-tone-programming> or <song> expected");
		}
	}
	
	private void parseSound(BitStream bits) {
		int songtype = bits.next(3);
		switch (songtype) {
		case 1: //<basic-song-type>
			parseBasicSong(bits);
			break;
		case 2: //<temporary-song-type>
			//parseTemporarySong(bits);
			//break;
			throw new RuntimeException("<temporary-song-type> not implemented");
		default:
			throw new RuntimeException("<basic-song-type> or <temporary-song-type> expected");		
		}
	}
	
	private void parseBasicSong(BitStream bits) {
		parseSongTitle(bits);
		parseTemporarySong(bits);
	}
	
	private void parseSongTitle(BitStream bits) {
		int length = bits.next(4);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) sb.append((char)bits.next(8));
		//System.out.println("Songtitle: " + sb);
	}
	
	private void parseTemporarySong(BitStream bits) {
		int length = bits.next(8);
		for (int i = 0; i < length; ++i) parseSongPattern(bits);
	}
	
	private void parseSongPattern(BitStream bits) {
		if (bits.next(3) != 0) throw new RuntimeException("Expected <pattern-header-id>");
		int patternid = bits.next(2);
		switch (patternid) {
		case 0:
		case 1:
		case 2:
		case 3:
			//int loop = 
				bits.next(4); //<loop-value>
			//if (loop == 15) //unlimited
			int specifier = bits.next(8); //<pattern-specifier>
			if (specifier == 0) //<already-defined-pattern>
				System.err.println("already-definded-pattern - what's that?");
			for (int i = 0; i < specifier; ++i) parsePatternInstruction(bits); //<length-of-the-new-pattern>
		}
	}
	
	private void parsePatternInstruction(BitStream bits) {
		int id = bits.next(3);
		switch (id) {
		case 1: //<note-instruction-id>
			parseNote(bits);
			break;
		case 2: //<scale-instruction-id>
			parseScale(bits);
			break;
		case 3: //<style-instruction-id>
			parseStyle(bits);
			break;
		case 4: //<tempo-instruction-id>
			parseTempo(bits);
			break;
		case 5: //<volume-instruction-id>
			parseVolume(bits);
			break;
		default:
			throw new RuntimeException("Expected <note-instruction-id>, <scale-instruction-id>, " +
					"<style-instruction-id>, <tempo-instruction-id> or <volume-instruction-id>");
		}
	}

	private void parseNote(BitStream bits) {
		int note_value = bits.next(4);
		int note;
		switch (note_value) { //<note-value>
		case 0: //pause
			note = 0;
			break;
		case 1: //C
			note = 60;
			break;
		case 2: //Cis �i.e. Des�
			note = 61;
			break;
		case 3: //D
			note = 62;
			break;
		case 4: //Dis �i.e. Es�
			note = 63;
			break;
		case 5: //E
			note = 64;
			break;
		case 6: //F
			note = 65;
			break;
		case 7: //Fis �i.e. Ges�
			note = 66;
			break;
		case 8: //G
			note = 67;
			break;
		case 9: //Gis �i.e. As�
			note = 68;
			break;
		case 10: //A
			note = 69;
			break;
		case 11: //Ais �i.e. B�
			note = 70;
			break;
		case 12: //H
			note = 71;
			break;
		default:
			throw new RuntimeException("Unexpected Note");
		}
		int duration = bits.next(3);//<note-duration>
		switch (duration) {
		case 0: //Full-note
			midi.add(note, 32);
			break;
		case 1: //1/2-note
			midi.add(note, 16);
			break;
		case 2: //1/4-note
			midi.add(note, 8);
			break;
		case 3: //1/8-note
			midi.add(note, 4);
			break;
		case 4: //1/16-note
			midi.add(note, 2);
			break;
		case 5: //1/32-note
			midi.add(note, 1);
			break;
		default: throw new RuntimeException("Unexpected note-duration");
		}
		int specifier = bits.next(2); //<note-duration-specifier>
		switch (specifier) {
		case 0: //No special duration
			break;
		case 1: //Dotted note
		case 2: //Double dotted note
		case 3: //2/3 length
			System.err.println("Special duration");
		}
	}
	
	private void parseScale(BitStream bits) {
		final int displacement = 0;
		int scale = bits.next(2); //<note-scale>
		switch (scale) {
		case 0: //Scale-1 (i.e. Note A is 440 Hz)
			midi.setDisplacement(displacement);
			break;
		case 1: //Scale-2 (i.e. Note A is 880 Hz), default
			midi.setDisplacement(displacement + 12);
			break;
		case 2: //Scale-3 (i.e. Note A is 1.76 kHz)
			midi.setDisplacement(displacement + 24);
			break;
		case 3: //Scale-4 (i.e. Note A is 3.52 kHz)
			midi.setDisplacement(displacement + 36);
			break;
		}
	}
	
	private void parseStyle(BitStream bits) {
		int style = bits.next(2); //<style-value>
		switch (style) {
		case 0: //Natural Style (rest between notes), default
			break;
		case 1: //Continuous Style (no rest between notes)
		case 2: //Staccato Style (shorter notes and longer rest period)
			System.err.println("Special style");
			break;
		default:
			throw new RuntimeException("Unexpected style");
		}
	}
	
	private void parseTempo(BitStream bits) {
		int bpm = bits.next(5); //<beats-per-minute>
		switch (bpm) {
		case 0: //25, i.e., length of � note = 2.40 sec.
			midi.setBPM(25);
			break;
		case 1: //28, i.e., length of � note = 2.14 sec.
			midi.setBPM(28);
			break;
		case 2: //31, i.e., length of � note = 1.90 sec.
			midi.setBPM(31);
			break;
		case 3: //35, i.e., length of � note = 1.70 sec.
			midi.setBPM(35);
			break;
		case 4: //40, i.e., length of � note = 1.51 sec.
			midi.setBPM(40);
			break;
		case 5: //45, i.e., length of � note = 1.35 sec.
			midi.setBPM(45);
			break;
		case 6: //50, i.e., length of � note = 1.20 sec.
			midi.setBPM(50);
			break;
		case 7: //56, i.e., length of � note = 1.07 sec.
			midi.setBPM(56);
			break;
		case 8: //63, i.e., length of � note = 0.95 sec, default
			midi.setBPM(63);
			break;
		case 9: //70, i.e., length of � note = 0.85 sec.
			midi.setBPM(70);
			break;
		case 10: //80, i.e., length of � note = 0.76 sec.
			midi.setBPM(80);
			break;
		case 11: //90, i.e., length of � note = 0.67 sec.
			midi.setBPM(90);
			break;
		case 12: //100, i.e., length of � note = 0.60 sec.
			midi.setBPM(100);
			break;
		case 13: //112, i.e., length of � note = 0.54 sec.
			midi.setBPM(112);
			break;
		case 14: //125, i.e., length of � note = 0.48 sec.
			midi.setBPM(125);
			break;
		case 15: //140, i.e., length of � note = 0.43 sec.
			midi.setBPM(140);
			break;
		case 16: //160, i.e., length of � note = 0.38 sec.
			midi.setBPM(160);
			break;
		case 17: //180, i.e., length of � note = 0.34 sec.
			midi.setBPM(180);
			break;
		case 18: //200, i.e., length of � note = 0.30 sec.
			midi.setBPM(200);
			break;
		case 19: //225, i.e., length of � note = 0.27 sec.
			midi.setBPM(225);
			break;
		case 20: //250, i.e., length of � note = 0.24 sec.
			midi.setBPM(250);
			break;
		case 21: //285, i.e., length of � note = 0.21 sec.
			midi.setBPM(285);
			break;
		case 22: //320, i.e., length of � note = 0.19 sec.
			midi.setBPM(320);
			break;
		case 23: //355, i.e., length of � note = 0.17 sec.
			midi.setBPM(355);
			break;
		case 24: //400, i.e., length of � note = 0.15 sec.
			midi.setBPM(400);
			break;
		case 25: //450, i.e., length of � note = 0.13 sec.
			midi.setBPM(450);
			break;
		case 26: //500, i.e., length of � note = 0.12 sec.
			midi.setBPM(500);
			break;
		case 27: //565, i.e., length of � note = 0.10 sec.
			midi.setBPM(565);
			break;
		case 28: //635, i.e., length of � note = 0.09 sec.
			midi.setBPM(635);
			break;
		case 29: //715, i.e., length of � note = 0.08 sec.
			midi.setBPM(715);
			break;
		case 30: //800, i.e., length of � note = 0.07 sec.
			midi.setBPM(800);
			break;
		case 31: //900, i.e., length of � note = 0.06 sec.
			midi.setBPM(900);
			break;
		}
	}
	
	private void parseVolume(BitStream bits) {
		//int volume = 
			bits.next(4); //<volume>
	}
	
	public void setGain(int gain) {
		
	}
	
	public void play(int loop) {
		state = SOUND_PLAYING;
		if (midi != null) midi.play();
	}
	
	public void stop() {
		state = SOUND_STOPPED;
		if (midi != null) midi.stop();
	}
	
	public int getState() {
		return state;
	}
	
	public void release() {
		
	}
}