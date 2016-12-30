package nl.boksebeld.hibernate;

public class BeplantingsPlanTest {
	// @Test
	// public void gevuldPlanTest() {
	//
	// List<BeplantingsPlan> lijst =
	// HibernateUtil.getList(BeplantingsPlan.class);
	//
	// BeplantingsPlan plan = createPlan();
	//
	// try {
	// HibernateUtil.save(plan);
	// List<BeplantingsPlan> lijst2 =
	// HibernateUtil.getList(BeplantingsPlan.class);
	// Assert.assertEquals(1, lijst2.size() - lijst.size());
	//
	// Set<PlantPlaats> plantPlaatsLijst = lijst2.get(lijst2.size() -
	// 1).getPlantPlaatsLijst();
	//
	// Assert.assertEquals(1, plantPlaatsLijst.size());
	// PlantPlaats plantPlaats = new
	// ArrayList<PlantPlaats>(plantPlaatsLijst).get(0);
	// Assert.assertEquals("Plantplaats", plantPlaats.getNaam());
	// Assert.assertEquals(Double.valueOf(13d),
	// Double.valueOf(plantPlaats.getVierkanteMeters()));
	//
	// Plant plant = plantPlaats.getPlant();
	// Assert.assertEquals("testplant", plant.getNederlandseNaam());
	//
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// Assert.fail("stumpert");
	//
	// } finally {
	// HibernateUtil.delete(plan);
	// }
	// }
	//
	// @Test
	// public void leegPlanTest() {
	//
	// List<BeplantingsPlan> lijst =
	// HibernateUtil.getList(BeplantingsPlan.class);
	//
	// BeplantingsPlan plan = new BeplantingsPlan();
	//
	// plan.setNaam("test");
	//
	// try {
	// HibernateUtil.save(plan);
	// List<BeplantingsPlan> lijst2 =
	// HibernateUtil.getList(BeplantingsPlan.class);
	// Assert.assertEquals(1, lijst2.size() - lijst.size());
	//
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// Assert.fail("stumpert");
	//
	// } finally {
	// HibernateUtil.delete(plan);
	// }
	// }
	//
	// @Test
	// public void planMetLegePlanPlaatsTest() {
	//
	// List<BeplantingsPlan> lijst =
	// HibernateUtil.getList(BeplantingsPlan.class);
	//
	// BeplantingsPlan plan = new BeplantingsPlan();
	//
	// plan.setNaam("test");
	// plan.addPlantPlaats(new PlantPlaats());
	// try {
	// HibernateUtil.save(plan);
	// List<BeplantingsPlan> lijst2 =
	// HibernateUtil.getList(BeplantingsPlan.class);
	// Assert.assertEquals(1, lijst2.size() - lijst.size());
	// Set<PlantPlaats> plantPlaatsLijst = lijst2.get(lijst2.size() -
	// 1).getPlantPlaatsLijst();
	// PlantPlaats plantPlaats = new
	// ArrayList<PlantPlaats>(plantPlaatsLijst).get(0);
	// Assert.assertNotNull(plantPlaats);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// Assert.fail("stumpert");
	//
	// } finally {
	// HibernateUtil.delete(plan);
	// }
	// }
	//
	// private BeplantingsPlan createPlan() {
	// BeplantingsPlan plan = new BeplantingsPlan();
	//
	// plan.setNaam("test");
	// plan.addPlantPlaats(creatPlantPlaats());
	// return plan;
	// }
	//
	// private PlantPlaats creatPlantPlaats() {
	// PlantPlaats plantPlaats = new PlantPlaats();
	// plantPlaats.setNaam("Plantplaats");
	// plantPlaats.setVierkanteMeters(13d);
	//
	// Plant plant = new Plant();
	// plant.setNederlandseNaam("testplant");
	// HibernateUtil.save(plant);
	// plantPlaats.setPlant(plant);
	// return plantPlaats;
	// }
}
