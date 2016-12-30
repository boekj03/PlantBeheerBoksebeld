package nl.boksebeld.domein.plant;

public enum Plantsoort {
	HEESTER("Heester"), VASTEPLANT("Vaste plant"), EENJARIG("Eenjarig"), BOOM("Boom"), VIJVERPLANT(
			"Vijverplant"), BOLKNOL("Bol en Knol/gewas");

	private String plantsoort;

	private Plantsoort(String value) {
		this.plantsoort = value;
	}

	public String getPlantsoort() {
		return plantsoort;
	}

	public String getPlantsoortAsString() {
		return this.plantsoort;
	}
}
