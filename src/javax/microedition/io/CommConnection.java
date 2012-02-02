package javax.microedition.io;

public interface CommConnection extends StreamConnection {
	public int getBaudRate();
	public int setBaudRate(int baudrate);
}