package javax.microedition.rms;

import jademula.Jademula;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecordStore {
	public static final int AUTHMODE_PRIVATE = 0;
	public static final int AUTHMODE_ANY = 1;
	
	private static Map<String, RecordStore> stores = new HashMap<String, RecordStore>();
	private List<_Record> records = new ArrayList<_Record>();
	private List<RecordListener> listeners = new ArrayList<RecordListener>();
	private String vendorName, gameName, storeName;
	private int authmode;
	private boolean writable;
	private int referenceCount = 0, version;
	private long lastModified;
	
	private static void _createFile(String filename) throws IOException {
		String[] dirs = filename.split("/");
		String dir = "";
		for (int i = 0; i < dirs.length - 1; ++i) {
			dir += dirs[i] + "/";
			File dirfile = new File(dir);
			if (!dirfile.exists()) dirfile.mkdir();
		}
		File file = new File(filename);
		file.createNewFile();
	}
	
	private static String _getDirname(String vendorName, String gameName) {
		return "save/" + vendorName + "/" + gameName + "/";
	}
	
	private static String _getFilename(String vendorName, String gameName, String storeName) {
		return _getDirname(vendorName, gameName) + storeName;
	}
	
	private static String _getFilename(String recordStoreName) {
		return _getFilename(Jademula.getVendor(), Jademula.getName(), recordStoreName);
	}
	
	private void _save() {
		++version;
		lastModified = System.currentTimeMillis();
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(_getFilename(vendorName, gameName, storeName)));
			out.writeInt(authmode);
			out.writeBoolean(writable);
			out.writeObject(records);
			out.close();
		}
		catch (IOException ex) { }
	}
	
	@SuppressWarnings("unchecked")
	public void _load() throws IOException, ClassNotFoundException {
		if (new File(_getFilename(vendorName, gameName, storeName)).length() == 0) {
			authmode = AUTHMODE_PRIVATE;
			writable = false;
		}
		else {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(_getFilename(vendorName, gameName, storeName)));
			authmode = in.readInt();
			writable = in.readBoolean();
			records = (List<_Record>) in.readObject();
			in.close();
		}
	}
	
	private boolean _isClosed() {
		return referenceCount == 0;
	}
	
	private void _assertOpen() throws RecordStoreNotOpenException {
		if (_isClosed()) throw new RecordStoreNotOpenException();
	}
	
	private _Record _getRecord(int recordId) throws InvalidRecordIDException {
		if (recordId > records.size()) throw new InvalidRecordIDException();
		_Record record = records.get(recordId - 1);
		if (record.isDeleted()) throw new InvalidRecordIDException();
		return record;
	}
	
	private void _open() throws IOException, ClassNotFoundException {
		++referenceCount;
		if (referenceCount == 1) _load();
	}
	
	public List<_Record> _getRecords() {
		return records;
	}
	
	static {
		File dir = new File("save");
		if (!dir.exists()) dir.mkdir();
		dir = new File("save/" + Jademula.getVendor());
		if (!dir.exists()) dir.mkdir();
		dir = new File(_getDirname(Jademula.getVendor(), Jademula.getName()));
		if (!dir.exists()) dir.mkdir();
		for (String file : dir.list())
			stores.put(file, new RecordStore(Jademula.getVendor(), Jademula.getName(), file));
	}
	
	public RecordStore(String vendorName, String gameName, String storeName) {
		this.vendorName = vendorName;
		this.gameName = gameName;
		this.storeName = storeName;
	}
	
	public static synchronized void deleteRecordStore(String recordStoreName) throws RecordStoreException, RecordStoreNotFoundException {
		RecordStore store = stores.get(recordStoreName);
		if (store == null) throw new RecordStoreNotFoundException();
		if (!store._isClosed()) throw new RecordStoreException();
		stores.remove(recordStoreName);
		new File(_getFilename(recordStoreName)).delete();
	}
	
	public static synchronized RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary) throws RecordStoreException, RecordStoreFullException, RecordStoreNotFoundException {
		if (recordStoreName.length() < 1 || recordStoreName.length() > 32) throw new IllegalArgumentException();
		RecordStore store = stores.get(recordStoreName);
		try {
			if (store != null) {
				store._open();
				return store;
			}
			if (!createIfNecessary) throw new RecordStoreNotFoundException();
			_createFile(_getFilename(recordStoreName));
			store = new RecordStore(Jademula.getVendor(), Jademula.getName(), recordStoreName);
			stores.put(recordStoreName, store);
			store._open();
		}
		catch (IOException ex) {
			throw new RecordStoreException();
		}
		catch (ClassNotFoundException ex) {
			throw new RecordStoreException();
		}
		return store;
	}

	public synchronized static RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary, int authmode, boolean writable) throws RecordStoreException, RecordStoreFullException, RecordStoreNotFoundException {
		System.err.println("Warning: Multi-Midlet RecordStores are not supported.");
		return openRecordStore(recordStoreName, createIfNecessary);
	}

	public synchronized static RecordStore openRecordStore(String recordStoreName, String vendorName, String suiteName) throws RecordStoreException, RecordStoreNotFoundException {
		System.err.println("Multi-Midlet RecordStores are not supported.");
		return null;
	}
	
	public synchronized void setMode(int authmode, boolean writable) throws RecordStoreException {
		this.authmode = authmode;
		this.writable = writable;
	}
	
	public synchronized void closeRecordStore() throws RecordStoreNotOpenException, RecordStoreException {
		_assertOpen();
		--referenceCount;
	}
	
	public synchronized static String[] listRecordStores() {
		List<String> records = new ArrayList<String>();
		for (String name : stores.keySet()) records.add(name);
		return records.toArray(new String[0]);
	}
	
	public synchronized String getName() throws RecordStoreNotOpenException {
		_assertOpen();
		return storeName;
	}

	public synchronized int getVersion() throws RecordStoreNotOpenException {
		_assertOpen();
		return version;
	}
	
	public synchronized int getNumRecords() throws RecordStoreNotOpenException {
		_assertOpen();
		return records.size();
	}

	public synchronized int getSize() throws RecordStoreNotOpenException {
		_assertOpen();
		int size = 0;
		for (_Record record : records) size += record.getData().length;
		return size;
	}
	
	public synchronized int getSizeAvailable() throws RecordStoreNotOpenException {
		_assertOpen();
		return 1024 * 1024 * 10;
	}

	public synchronized long getLastModified() throws RecordStoreNotOpenException {
		_assertOpen();
		return lastModified;
	}
	
	public synchronized void addRecordListener(RecordListener listener) {
		if (listeners.contains(listener)) return;
		listeners.add(listener);
	}
	
	public synchronized void removeRecordListener(RecordListener listener) {
		listeners.remove(listener);
	}
	
	public synchronized int getNextRecordID() throws RecordStoreNotOpenException, RecordStoreException {
		_assertOpen();
		return records.size() + 1;
	}
	
	public synchronized int addRecord(byte[] data, int offset, int numBytes) throws RecordStoreNotOpenException, RecordStoreException, RecordStoreFullException {
		_assertOpen();
		records.add(new _Record(records.size() - 1, data, offset, numBytes));
		_save();
		System.out.println("addRecord " + storeName + " " + records.size());
		for (RecordListener listener : listeners) listener.recordAdded(this, records.size() - 1);
		return records.size();
	}
	
	public synchronized void deleteRecord(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		_assertOpen();
		records.get(recordId - 1).delete();
		_save();
		for (RecordListener listener : listeners) listener.recordDeleted(this, recordId);
	}

	public synchronized int getRecordSize(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		_assertOpen();
		return _getRecord(recordId).getData().length;
	}

	public synchronized int getRecord(int recordId, byte[] buffer, int offset) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		System.out.println("getRecord " + storeName + " " + recordId);
		_assertOpen();
		_Record record = _getRecord(recordId);
		System.arraycopy(record.getData(), 0, buffer, offset, record.getData().length);
		return record.getData().length;
	}

	public synchronized byte[] getRecord(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		System.out.println("getRecord " + storeName + " " + recordId);
		_assertOpen();
		_Record record = _getRecord(recordId);
		byte[] data = new byte[record.getData().length];
		System.arraycopy(record.getData(), 0, data, 0, record.getData().length);
		return data;
	}

	public synchronized void setRecord(int recordId, byte[] newData, int offset, int numBytes) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException, RecordStoreFullException {
		System.out.println("setRecord " + storeName + " " + recordId);
		_assertOpen();
		_Record record = _getRecord(recordId);
		byte[] data = new byte[numBytes];
		System.arraycopy(newData, offset, data, 0, numBytes);
		record.setData(data);
		_save();
		for (RecordListener listener : listeners) listener.recordChanged(this, recordId);
	}

	public synchronized RecordEnumeration enumerateRecords(RecordFilter filter, RecordComparator comparator, boolean keepUpdated) throws RecordStoreNotOpenException {
		if (keepUpdated) System.err.println("Keeping a RecordEnumeration updated is not supported.");
		return new _RecordEnumeration(this, filter, comparator, keepUpdated);
	}
}