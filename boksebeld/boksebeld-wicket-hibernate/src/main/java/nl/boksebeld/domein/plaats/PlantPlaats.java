package nl.boksebeld.domein.plaats;

import java.io.Serializable;

import nl.boksebeld.domein.plant.Plant;

public class PlantPlaats implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -4091661879105227883L;
	private int id;
	private String naam;
	private double vierkanteMeters;
	private Plant plant;
	// een PlantPlaats hoort bij een Beplantingsplan.
	private BeplantingsPlan beplantingsPlan;

	public double getVierkanteMeters() {
		return vierkanteMeters;
	}

	public void setVierkanteMeters(double vierkanteMeters) {
		this.vierkanteMeters = vierkanteMeters;
	}

	public Plant getPlant() {

		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public BeplantingsPlan getBeplantingsPlan() {
		return beplantingsPlan;
	}

	public void setBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		this.beplantingsPlan = beplantingsPlan;
	}
}
