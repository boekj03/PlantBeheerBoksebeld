package nl.boksebeld.domein.plant;

public enum Hoogte {

	VIJF_TIEN("5 tot 10 cm"),
	
	TIEN_DERTIG("10 tot 30 cm"),

	DERTIG_ZESTIG("30 tot 60 cm"),
	
	ZESTIG_HONDERD("60 tot 100 cm"),
	
	HONDERD_TWEEHONDERD("100 tot 200 cm"),
	
	TWEE_ZES("2 m tot 6 m"),
	
	ZES_TWAALF("6 m tot 12 m"),
	
	TWAALF_HOGER("12 m en hoger");
	
	
	private String hoogte;

	private Hoogte(String value) {
		this.hoogte = value;
	}

	public String getHoogte() {
		return hoogte;
	}
	
	public String getHoogteAsString(){
		return this.hoogte;
	}
	
}
