package nl.boksebeld.domein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.boksebeld.domein.klant.Klant;
import nl.boksebeld.hibernate.KlantDAO;

@Component
public class KlantServiceImpl implements KlantService {

	@Autowired
	private KlantDAO klantDAO;

	public Klant createKlant(String voornaam, String achternaam) {
		return klantDAO.createKlant(voornaam, achternaam);
	}

	public Klant saveKlant(Klant klant) {
		return klantDAO.saveKlant(klant);
	}

	public Klant getKlant(int id) {
		return klantDAO.getKlant(id);
	}

	public List<Klant> getKlanten(boolean sorteerAsc) {
		return klantDAO.getKlanten(sorteerAsc);
	}

	public Klant updateKlant(Klant klant) {
		return klantDAO.updateKlant(klant);
	}

	public void deleteKlant(Klant klant) {
		klantDAO.deleteKlant(klant);

	}

}
