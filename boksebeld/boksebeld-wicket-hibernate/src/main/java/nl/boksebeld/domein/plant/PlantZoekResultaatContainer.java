package nl.boksebeld.domein.plant;

import java.io.Serializable;
import java.util.List;

public class PlantZoekResultaatContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1391633098607821886L;

	private List<Plant> plantLijstje;

	public List<Plant> getPlantLijstje() {
		return plantLijstje;
	}

	public void setPlantLijstje(List<Plant> plantLijstje) {
		this.plantLijstje = plantLijstje;
	}

}
