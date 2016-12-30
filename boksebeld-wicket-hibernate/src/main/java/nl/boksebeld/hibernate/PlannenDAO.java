package nl.boksebeld.hibernate;

import java.util.List;

import nl.boksebeld.domein.plaats.BeplantingsPlan;

public interface PlannenDAO {
	BeplantingsPlan createBeplantingsPlan(String naam);

	BeplantingsPlan saveBeplantingsPlan(BeplantingsPlan beplantingsPlan);

	BeplantingsPlan getBeplantingsPlan(int id);

	List<BeplantingsPlan> getBeplantingsPlanen();

	BeplantingsPlan updateBeplantingsPlan(BeplantingsPlan beplantingsPlan);

	void deleteBeplantingsPlan(BeplantingsPlan beplantingsPlan);
}
