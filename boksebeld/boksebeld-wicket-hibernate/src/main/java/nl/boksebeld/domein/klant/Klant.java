package nl.boksebeld.domein.klant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.boksebeld.domein.plaats.BeplantingsPlan;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class Klant implements Serializable {

	private static final long serialVersionUID = 8557686790022611970L;

	private int id;
	private String voornaam;
	private String achternaam;
	private String straat;
	private String huisnummer;
	private String postcode;
	private String plaats;

	private String klantennummer;
	private String telefoonnummer;
	private String mailadres;

	private List<BeplantingsPlan> beplantingsPlanLijst = new ArrayList<BeplantingsPlan>();

	public int getId() {
		return id;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getMailadres() {
		return mailadres;
	}

	public void setMailadres(String mailadres) {
		this.mailadres = mailadres;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getKlantennummer() {
		return klantennummer;
	}

	public void setKlantennummer(String klantennummer) {
		this.klantennummer = klantennummer;
	}

	public List<BeplantingsPlan> getBeplantingsPlanLijst() {
		return beplantingsPlanLijst;
	}

	public void addBeplantingsPlanLijst(BeplantingsPlan beplantingsPlan) {
		this.beplantingsPlanLijst.add(beplantingsPlan);
	}

}
