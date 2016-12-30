package nl.boksebeld.domein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.hibernate.PlannenDAO;

@Component
public class PlannenServiceImpl implements PlannenService {

	@Autowired
	private PlannenDAO beplantingsPlanDAO;

	public BeplantingsPlan createBeplantingsPlan(String naam) {
		return beplantingsPlanDAO.createBeplantingsPlan(naam);
	}

	public BeplantingsPlan saveBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		return beplantingsPlanDAO.saveBeplantingsPlan(beplantingsPlan);
	}

	public BeplantingsPlan getBeplantingsPlan(int id) {
		return beplantingsPlanDAO.getBeplantingsPlan(id);
	}

	public List<BeplantingsPlan> getBeplantingsPlannen() {
		return beplantingsPlanDAO.getBeplantingsPlanen();
	}

	public BeplantingsPlan updateBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		return beplantingsPlanDAO.updateBeplantingsPlan(beplantingsPlan);
	}

	public void deleteBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		beplantingsPlanDAO.deleteBeplantingsPlan(beplantingsPlan);

	}

}
