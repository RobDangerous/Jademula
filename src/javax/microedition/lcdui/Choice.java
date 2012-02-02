package javax.microedition.lcdui;

public interface Choice {
	public static final int EXCLUSIVE = 1;
	public static final int MULTIPLE = 2;
	public static final int IMPLICIT = 3;
	public static final int POPUP = 4;
	public static final int TEXT_WRAP_DEFAULT = 0;
	public static final int TEXT_WRAP_ON = 1;
	public static final int TEXT_WRAP_OFF = 2;

	public int size();
	public String getString(int elementNum);
	public Image getImage(int elementNum);
	public int append(String stringPart, Image imagePart);
	public void insert(int elementNum, String stringPart, Image imagePart);
	public void delete(int elementNum);
	public void deleteAll();
	public void set(int elementNum, String stringPart, Image imagePart);
	public boolean isSelected(int elementNum);
	public int getSelectedIndex();
	public int getSelectedFlags(boolean[] selectedArray_return);
	public void setSelectedIndex(int elementNum, boolean selected);
	public void setSelectedFlags(boolean[] selectedArray);
	public void setFitPolicy(int fitPolicy);
	public int getFitPolicy();
	public void setFont(int elementNum, Font font);
	public Font getFont(int elementNum);
}