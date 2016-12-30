package nl.boksebeld.domein.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;
import nl.boksebeld.hibernate.PlantDAO;

@Component
public class PlantenServiceImpl implements PlantenService {

	@Autowired
	private PlantDAO plantDAO;

	public Plant savePlant(Plant plant) {

		return plantDAO.savePlant(plant);
	}

	public Plant getPlant(int id) {
		return plantDAO.getPlant(id);
	}

	public List<Plant> getPlanten() {
		return plantDAO.getPlanten();
	}

	public Plant updatePlant(Plant plant) {
		return plantDAO.updatePlant(plant);
	}

	public void deletePlant(Plant plant) {
		plantDAO.deletePlant(plant);

	}

	public List<Plant> getPlantLijst(PlantZoekItem plantZoekItem) {

		return plantDAO.getPlantLijst(plantZoekItem);
	}

}
