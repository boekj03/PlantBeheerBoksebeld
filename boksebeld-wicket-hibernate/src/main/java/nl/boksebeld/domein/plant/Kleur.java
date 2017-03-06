package nl.boksebeld.domein.plant;

public enum Kleur {

	WIT("wit"), BLAUW("blauw"), PAARS("paars"), VIOLET("violet"), ROOD("rood"), GEEL("geel"), ORANJE("oranje"), ROZE(
			"roze");

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
