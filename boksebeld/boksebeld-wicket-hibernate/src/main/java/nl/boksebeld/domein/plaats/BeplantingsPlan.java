package nl.boksebeld.domein.plaats;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BeplantingsPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4741432967753355532L;

	private int id;
	private Set<PlantPlaats> plantPlaatsLijst;

	private String naam;

	public BeplantingsPlan() {
		plantPlaatsLijst = new HashSet<PlantPlaats>();
	}

	public Set<PlantPlaats> getPlantPlaatsLijst() {
		return plantPlaatsLijst;
	}

	public void setPlantPlaatsLijst(Set<PlantPlaats> plantPlaatsLijst) {
		this.plantPlaatsLijst = plantPlaatsLijst;
	}

	public void addPlantPlaats(PlantPlaats plantPlaats) {
		this.plantPlaatsLijst.add(plantPlaats);
		plantPlaats.setBeplantingsPlan(this);
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
