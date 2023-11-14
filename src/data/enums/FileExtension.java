package data.enums;

public enum FileExtension {

	TXT,
	XML,
	HTML;
	
	public String toString() {
        return name().toLowerCase();
    }
}