package nl.boksebeld.pages.planten.zoeken;

import java.io.Serializable;

import nl.boksebeld.domein.plant.Plant;

public class PlantContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2829973576174467514L;
	private Plant plantEen;
	private Plant plantTwee;
	private Plant plantDrie;

	public Plant getPlantEen() {
		return plantEen;
	}

	public void setPlantEen(Plant plantEen) {
		this.plantEen = plantEen;
	}

	public Plant getPlantTwee() {
		return plantTwee;
	}

	public void setPlantTwee(Plant plantTwee) {
		this.plantTwee = plantTwee;
	}

	public Plant getPlantDrie() {
		return plantDrie;
	}

	public void setPlantDrie(Plant plantDrie) {
		this.plantDrie = plantDrie;
	}
}
