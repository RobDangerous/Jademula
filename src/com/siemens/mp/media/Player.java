package com.siemens.mp.media;

public interface Player extends Controllable {
	public static final int CLOSED = 0;
	public static final int PREFETCHED = 300;
	public static final int REALIZED = 200;
	public static final int STARTED = 400;
	public static final long TIME_UNKNOWN = -1l;
	public static final int UNREALIZED = 100;
	
	public void realize() throws MediaException;
	public void prefetch() throws MediaException;
	public void start() throws MediaException;
	public void stop() throws MediaException;
	public void deallocate();
	public void close();
	public void setTimeBase(TimeBase master) throws MediaException;
	public TimeBase getTimeBase();
	public long setMediaTime(long now) throws MediaException;
	public long getMediaTime();
	public int getState();
	public long getDuration();
	public String getContentType();
	public void setLoopCount(int count);
	public void addPlayerListener(PlayerListener playerListener);
	public void removePlayerListener(PlayerListener playerListener);
}