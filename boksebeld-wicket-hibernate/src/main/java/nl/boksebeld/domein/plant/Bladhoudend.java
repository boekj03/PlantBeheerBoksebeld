package nl.boksebeld.domein.plant;

public enum Bladhoudend {

	BLADHOUDEN("bladhoudend"), BLANDVERLIEZEND("bladverliezend");

	private String bladhoudend;

	private Bladhoudend(String value) {
		this.bladhoudend = value;
	}

	public String getBladhoudend() {
		return bladhoudend;
	}

	public String getBladhoudendAsString() {
		return this.bladhoudend;
	}
}
