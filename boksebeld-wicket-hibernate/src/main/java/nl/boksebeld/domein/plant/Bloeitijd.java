package nl.boksebeld.domein.plant;

public enum Bloeitijd {

	MEI_APRIL("Mei_April"), NAJAAR("Najaar"), ZOMER("Zomer");
	private String bloeitijd;

	private Bloeitijd(String value) {
		this.bloeitijd = value;
	}

	public String getBloeitijd() {
		return bloeitijd;
	}

	public String getBloeitijdAsString() {
		return this.bloeitijd;
	}
}
