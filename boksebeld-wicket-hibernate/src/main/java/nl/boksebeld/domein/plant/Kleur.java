package nl.boksebeld.domein.plant;

public enum Kleur {

	WIT("Wit"), BLAUW("Blauw"), PAARS("Paars"), VIOLET("Violet"), 
	ROOD("Rood"), GEEL("geel"), ORANJE("oranje"), ROZE(
			"Roze");

	private String kleur;

	private Kleur(String value) {
		this.kleur = value;
	}

	public String getKleur() {
		return kleur;
	}

	public String getKleurAsString() {
		return this.kleur;
	}

}
