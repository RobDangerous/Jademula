package javax.microedition.rms;

public class InvalidRecordIDException extends RecordStoreException {
	private static final long serialVersionUID = 1L;

	public InvalidRecordIDException() { }
	
	public InvalidRecordIDException(String message) {
		super(message);
	}
}