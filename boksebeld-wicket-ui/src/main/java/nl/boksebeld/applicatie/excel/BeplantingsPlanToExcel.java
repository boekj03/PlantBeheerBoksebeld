package nl.boksebeld.applicatie.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.plant.Plant;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class BeplantingsPlanToExcel {

	public static final Integer KOL_EEN = 0;
	public static final Integer KOL_TWEE = 1;
	public static final Integer KOL_DRIE = 2;
	public static final Integer KOL_VIER = 3;
	public static final Integer KOL_VIJF = 4;

	private static final Integer PAGINA_LENGTH = 50;

	private static final String NEDERLANDSE_NAAM = "Nederlands naam: ";
	private static final String BLOEITIJD = "Bloeitijd: ";
	private static final String KLEUR = "Kleur: ";

	private static final Integer AANTAL_RIJEN_FOTO = 6;
	private static final Integer AANTAL_RIJEN_BESCHRIJVING = 6;

	private LogoRegelCreator logoRegelCreator = new LogoRegelCreator();

	public File plantToExcel(BeplantingsPlan plan) {
		Workbook wb = new XSSFWorkbook();
		File excelfile = new File(plan.getNaam() + System.currentTimeMillis() + ".xlsx");
		Sheet sheet = wb.createSheet(plan.getNaam());
		try {
			logoRegelCreator.createLogoRegel(sheet, "Dit is het plan van hans");
			Set<PlantPlaats> plantPlaatsLijst = plan.getPlantPlaatsLijst();
			for (PlantPlaats plantPlaats : plantPlaatsLijst) {
				voegPlantToeAanExcel(sheet, plantPlaats);

			}

			// Write the Excel file
			FileOutputStream fileOut = null;
			fileOut = new FileOutputStream(excelfile);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelfile;
	}

	// voor test

	// public void maakexceltest(List<Plant> plantLijst) throws
	// FileNotFoundException, IOException { dit was voor test
	// Workbook wb = new XSSFWorkbook();
	//
	// Sheet sheet = wb.createSheet("My Sample Excel");
	//
	// logoRegelCreator.createLogoRegel(sheet, "Dit is het plan van hans");
	// for (int i = 0; i < plantLijst.size(); i++) {
	//
	// voegPlantToeAanExcel(sheet, plantLijst.get(i));
	// }
	//
	// // Write the Excel file
	// FileOutputStream fileOut = null;
	// fileOut = new FileOutputStream("myFile1" + System.currentTimeMillis() +
	// ".xlsx");
	// wb.write(fileOut);
	// fileOut.close();
	// }

	private void voegPlantToeAanExcel(Sheet sheet, PlantPlaats plantPlaats) throws IOException {
		int onderhandenRij = bepaalStartRij(sheet);

		Plant plant = plantPlaats.getPlant();
		if (plant != null) {

			voegFotoToe(sheet, onderhandenRij, plant);
			voegcodeToe(sheet, onderhandenRij++, plantPlaats);
			voegBotanischeNaamToe(sheet, onderhandenRij++, plant);
			voegNederlandseNaamToe(sheet, onderhandenRij++, plant);
			voegBloeitijdToe(sheet, onderhandenRij++, plant);
			voegKleurToe(sheet, onderhandenRij++, plant);
			voegBeschrijvingToe(sheet, plant);
		}
	}

	private int bepaalStartRij(Sheet sheet) {
		int lastRowNum = sheet.getLastRowNum();
		int onderhandenRij = 0;

		int startpositieOpPagina = lastRowNum % PAGINA_LENGTH;
		int restRuimte = (PAGINA_LENGTH - startpositieOpPagina);

		if (restRuimte < 17) {
			onderhandenRij = lastRowNum + restRuimte + 1;
		} else {
			onderhandenRij = lastRowNum + 5;
		}

		return onderhandenRij;
	}

	private void voegBeschrijvingToe(Sheet sheet, Plant plant) {
		int lastRowNum = sheet.getLastRowNum();
		lastRowNum = lastRowNum + 1;
		sheet.addMergedRegion(
				new CellRangeAddress(lastRowNum, lastRowNum + AANTAL_RIJEN_BESCHRIJVING, KOL_EEN, KOL_VIER));
		Row row = sheet.createRow(lastRowNum);
		Cell cell = row.createCell(KOL_EEN);

		XSSFCellStyle terugloopStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
		terugloopStyle.setWrapText(true);
		terugloopStyle.setVerticalAlignment(VerticalAlignment.TOP);

		cell.setCellValue(plant.getBeschrijving());
		cell.setCellStyle(terugloopStyle);
		sheet.createRow(lastRowNum + AANTAL_RIJEN_BESCHRIJVING);
	}

	private void voegKleurToe(Sheet sheet, int onderhandenRij, Plant plant) {
		Row row = sheet.createRow(onderhandenRij);
		Cell cell = row.createCell(KOL_EEN);
		if (plant.getKleur() != null) {
			cell.setCellValue(KLEUR + plant.getKleur().getKleur());
		}

	}

	private void voegBloeitijdToe(Sheet sheet, int onderhandenRij, Plant plant) {
		Row row = sheet.createRow(onderhandenRij);
		Cell cell = row.createCell(KOL_EEN);
		cell.setCellValue(BLOEITIJD + plant.getBloeitijdWeergaveOrg());

	}

	private void voegNederlandseNaamToe(Sheet sheet, int onderhandenRij, Plant plant) {
		Row row = sheet.createRow(onderhandenRij);
		Cell cell = row.createCell(KOL_EEN);
		cell.setCellValue(NEDERLANDSE_NAAM + plant.getNederlandseNaam());

	}

	private void voegBotanischeNaamToe(Sheet sheet, int onderhandenRij, Plant plant) {
		XSSFFont defaultFont = (XSSFFont) sheet.getWorkbook().createFont();
		defaultFont.setBold(true);
		XSSFCellStyle boldStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
		boldStyle.setFont(defaultFont);

		Row row = sheet.createRow(onderhandenRij);
		Cell cell = row.createCell(KOL_EEN);
		cell.setCellStyle(boldStyle);
		cell.setCellValue(plant.getBotanischeNaam());

	}

	private void voegcodeToe(Sheet sheet, int onderhandenRij, PlantPlaats plantPlaats) {
		Row row = sheet.createRow(onderhandenRij);
		Cell cell = row.createCell(KOL_EEN);
		cell.setCellValue(plantPlaats.getNaam());
	}

	private void voegFotoToe(Sheet sheet, int onderhandenRij, Plant plant) {

		byte[] bytes = plant.getImage();
		if (null == bytes) {
			return;
		}

		Workbook wb = sheet.getWorkbook();
		int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

		CreationHelper helper = wb.getCreationHelper();
		Drawing drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = helper.createClientAnchor();

		// create an anchor with upper left cell _and_ bottom right cell
		anchor.setCol1(KOL_DRIE);
		anchor.setRow1(onderhandenRij);
		anchor.setCol2(KOL_VIJF);
		anchor.setRow2(onderhandenRij + AANTAL_RIJEN_FOTO);

		Picture pict = drawing.createPicture(anchor, pictureIdx);

		for (int i = 0; i < AANTAL_RIJEN_FOTO; i++) {
			// Create the Cell B3
			Row row = sheet.createRow(onderhandenRij + i);
			row.createCell(KOL_DRIE);
			row.createCell(KOL_VIER);

		}

	}

}
