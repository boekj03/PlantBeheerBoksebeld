package nl.boksebeld.domein.plaats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

	public List<PlantPlaats> getSortedPlantPlaatsLijst() {
		List<PlantPlaats> plantPlaatsLijstSorted = new ArrayList<PlantPlaats>(this.getPlantPlaatsLijst());
		Collections.sort(plantPlaatsLijstSorted);
		return plantPlaatsLijstSorted;
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

	/**
	 * Hier maak ik een copy.
	 * 
	 * @return
	 */
	public BeplantingsPlan createCopy() {
		BeplantingsPlan copy = new BeplantingsPlan();
		copy.setNaam("kopie" + this.getNaam());
		for (PlantPlaats plantPlaats : this.getPlantPlaatsLijst()) {
			PlantPlaats plantPlaatCopy = plantPlaats.createCopy();
			copy.addPlantPlaats(plantPlaatCopy);
		}
		return copy;
	}

}
