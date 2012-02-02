package javax.microedition.pki;

public interface Certificate {
	public String getSubject();
	public String getIssuer();
	public String getType();
	public String getVersion();
	public String getSigAlgName();
	public long getNotBefore();
	public long getNotAfter();
	public String getSerialNumber();
}