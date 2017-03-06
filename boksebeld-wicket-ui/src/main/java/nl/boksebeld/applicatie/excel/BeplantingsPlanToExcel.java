package nl.boksebeld.applicatie.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
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
	private static final Integer AANTAL_PER_M2 = 2;
	private static final Integer AANTAL_COLUM = 3;
	private static final Integer PRIJS_COLUM = 4;
	private static final Integer BOTANISCHE_COLUMN = 5;
	private static final Integer BESCHRIJVING_COLUMN = 6;

	/**
	 * 
	 * @param plan
	 * @throws IOException
	 */
	public static File plantToExcel(BeplantingsPlan plan) {

		Workbook workbook = new HSSFWorkbook();
		CellStyle boldStyl = createBoldStyle(workbook);
		File excelfile = new File(plan.getNaam() + System.currentTimeMillis() + ".xls");
		Sheet sheet = workbook.createSheet(plan.getNaam());

		try {
			Set<PlantPlaats> plantPlaatsLijst = plan.getPlantPlaatsLijst();
			int rijnummer = 0;
			maakEersteRij(sheet, boldStyl);
			for (Iterator iterator = plantPlaatsLijst.iterator(); iterator.hasNext();) {
				rijnummer++;
				PlantPlaats plantPlaats = (PlantPlaats) iterator.next();
				Row row = sheet.createRow(rijnummer);

				row.createCell(NAAM_COLUMN).setCellValue(plantPlaats.getNaam());
				row.createCell(VIERKANTEMETERS_COLUMN).setCellValue(plantPlaats.getVierkanteMeters());

				Plant plant = plantPlaats.getPlant();
				if (null != plant) {
					row.createCell(AANTAL_PER_M2).setCellValue(plant.getAantalPerMeter());
					int aantal = berekenAantal(plantPlaats, plant);
					row.createCell(AANTAL_COLUM).setCellValue(aantal);
					row.createCell(BOTANISCHE_COLUMN).setCellValue(plant.getBotanischeNaam());
					row.createCell(BESCHRIJVING_COLUMN).setCellValue(plant.getBeschrijving());
				}
			}

			FileOutputStream output = new FileOutputStream(excelfile);
			workbook.write(output);
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return excelfile;

	}

	private static CellStyle createBoldStyle(Workbook workbook) {
		CellStyle boldStyle = workbook.createCellStyle();
		Font boldFont = workbook.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldStyle.setFont(boldFont);
		return boldStyle;
	}

	private static void maakEersteRij(Sheet sheet, CellStyle boldStyl) {
		Row eersteRij = sheet.createRow(0);

		eersteRij.createCell(NAAM_COLUMN).setCellValue("Naam van plaats");
		eersteRij.createCell(VIERKANTEMETERS_COLUMN).setCellValue("M2  ");
		eersteRij.createCell(AANTAL_PER_M2).setCellValue("Stuk per m2");
		eersteRij.createCell(AANTAL_COLUM).setCellValue("Aantal");
		eersteRij.createCell(PRIJS_COLUM).setCellValue("Prijs");
		eersteRij.createCell(BOTANISCHE_COLUMN).setCellValue("Botanische naam   ");
		eersteRij.createCell(BESCHRIJVING_COLUMN).setCellValue("Beschrijving                  ");

		// colomn breedte
		sheet.autoSizeColumn(NAAM_COLUMN);
		sheet.autoSizeColumn(VIERKANTEMETERS_COLUMN);
		sheet.autoSizeColumn(AANTAL_PER_M2);
		sheet.autoSizeColumn(AANTAL_COLUM);
		sheet.autoSizeColumn(PRIJS_COLUM);
		sheet.autoSizeColumn(BOTANISCHE_COLUMN);
		sheet.autoSizeColumn(BESCHRIJVING_COLUMN);
		// zet bold
		eersteRij.setRowStyle(boldStyl);

	}

	private static Integer berekenAantal(PlantPlaats plantPlaats, Plant plant) {
		Integer retVal;
		try {
			Double ceil = Math.ceil(plantPlaats.getVierkanteMeters() * plant.getAantalPerMeter());
			retVal = ceil.intValue();
		} catch (Exception e) {
			return null;
		}
		return retVal;

	}

}
