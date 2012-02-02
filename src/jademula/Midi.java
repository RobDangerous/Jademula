package jademula;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Midi {
	private Sequencer sequencer;
	private Sequence sequence;
	private Track track;
	private int position, bpm = 63, displacement = 12;
	private static final int VELOCITY = 64;

	public Midi() {
		try {
			sequence = new Sequence(Sequence.PPQ, 8);
		}
		catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		track = sequence.createTrack();
	}
	
	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}
	
	public void add(int note, int duration) {
		track.add(createNoteOnEvent(note + displacement, position));
		track.add(createNoteOffEvent(note + displacement, position += duration));
	}
	
	public void prepare() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(sequence);
			sequencer.open();
			sequencer.setTempoInBPM(bpm);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void play() {
		try {
			sequencer.setTickPosition(0);
			sequencer.start();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void stop() {
		sequencer.stop();
	}
	
	public void setBPM(int bpm) {
		this.bpm = bpm;
	}
	
	private static MidiEvent createNoteOnEvent(int nKey, long lTick) {
		return createNoteEvent(ShortMessage.NOTE_ON, nKey, VELOCITY, lTick);
	}

	private static MidiEvent createNoteOffEvent(int nKey, long lTick) {
		return createNoteEvent(ShortMessage.NOTE_OFF, nKey, 0, lTick);
	}

	private static MidiEvent createNoteEvent(int nCommand, int nKey, int nVelocity, long lTick) {
		ShortMessage message = new ShortMessage();
		try {
			message.setMessage(nCommand, 0,	// always on channel 1
							   nKey, nVelocity);
		}
		catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		MidiEvent event = new MidiEvent(message, lTick);
		return event;
	}
}