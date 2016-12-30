package nl.boksebeld.pages.beplantingsplan;

import nl.boksebeld.domein.plaats.PlantPlaats;

public class UpdatePlantPlaats extends PlantplaatsBewerker {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4640165213180723692L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
	public UpdatePlantPlaats(PlantPlaats plaats) {

		this.setBeplantingsPlan(plaats.getBeplantingsPlan());

		initBewerker(plaats);

	}
}