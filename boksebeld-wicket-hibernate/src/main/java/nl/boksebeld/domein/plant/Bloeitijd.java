package nl.boksebeld.domein.plant;

public enum Bloeitijd {

	JANUARI("januari"), FEBRUARI("februari"), MAART("maart"), APRIL("april"), MEI("mei"), JUNI("juni"), JULI(
			"juli"), AUGUSTUS(
					"augustus"), SEPTEMBER("september"), OKTOBER("oktober"), NOVEMBER("november"), DECEMBER("december");

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
