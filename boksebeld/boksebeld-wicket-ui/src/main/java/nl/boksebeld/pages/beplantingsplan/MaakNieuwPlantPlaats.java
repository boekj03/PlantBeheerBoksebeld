package nl.boksebeld.pages.beplantingsplan;

import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class MaakNieuwPlantPlaats extends PlantplaatsBewerker {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4603267100935309893L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
	public MaakNieuwPlantPlaats(BeplantingsPlan plan) {

		this.setBeplantingsPlan(plan);
		PlantPlaats plaats = new PlantPlaats();

		initBewerker(plaats);

	}
}