package nl.boksebeld.domein.service;

import java.util.List;

import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;

public interface PlantenService {
	Plant savePlant(Plant Plant);

	Plant getPlant(int id);

	List<Plant> getPlanten();

	Plant updatePlant(Plant Plant);

	void deletePlant(Plant Plant);

	List<Plant> getPlantLijst(PlantZoekItem plantZoekItem);
}
