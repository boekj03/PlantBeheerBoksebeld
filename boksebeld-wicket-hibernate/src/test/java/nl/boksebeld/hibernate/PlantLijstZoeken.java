package nl.boksebeld.hibernate;

public class PlantLijstZoeken {
	//
	// @Test
	//
	// public void zoekPlantOpnaamTest() {
	// try {
	// // HibernateUtil.save(plant);
	//
	// PlantZoekItem pzi = new PlantZoekItem();
	// // pzi.setNederlandseNaam("plant");
	// // pzi.getBloeitijdLijst().add(Bloeitijd.JANUARI);
	// List<Plant> plantLijst = HibernateUtil.getPlantLijst(pzi);
	//
	// List list = HibernateUtil.getList(Plant.class);
	// System.out.println(plantLijst.size());
	// System.out.println(list.size());
	//
	// Assert.assertEquals(1, plantLijst.size());
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// System.out.println(ex.getMessage());
	// } finally {
	// // HibernateUtil.delete(plant);
	// }
	// }
	//
	// @Test
	// @Ignore
	// public void zoekPlantOpHoogteTest() {
	// Plant plant1 = new Plant();
	// plant1.setHoogte(Hoogte.VIJF_TIEN);
	//
	// Plant plant2 = new Plant();
	// plant2.setHoogte(Hoogte.HONDERD_TWEEHONDERD);
	//
	// try {
	// HibernateUtil.save(plant1);
	// HibernateUtil.save(plant2);
	//
	// PlantZoekItem pzi = new PlantZoekItem();
	// pzi.addHoogte(Hoogte.VIJF_TIEN);
	// pzi.addHoogte(Hoogte.HONDERD_TWEEHONDERD);
	// List<Plant> plantLijst = HibernateUtil.getPlantLijst(pzi);
	// List list = HibernateUtil.getList(Plant.class);
	// System.out.println(list.size());
	// Assert.assertEquals(2, plantLijst.size());
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// System.out.println(ex.getMessage());
	// } finally {
	// HibernateUtil.delete(plant1);
	// HibernateUtil.delete(plant2);
	// }
	// }
}
