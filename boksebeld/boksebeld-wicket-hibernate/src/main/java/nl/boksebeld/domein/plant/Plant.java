package nl.boksebeld.domein.plant;

import java.io.Serializable;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class Plant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5214757141923237966L;

	private int id;

	private String nederlandseNaam;
	private double aantalPerMeter;
	private String beschrijving;
	private String botanischeNaam;
	private String code;

	private Hoogte hoogte;
	private boolean bladhoudend;

	private Bloeitijd bloeitijd;
	private Grondsoort grondsoort;
	private Kleur kleur;
	private Plantsoort plantsoort;
	private Lichtbehoefte lichtbehoefte;

	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Plant() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNederlandseNaam() {
		return nederlandseNaam;
	}

	public void setNederlandseNaam(String nederlandseNaam) {
		this.nederlandseNaam = nederlandseNaam;
	}

	public double getAantalPerMeter() {
		return aantalPerMeter;
	}

	public void setAantalPerMeter(double aantalPerMeter) {
		this.aantalPerMeter = aantalPerMeter;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Bloeitijd getBloeitijd() {
		return bloeitijd;
	}

	public void setBloeitijd(Bloeitijd bloeitijd) {
		this.bloeitijd = bloeitijd;
	}

	public Hoogte getHoogte() {
		return hoogte;
	}

	public void setHoogte(Hoogte hoogte) {
		this.hoogte = hoogte;
	}

	public boolean isBladhoudend() {
		return bladhoudend;
	}

	public void setBladhoudend(boolean bladhoudend) {
		this.bladhoudend = bladhoudend;
	}

	public Grondsoort getGrondsoort() {
		return grondsoort;
	}

	public void setGrondsoort(Grondsoort grondsoort) {
		this.grondsoort = grondsoort;
	}

	public Kleur getKleur() {
		return kleur;
	}

	public void setKleur(Kleur kleur) {
		this.kleur = kleur;
	}

	public Plantsoort getPlantsoort() {
		return plantsoort;
	}

	public void setPlantsoort(Plantsoort plantsoort) {
		this.plantsoort = plantsoort;
	}

	public Lichtbehoefte getLichtbehoefte() {
		return lichtbehoefte;
	}

	public void setLichtbehoefte(Lichtbehoefte lichtbehoefte) {
		this.lichtbehoefte = lichtbehoefte;
	}

	public String getBotanischeNaam() {
		return botanischeNaam;
	}

	public void setBotanischeNaam(String botanischeNaam) {
		this.botanischeNaam = botanischeNaam;
	}

}
