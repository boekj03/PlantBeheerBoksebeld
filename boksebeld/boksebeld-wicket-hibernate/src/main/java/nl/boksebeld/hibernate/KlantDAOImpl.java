package nl.boksebeld.hibernate;

import java.util.List;

import org.springframework.stereotype.Component;

import nl.boksebeld.domein.klant.Klant;

@Component
public class KlantDAOImpl implements KlantDAO {

	public Klant createKlant(String voornaam, String achternaam) {
		final Klant klant = new Klant();
		klant.setVoornaam(voornaam);
		klant.setAchternaam(achternaam);
		HibernateUtil.save(klant);
		return null;
	}

	public Klant saveKlant(Klant klant) {
		HibernateUtil.save(klant);
		return klant;
	}

	public Klant getKlant(int id) {
		return (Klant) HibernateUtil.get(Klant.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Klant> getKlanten(boolean sorteerAsc) {
		return (List<Klant>) HibernateUtil.getKlantenList(sorteerAsc);
	}

	public Klant updateKlant(Klant klant) {
		HibernateUtil.update(klant);
		return klant;
	}

	public void deleteKlant(Klant klant) {
		HibernateUtil.delete(klant);

	}

}
