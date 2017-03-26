package nl.boksebeld.applicatie.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.plant.Plant;

public class OverzichtExcel {

	private static final Map<Integer, Integer> KOLOMBREEDTELIJST = new HashMap<Integer, Integer>();
	private static final Map<Integer, String> KOPTEKSTEN_LIJST = new HashMap<Integer, String>();
	private static final Map<Integer, String> KOLOM_LETTERS = new HashMap<Integer, String>();

	private static Integer KOLNAAM = 0;
	private static Integer KOLBOTANISCHE_NAAM = 1;
	private static Integer BLOEITIJD_NAAM = 2;
	private static Integer KOLM2 = 3;
	private static Integer KOLSTUKM2 = 4;
	private static Integer KOLAANTAL = 5;
	private static Integer KOLPRIJS = 6;
	private static Integer KOLTOTAAL = 7;
	private static Integer KOLBESCHRIJVING = 8;
	private static Integer KOLFOTO = 9;

	private static Integer ROW1 = 1;

	static {
		KOLOMBREEDTELIJST.put(KOLNAAM, Integer.valueOf(20 * 256));
		KOLOMBREEDTELIJST.put(KOLBOTANISCHE_NAAM, Integer.valueOf(20 * 256));
		KOLOMBREEDTELIJST.put(BLOEITIJD_NAAM, Integer.valueOf(15 * 256));
		KOLOMBREEDTELIJST.put(KOLM2, Integer.valueOf(10 * 256));
		KOLOMBREEDTELIJST.put(KOLSTUKM2, Integer.valueOf(10 * 256));
		KOLOMBREEDTELIJST.put(KOLAANTAL, Integer.valueOf(10 * 256));
		KOLOMBREEDTELIJST.put(KOLPRIJS, Integer.valueOf(10 * 256));
		KOLOMBREEDTELIJST.put(KOLTOTAAL, Integer.valueOf(10 * 256));
		KOLOMBREEDTELIJST.put(KOLBESCHRIJVING, Integer.valueOf(60 * 256));
		KOLOMBREEDTELIJST.put(KOLFOTO, Integer.valueOf(22 * 251));

		KOPTEKSTEN_LIJST.put(KOLNAAM, "Naam van plaats");
		KOPTEKSTEN_LIJST.put(KOLBOTANISCHE_NAAM, "Botanische naam");
		KOPTEKSTEN_LIJST.put(BLOEITIJD_NAAM, "Bloeitijd");
		KOPTEKSTEN_LIJST.put(KOLM2, "M2");
		KOPTEKSTEN_LIJST.put(KOLSTUKM2, "Stuk per m2");
		KOPTEKSTEN_LIJST.put(KOLAANTAL, "Aantal");
		KOPTEKSTEN_LIJST.put(KOLPRIJS, "Prijs");
		KOPTEKSTEN_LIJST.put(KOLTOTAAL, "totaal");
		KOPTEKSTEN_LIJST.put(KOLBESCHRIJVING, "Beschrijving");
		KOPTEKSTEN_LIJST.put(KOLFOTO, "Foto");

		KOLOM_LETTERS.put(KOLM2, "D");
		KOLOM_LETTERS.put(KOLSTUKM2, "E");
		KOLOM_LETTERS.put(KOLAANTAL, "F");
		KOLOM_LETTERS.put(KOLPRIJS, "G");

	}

	public void createOverzichtSheet(BeplantingsPlan plan, Sheet sheet) {
		sheet.createFreezePane(0, 2);// bovenste rij blokeren

		// afdruk bereik

		printSetupInstellen(sheet);

		zetKolomBreedte(sheet);

		Set<PlantPlaats> plantPlaatsLijst = plan.getPlantPlaatsLijst();
		for (PlantPlaats plantPlaats : plantPlaatsLijst) {

			voegPlantToeAanExcel(sheet, plantPlaats);

		}
	}

	private void printSetupInstellen(Sheet sheet) {
		sheet.setFitToPage(true);
		PrintSetup ps = sheet.getPrintSetup();
		ps.setLandscape(true);
		ps.setFitWidth((short) 1);
		ps.setFitHeight((short) 0);

	}

