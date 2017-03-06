package nl.boksebeld.domein.plant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Bladhoudend bladhoudend;

	private Set<Bloeitijd> bloeitijdLijst = new HashSet<Bloeitijd>();

	private Grondsoort grondsoort;
	private Kleur kleur;
	private Plantsoort plantsoort;
	private Lichtbehoefte lichtbehoefte;

	private boolean selected;

	public Plant() {
	}

	public Set<Bloeitijd> getBloeitijdLijst() {
		return bloeitijdLijst;
	}

	public void setBloeitijdLijst(Set<Bloeitijd> bloeitijdLijst) {
		this.bloeitijdLijst = bloeitijdLijst;
	}

	public void addBloeitijd(Bloeitijd bloeitijd) {
		this.bloeitijdLijst.add(bloeitijd);
	}

	public String getBloeitijdWeergave() {
		String retVal = "";
		int i = 0;

		List<Bloeitijd> gesorteerdeLijst = new ArrayList<Bloeitijd>();

		if (bloeitijdLijst.contains(Bloeitijd.JANUARI)) {
			gesorteerdeLijst.add(Bloeitijd.JANUARI);
		}
		if (bloeitijdLijst.contains(Bloeitijd.FEBRUARI)) {
			gesorteerdeLijst.add(Bloeitijd.FEBRUARI);
		}
		if (bloeitijdLijst.contains(Bloeitijd.MAART)) {
			gesorteerdeLijst.add(Bloeitijd.MAART);
		}
		if (bloeitijdLijst.contains(Bloeitijd.APRIL)) {
			gesorteerdeLijst.add(Bloeitijd.APRIL);
		}
		if (bloeitijdLijst.contains(Bloeitijd.MEI)) {
			gesorteerdeLijst.add(Bloeitijd.MEI);
		}
		if (bloeitijdLijst.contains(Bloeitijd.JUNI)) {
			gesorteerdeLijst.add(Bloeitijd.JUNI);
		}
		if (bloeitijdLijst.contains(Bloeitijd.JULI)) {
			gesorteerdeLijst.add(Bloeitijd.JULI);
		}
		if (bloeitijdLijst.contains(Bloeitijd.AUGUSTUS)) {
			gesorteerdeLijst.add(Bloeitijd.AUGUSTUS);
		}
		if (bloeitijdLijst.contains(Bloeitijd.SEPTEMBER)) {
			gesorteerdeLijst.add(Bloeitijd.SEPTEMBER);
		}
		if (bloeitijdLijst.contains(Bloeitijd.OKTOBER)) {
			gesorteerdeLijst.add(Bloeitijd.OKTOBER);
		}
		if (bloeitijdLijst.contains(Bloeitijd.NOVEMBER)) {
			gesorteerdeLijst.add(Bloeitijd.NOVEMBER);
		}
		if (bloeitijdLijst.contains(Bloeitijd.DECEMBER)) {
			gesorteerdeLijst.add(Bloeitijd.DECEMBER);
		}

		for (Bloeitijd bloeitijd : gesorteerdeLijst) {
			i++;
			retVal = retVal + bloeitijd + "; ";
			if (i % 3 == 0) {
				retVal = retVal + "</br>";
				i = 0;
			}
		}
		return retVal;
	}

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

	public Hoogte getHoogte() {
		return hoogte;
	}

	public void setHoogte(Hoogte hoogte) {
		this.hoogte = hoogte;
	}

	public Bladhoudend getBladhoudend() {
		return bladhoudend;
	}

	public void setBladhoudend(Bladhoudend bladhoudend) {
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
