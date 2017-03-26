package nl.excel;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import nl.boksebeld.applicatie.excel.BeplantingsPlanToExcel;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.hibernate.HibernateUtil;

public class NaarExcelTest {
	@Test
	@Ignore
	public void naarExcelTest() {
		try {
			// HibernateUtil.save(plant);

			// PlantZoekItem pzi = new PlantZoekItem();
			// pzi.setNederlandseNaam("plant");
			// pzi.getBloeitijdLijst().add(Bloeitijd.JANUARI);
			// pzi.getBloeitijdLijst().add(Bloeitijd.FEBRUARI);
			// List<Plant> plantLijst = HibernateUtil.getPlantLijst(pzi);
			// List<Plant> plantLijst2 = HibernateUtil.getPlantLijst(pzi);
			// plantLijst.addAll(plantLijst2);
			// List list = HibernateUtil.getList(Plant.class);

			List<BeplantingsPlan> plannenlijst = HibernateUtil.getPlannenList(true);

			System.out.println(plannenlijst.size());
			// System.out.println(list.size());
			BeplantingsPlanToExcel beplantingsPlanToExcel = new BeplantingsPlanToExcel();
			beplantingsPlanToExcel.plantToExcel(plannenlijst.get(0));

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		} finally {
			// HibernateUtil.delete(plant);
		}
	}

}
