package javax.microedition.rms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _RecordEnumeration implements RecordEnumeration {
	private RecordStore recordStore;
	private int index;
	private RecordFilter filter;
	private RecordComparator comparator;
	private boolean keepUpdated;
	private boolean started = false;
	private List<_Record> records = new ArrayList<_Record>();
	
	private void loadRecords() {
		records.clear();
		for (_Record record : recordStore._getRecords()) {
			if (filter == null || filter.matches(record.getData())) records.add(record);
		}
		if (comparator != null) {
			Collections.sort(records, new _RecordComparator(comparator));
		}
	}
	
	public _RecordEnumeration(RecordStore recordStore, RecordFilter filter, RecordComparator comparator, boolean keepUpdated) {
		this.recordStore = recordStore;
		this.filter = filter;
		this.comparator = comparator;
		this.keepUpdated = keepUpdated;
		loadRecords();
	}
	
	public void destroy() { }

	public boolean hasNextElement() {
		return index < records.size() - 1;
	}

	public boolean hasPreviousElement() {
		if (!started) return records.size() > 0;
		return index > 0;
	}

	public boolean isKeptUpdated() {
		return keepUpdated;
	}

	public void keepUpdated(boolean keepUpdated) {
		this.keepUpdated = keepUpdated;
	}

	public byte[] nextRecord() throws InvalidRecordIDException {
		try {
		if (!started) {
			started = true;
			return records.get(0).getData();
		}
		return records.get(++index).getData();
		}
		catch (IndexOutOfBoundsException ex) {
			throw new InvalidRecordIDException("out of records");
		}
	}

	public int nextRecordId() {
		if (!started) return 0;
		return records.get(index + 1).getId();
	}

	public int numRecords() {
		return records.size();
	}

	public byte[] previousRecord() {
		if (!started) {
			started = true;
			index = records.size() - 1;
			return records.get(index).getData();
		}
		return records.get(--index).getData();
	}

	public int previousRecordId() {
		return records.get(index - 1).getId();
	}

	public void rebuild() {
		loadRecords();
		reset();
	}

	public void reset() {
		index = 0;
	}
}