	private void voegPlantToeAanExcel(Sheet sheet, PlantPlaats plantPlaats) {
		int lastRowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(++lastRowNum);

		Plant plant = plantPlaats.getPlant();
		if (plant == null) {
			plant = new Plant();
		}

		createKolnaam(plantPlaats, row);
		createBotanischeNaam(row, plant);
		createBloeitijd(row, plant);
		createM2(plantPlaats, row);
		createStukM2(row);
		createKolAantal(row);
		createPrijs(row);
		createKolTotaal(row);

		Cell beschrijvingCell = row.createCell(KOLBESCHRIJVING);
		XSSFCellStyle terugloopStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
		terugloopStyle.setWrapText(true);
		terugloopStyle.setVerticalAlignment(VerticalAlignment.TOP);
		beschrijvingCell.setCellStyle(terugloopStyle);
		beschrijvingCell.setCellValue(plant.getBeschrijving());

		voegFotoToe(sheet, row, plant);
	}

	private void createBloeitijd(Row row, Plant plant) {
		// boven uitlijnen alles in de regel
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);

		Cell cell = row.createCell(BLOEITIJD_NAAM);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellValue(plant.getBloeitijdWeergaveOrg());

	}

	private XSSFCellStyle bovenuitlijnen(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = (XSSFCellStyle) row.getSheet().getWorkbook().createCellStyle();
		bovenuitlijnenStyle.setVerticalAlignment(VerticalAlignment.TOP);
		bovenuitlijnenStyle.setWrapText(true);
		return bovenuitlijnenStyle;
	}

	private void createKolTotaal(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);

		DataFormat df = row.getSheet().getWorkbook().createDataFormat();
		bovenuitlijnenStyle.setDataFormat(df.getFormat("€#,##0.00;€#,##0.00"));

		Cell cell = row.createCell(KOLTOTAAL);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellFormula("PRODUCT(" + KOLOM_LETTERS.get(KOLAANTAL) + (row.getRowNum() + 1) + ":"
				+ KOLOM_LETTERS.get(KOLPRIJS) + (row.getRowNum() + 1) + ")");
	}

	private void createKolAantal(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLAANTAL);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellFormula("PRODUCT(" + KOLOM_LETTERS.get(KOLM2) + (row.getRowNum() + 1) + ":"
				+ KOLOM_LETTERS.get(KOLSTUKM2) + (row.getRowNum() + 1) + ")");
	}

	private void createM2(PlantPlaats plantPlaats, Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLM2);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellValue(plantPlaats.getVierkanteMeters());
	}

	private void createStukM2(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLSTUKM2);
		cell.setCellStyle(bovenuitlijnenStyle);

	}

	private void createPrijs(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLPRIJS);
		cell.setCellStyle(bovenuitlijnenStyle);

	}

	private void createBotanischeNaam(Row row, Plant plant) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLBOTANISCHE_NAAM);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellValue(plant.getBotanischeNaam());
	}

	private void createKolnaam(PlantPlaats plantPlaats, Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLNAAM);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellValue(plantPlaats.getNaam());
	}

	private void voegFotoToe(Sheet sheet, Row row, Plant plant) {

		row.setHeight((short) 2270);
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
		anchor.setCol1(KOLFOTO);
		anchor.setRow1(row.getRowNum());
		anchor.setCol2(KOLFOTO + 1);
		anchor.setRow2(row.getRowNum() + 1);

		Picture pict = drawing.createPicture(anchor, pictureIdx);
		row.createCell(KOLFOTO);

	}

	private void zetKolomBreedte(Sheet sheet) {
		for (Entry<Integer, Integer> entry : KOLOMBREEDTELIJST.entrySet()) {
			sheet.setColumnWidth(entry.getKey(), entry.getValue());
		}

		Row regel1 = sheet.createRow(ROW1);
		// bold
		XSSFFont defaultFont = (XSSFFont) sheet.getWorkbook().createFont();
		defaultFont.setBold(true);
		XSSFCellStyle boldStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
		boldStyle.setFont(defaultFont);

		boldStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		boldStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		defaultFont.setColor(IndexedColors.ORANGE.getIndex());

		for (Entry<Integer, String> entry : KOPTEKSTEN_LIJST.entrySet()) {
			Cell cell = regel1.createCell(entry.getKey());
			cell.setCellValue(entry.getValue());
			cell.setCellStyle(boldStyle);
		}
	}

}
