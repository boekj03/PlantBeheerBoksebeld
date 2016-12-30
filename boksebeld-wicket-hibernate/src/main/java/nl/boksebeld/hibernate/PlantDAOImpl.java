package nl.boksebeld.hibernate;

import java.util.List;

import org.springframework.stereotype.Component;

import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;

@Component
public class PlantDAOImpl implements PlantDAO {

	public Plant savePlant(Plant plant) {
		HibernateUtil.save(plant);
		return plant;
	}

	public Plant getPlant(int id) {
		return (Plant) HibernateUtil.get(Plant.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Plant> getPlanten() {
		return (List<Plant>) HibernateUtil.getPlantenList(true);
	}

	public Plant updatePlant(Plant plant) {
		HibernateUtil.update(plant);
		return plant;
	}

	public void deletePlant(Plant plant) {
		HibernateUtil.delete(plant);

	}

	public List<Plant> getPlantLijst(PlantZoekItem plantZoekItem) {
		return HibernateUtil.getPlantLijst(plantZoekItem);
	}

}
