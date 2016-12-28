package nl.boksebeld.domein.plant;

public enum Lichtbehoefte {

	ZON("Zon"), SCHADUW("Schaduw"), HALFSCHADUW("Halfschaduw");

	private String plantsoort;

	private Lichtbehoefte(String value) {
		this.plantsoort = value;
	}

	public String getLichtbehoefte() {
		return plantsoort;
	}

	public String getLichtbehoefteAsString() {
		return this.plantsoort;
	}
}
