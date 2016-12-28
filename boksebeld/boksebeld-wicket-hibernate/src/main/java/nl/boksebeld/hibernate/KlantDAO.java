package nl.boksebeld.hibernate;

import java.util.List;

import nl.boksebeld.domein.klant.Klant;

public interface KlantDAO {

	Klant createKlant(String voornaam, String achternaam);

	Klant saveKlant(Klant klant);

	Klant getKlant(int id);

	List<Klant> getKlanten(boolean sorteerAsc);

	Klant updateKlant(Klant klant);

	void deleteKlant(Klant klant);

}
