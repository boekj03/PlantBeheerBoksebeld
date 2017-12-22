package nl.boksebeld.domein.plant;

import java.io.Serializable;
import java.util.HashSet;
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
	private String leverancier;
	private String handelsmaat;
	private Double inkoopprijs;

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

	public String getBloeitijdWeergaveOrg() {
		String retVal = "";

		for (Bloeitijd bloeitijd : Bloeitijd.values()) {
			if (bloeitijdLijst.contains(bloeitijd)) {

				retVal = retVal + bloeitijd.getBloeitijd() + " ";
			}
		}

		return retVal;
	}

	public String getBloeitijdWeergave() {
		String retVal = "";

		for (Bloeitijd bloeitijd : Bloeitijd.values()) {
			retVal = retVal + stringWaardeBloeitijd(bloeitijd);
		}

		return retVal;
	}

	private String stringWaardeBloeitijd(Bloeitijd btijd) {
		String retVal = "";

		if (bloeitijdLijst.contains(btijd)) {
			// retVal = btijd.getBloeitijdAsString().substring(0,
			// 1).toUpperCase();
			retVal = "\u2B1B";
		} else {
			retVal = "\u2B1C";
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

	public String getLeverancier() {
		return leverancier;
	}

	public void setLeverancier(String leverancier) {
		this.leverancier = leverancier;
	}

	public String getHandelsmaat() {
		return handelsmaat;
	}

	public void setHandelsmaat(String handelsmaat) {
		this.handelsmaat = handelsmaat;
	}

	public Double getInkoopprijs() {
		return inkoopprijs;
	}

	public void setInkoopprijs(Double inkoopprijs) {
		this.inkoopprijs = inkoopprijs;
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

	public static void main(String[] args) {
		Plant plant = new Plant();
		plant.getBloeitijdLijst().add(Bloeitijd.JANUARI);
		plant.getBloeitijdLijst().add(Bloeitijd.MAART);
		plant.getBloeitijdLijst().add(Bloeitijd.JULI);
		plant.getBloeitijdLijst().add(Bloeitijd.NOVEMBER);
		System.out.println(plant.getBloeitijdWeergave());
	}

	public Plant createCopy() {
		Plant copy = new Plant();
		copy.setBotanischeNaam(this.getBotanischeNaam() + "kopie");
		copy.setAantalPerMeter(aantalPerMeter);
		copy.setBeschrijving(beschrijving);
		copy.setBladhoudend(bladhoudend);

		for (Bloeitijd bloeitijd : bloeitijdLijst) {
			copy.getBloeitijdLijst().add(bloeitijd);
		}

		copy.setCode(code);
		copy.setGrondsoort(grondsoort);
		copy.setHandelsmaat(handelsmaat);
		copy.setHoogte(hoogte);
		copy.setImage(image);
		copy.setInkoopprijs(inkoopprijs);
		copy.setKleur(kleur);
		copy.setLeverancier(leverancier);
		copy.setLichtbehoefte(lichtbehoefte);
		copy.setNederlandseNaam(nederlandseNaam);
		copy.setPlantsoort(plantsoort);

		return copy;
	}

}
