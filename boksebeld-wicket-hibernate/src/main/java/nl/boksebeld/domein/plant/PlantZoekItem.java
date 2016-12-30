package nl.boksebeld.domein.plant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class PlantZoekItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6491467751858471293L;
	private String botanischeNaam;
	private String code;

	private String nederlandseNaam;
	private List<Hoogte> hoogteLijst = new ArrayList<Hoogte>();
	private Boolean bladhoudend;

	private List<Bloeitijd> bloeitijdLijst = new ArrayList<Bloeitijd>();
	private List<Grondsoort> grondsoortLijst = new ArrayList<Grondsoort>();
	private List<Kleur> kleurLijst = new ArrayList<Kleur>();
	private List<Plantsoort> plantsoortLijst = new ArrayList<Plantsoort>();
	private List<Lichtbehoefte> lichtbehoefteLijst = new ArrayList<Lichtbehoefte>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBotanischeNaam() {
		return botanischeNaam;
	}

	public void setBotanischeNaam(String botanischeNaam) {
		this.botanischeNaam = botanischeNaam;
	}

	public String getNederlandseNaam() {
		return nederlandseNaam;
	}

	public void setNederlandseNaam(String nederlandseNaam) {
		this.nederlandseNaam = nederlandseNaam;
	}

	public List<Hoogte> getHoogteLijst() {
		return hoogteLijst;
	}

	public void addHoogte(Hoogte hoogteItem) {
		this.hoogteLijst.add(hoogteItem);
	}

	public Boolean getBladhoudend() {
		return bladhoudend;
	}

	public Boolean isBladhoudend() {
		return bladhoudend;
	}

	public void setBladhoudend(Boolean bladhoudend) {
		this.bladhoudend = bladhoudend;
	}

	public List<Bloeitijd> getBloeitijdLijst() {
		return bloeitijdLijst;
	}

	public void addBloeitijd(Bloeitijd bloeitijd) {
		this.bloeitijdLijst.add(bloeitijd);
	}

	public List<Grondsoort> getGrondsoortLijst() {
		return grondsoortLijst;
	}

	public void setGrondsoort(Grondsoort grondsoort) {
		this.grondsoortLijst.add(grondsoort);
	}

	public List<Kleur> getKleurLijst() {
		return kleurLijst;
	}

	public void addKleur(Kleur kleur) {
		this.kleurLijst.add(kleur);
	}

	public List<Plantsoort> getPlantsoortLijst() {
		return plantsoortLijst;
	}

	public void addPlantsoort(Plantsoort plantsoort) {
		this.plantsoortLijst.add(plantsoort);
	}

	public List<Lichtbehoefte> getLichtbehoefteLijst() {
		return lichtbehoefteLijst;
	}

	public void addLichtbehoefte(Lichtbehoefte lichtbehoefte) {
		this.lichtbehoefteLijst.add(lichtbehoefte);
	}

}
