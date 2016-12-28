package nl.boksebeld.domein.plant;

public enum Grondsoort {

	DROOG("Droog"), NAT("Nat"), ELKEGRONDSOORT("Elke grondsoort");
	private String plantsoort;

	private Grondsoort(String value) {
		this.plantsoort = value;
	}

	public String getGrondsoort() {
		return plantsoort;
	}

	public String getGrondsoortAsString() {
		return this.plantsoort;
	}
}
