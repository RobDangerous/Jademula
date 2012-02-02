package javax.microedition.io;

public interface DatagramConnection {
	public String getAddress();
	public int getMaximumLength();
	public int getNominalLength();
	public void setTimeout(int time);
	public void send(Datagram datagram);
	public void receive(Datagram datagram);
	public Datagram newDatagram(int size);
	public Datagram newDatagram(byte[] buf, int size);
	public Datagram newDatagram(byte[] buf, int size, String addr);
}