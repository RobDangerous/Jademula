package com.siemens.mp.media;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class _Player implements Player {
	private Sequencer sequencer;
	
	public _Player(String filename) {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			InputStream is = "".getClass().getResourceAsStream(filename);
			Sequence mySeq = MidiSystem.getSequence(is);
			sequencer.setSequence(mySeq);
		}
		catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void realize() throws MediaException { }
	
	public void setLoopCount(int count) {
		sequencer.setLoopCount(count);
	}
	
	public void start() throws MediaException {
		sequencer.start();
	}
	
	public void stop() throws MediaException {
		sequencer.stop();
	}

	public void prefetch() throws MediaException {
		
	}

	public void deallocate() {
		
	}

	public void close() {
		
	}

	public void setTimeBase(TimeBase master) throws MediaException {
		
	}

	public TimeBase getTimeBase() {
		return null;
	}

	public long setMediaTime(long now) throws MediaException {
		return 0;
	}

	public long getMediaTime() {
		return 0;
	}

	public int getState() {
		return 0;
	}

	public long getDuration() {
		return 0;
	}

	public String getContentType() {
		return null;
	}

	public void addPlayerListener(PlayerListener playerListener) {
		
	}

	public void removePlayerListener(PlayerListener playerListener) {
		
	}

	public Control[] getControls() {
		return null;
	}

	public Control getControl(String controlType) {
		return null;
	}
}