package nl.boksebeld.hibernate;

import java.util.List;

import org.springframework.stereotype.Component;

import nl.boksebeld.domein.plaats.BeplantingsPlan;

@Component
public class PlannenDAOImpl implements PlannenDAO {

	public BeplantingsPlan createBeplantingsPlan(String naam) {
		final BeplantingsPlan beplantingsPlan = new BeplantingsPlan();
		beplantingsPlan.setNaam(naam);

		HibernateUtil.save(beplantingsPlan);
		return null;
	}

	public BeplantingsPlan saveBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		HibernateUtil.save(beplantingsPlan);
		return beplantingsPlan;
	}

	public BeplantingsPlan getBeplantingsPlan(int id) {
		return (BeplantingsPlan) HibernateUtil.get(BeplantingsPlan.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BeplantingsPlan> getBeplantingsPlanen() {
		return (List<BeplantingsPlan>) HibernateUtil.getPlannenList(true);
	}

	public BeplantingsPlan updateBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		HibernateUtil.update(beplantingsPlan);
		return beplantingsPlan;
	}

	public void deleteBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		HibernateUtil.delete(beplantingsPlan);

	}
}