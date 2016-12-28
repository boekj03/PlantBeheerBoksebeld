package nl.boksebeld.domein.service;

import java.util.List;

import nl.boksebeld.domein.plaats.BeplantingsPlan;

public interface PlannenService {
	BeplantingsPlan createBeplantingsPlan(String naam);

	BeplantingsPlan saveBeplantingsPlan(BeplantingsPlan beplantingsPlan);

	BeplantingsPlan getBeplantingsPlan(int id);

	List<BeplantingsPlan> getBeplantingsPlannen();

	BeplantingsPlan updateBeplantingsPlan(BeplantingsPlan beplantingsPlan);

	void deleteBeplantingsPlan(BeplantingsPlan beplantingsPlan);
}
