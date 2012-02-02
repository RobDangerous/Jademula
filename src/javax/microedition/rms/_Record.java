package javax.microedition.rms;

import java.io.Serializable;

public class _Record implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte[] data;
	private boolean deleted = false;
	
	public _Record(int id, byte[] data, int offset, int numBytes) {
		this.id = id;
		this.data = new byte[numBytes];
		System.arraycopy(data, offset, this.data, 0, numBytes);
	}
	
	public void delete() {
		deleted = true;
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public int getId() {
		return id;
	}
}