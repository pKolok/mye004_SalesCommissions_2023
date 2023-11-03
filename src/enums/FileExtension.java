package enums;

public enum FileExtension {

	TXT,
	XML;
	
	public String toString() {
        return name().toLowerCase();
    }
}