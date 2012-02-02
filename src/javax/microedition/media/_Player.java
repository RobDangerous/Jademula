package javax.microedition.media;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.media.control._VolumeControl;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

public class _Player implements Player {
	class Listener implements ControllerEventListener {
		private _Player player;
		
		public Listener(_Player player) {
			this.player = player;
		}
		
		public void controlChange(ShortMessage event) {
			if (event.getData1() == ShortMessage.STOP) player._sendMessage(PlayerListener.END_OF_MEDIA, null);
		}
	}
	
	private Sequencer sequencer;
	private int state = UNREALIZED;
	private List<PlayerListener> listener = new ArrayList<PlayerListener>();
	
	public _Player(InputStream is, String s) {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
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
	
	public void realize() {
		if (state == CLOSED) throw new IllegalStateException();
		if (state != PREFETCHED && state != STARTED) state = REALIZED;
	}
	
	public void prefetch() {
		if (state == CLOSED) throw new IllegalStateException();
		if (state != STARTED) state = PREFETCHED;
	}
	
	public void start() {
		if (state == CLOSED) throw new IllegalStateException();
		if (!sequencer.isRunning()) sequencer.start();
		state = STARTED;
	}
	
	public void stop() {
		if (state == CLOSED) throw new IllegalStateException();
		sequencer.stop();
		state = PREFETCHED;
		_sendMessage(PlayerListener.STOPPED, null);
	}
	
	public void deallocate() {
		if (state == CLOSED) throw new IllegalStateException();
		sequencer.stop();
		if (state != UNREALIZED) state = REALIZED;
	}
	
	public void close() {
		state = CLOSED;
		sequencer.close();
	}
	
	public long setMediaTime(long now) {
		if (state == CLOSED || state == UNREALIZED) throw new IllegalStateException();
		sequencer.setMicrosecondPosition(now);
		return now;
	}
	
	public long getMediaTime() {
		if (state == CLOSED) throw new IllegalStateException();
		return sequencer.getMicrosecondPosition();
	}
	
	public int getState() {
		if (state == STARTED && !sequencer.isRunning()) state = PREFETCHED;
		return state;
	}
	
	public long getDuration() {
		if (state == CLOSED) throw new IllegalStateException();
		return sequencer.getMicrosecondLength();
	}
	
	public String getContentType() {
		if (state == CLOSED || state == UNREALIZED) throw new IllegalStateException();
		System.err.println("javax.microedition.media._Player.getContentType not implemented.");
		return "";
	}
	
	public void setLoopCount(int count) {
		if (state == STARTED && !sequencer.isRunning()) state = PREFETCHED;
		if (state == CLOSED || state == STARTED) throw new IllegalStateException();
		sequencer.setLoopCount(count);
	}
	
	public void addPlayerListener(PlayerListener playerListener) {
		if (state == CLOSED) throw new IllegalStateException();
		listener.add(playerListener);
	}
	
	public void removePlayerListener(PlayerListener playerListener) {
		if (state == CLOSED) throw new IllegalStateException();
		listener.remove(playerListener);
	}
	
	public Control[] getControls() {
		System.out.println("javax.microedition.media._Player.getControls() only returns VolumeControl");
		return new Control[]{new _VolumeControl()};
	}
	
	public Control getControl(String controlType) {
		System.out.println("javax.microedition.media._Player.getControl(String) only returns VolumeControl");
		return new _VolumeControl();
	}
	
	private void _sendMessage(String event, Object eventData) {
		for (PlayerListener l : listener) {
			l.playerUpdate(this, event, eventData);
		}
	}
}