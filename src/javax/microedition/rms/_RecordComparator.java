package javax.microedition.rms;

import java.util.Comparator;

public class _RecordComparator implements Comparator<_Record> {
	private RecordComparator comparator;
	
	public _RecordComparator(RecordComparator comparator) {
		this.comparator = comparator;
	}
	
	public int compare(_Record r1, _Record r2) {
		return comparator.compare(r1.getData(), r2.getData());
	}
}