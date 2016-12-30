package nl.boksebeld.domein.service;

import java.util.List;

import nl.boksebeld.domein.klant.Klant;

public interface KlantService {
	Klant createKlant(String voornaam, String achternaam);

	Klant saveKlant(Klant klant);

	Klant getKlant(int id);

	List<Klant> getKlanten(boolean sorteerAsc);

	Klant updateKlant(Klant klant);

	void deleteKlant(Klant klant);
}
