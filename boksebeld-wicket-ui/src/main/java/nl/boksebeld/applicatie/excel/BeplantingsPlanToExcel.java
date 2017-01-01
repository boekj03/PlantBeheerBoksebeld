package nl.boksebeld.applicatie.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.plant.Plant;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class BeplantingsPlanToExcel {

	private static final Integer NAAM_COLUMN = 0;
	private static final Integer VIERKANTEMETERS_COLUMN = 1;
	private static final Integer BOTANISCHE_COLUMN = 2;

	/**
	 * 
	 * @param plan
	 * @throws IOException
	 */
	public static File plantToExcel(BeplantingsPlan plan) {

		Workbook workbook = new HSSFWorkbook();
		File excelfile = new File(plan.getNaam() + ".xls");
		Sheet sheet = workbook.createSheet(plan.getNaam());

		try {
			Set<PlantPlaats> plantPlaatsLijst = plan.getPlantPlaatsLijst();
			int rijnummer = 0;

			for (Iterator iterator = plantPlaatsLijst.iterator(); iterator.hasNext();) {
				rijnummer++;
				PlantPlaats plantPlaats = (PlantPlaats) iterator.next();
				Row row = sheet.createRow(rijnummer);

				row.createCell(NAAM_COLUMN).setCellValue(plantPlaats.getNaam());
				row.createCell(VIERKANTEMETERS_COLUMN).setCellValue(plantPlaats.getVierkanteMeters());

				Plant plant = plantPlaats.getPlant();
				row.createCell(BOTANISCHE_COLUMN).setCellValue(plant.getBotanischeNaam());
			}

			FileOutputStream output = new FileOutputStream(excelfile);
			workbook.write(output);
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return excelfile;

	}

}
