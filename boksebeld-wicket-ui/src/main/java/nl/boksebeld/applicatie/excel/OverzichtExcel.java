package nl.boksebeld.applicatie.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import org.apache.poi.xssf.usermodel.XSSFColor;
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
	private static Integer KOLINKOOPPRIJS = 6;
	private static Integer KOLMARGE = 7;
	private static Integer KOLVERKOOPPRIJS = 8;
	private static Integer KOLTOTAAL = 9;

	private static Integer KOLLEVERANCIER = 10;
	private static Integer KOLMAAT = 11;

	private static Integer KOLBESCHRIJVING = 12;
	private static Integer KOLFOTO = 13;

	private static Integer ROW1 = 0;

	static {
		KOLOMBREEDTELIJST.put(KOLNAAM, Integer.valueOf(1600));
		KOLOMBREEDTELIJST.put(KOLBOTANISCHE_NAAM, Integer.valueOf(5760));
		KOLOMBREEDTELIJST.put(BLOEITIJD_NAAM, Integer.valueOf(3160));
		KOLOMBREEDTELIJST.put(KOLM2, Integer.valueOf(1600));
		KOLOMBREEDTELIJST.put(KOLSTUKM2, Integer.valueOf(3200));
		KOLOMBREEDTELIJST.put(KOLAANTAL, Integer.valueOf(1600));
		KOLOMBREEDTELIJST.put(KOLINKOOPPRIJS, Integer.valueOf(1600));
		KOLOMBREEDTELIJST.put(KOLMARGE, Integer.valueOf(1600));
		KOLOMBREEDTELIJST.put(KOLVERKOOPPRIJS, Integer.valueOf(1600));
		KOLOMBREEDTELIJST.put(KOLTOTAAL, Integer.valueOf(3200));

		KOLOMBREEDTELIJST.put(KOLLEVERANCIER, Integer.valueOf(5760));
		KOLOMBREEDTELIJST.put(KOLMAAT, Integer.valueOf(5760));

		KOLOMBREEDTELIJST.put(KOLBESCHRIJVING, Integer.valueOf(23040));
		KOLOMBREEDTELIJST.put(KOLFOTO, Integer.valueOf(5522)); // 5522 20.86
																// =
																// 2.64717162

		KOPTEKSTEN_LIJST.put(KOLNAAM, "Plaats");
		KOPTEKSTEN_LIJST.put(KOLBOTANISCHE_NAAM, "Botanische naam");
		KOPTEKSTEN_LIJST.put(BLOEITIJD_NAAM, "Bloeitijd");
		KOPTEKSTEN_LIJST.put(KOLM2, "M2");
		KOPTEKSTEN_LIJST.put(KOLSTUKM2, "Stuk per m2");
		KOPTEKSTEN_LIJST.put(KOLAANTAL, "Aantal");
		KOPTEKSTEN_LIJST.put(KOLINKOOPPRIJS, "Prijs 1");
		KOPTEKSTEN_LIJST.put(KOLMARGE, "Marge");
		KOPTEKSTEN_LIJST.put(KOLVERKOOPPRIJS, "Prijs 2");
		KOPTEKSTEN_LIJST.put(KOLTOTAAL, "totaal");

		KOPTEKSTEN_LIJST.put(KOLLEVERANCIER, "leverancier");
		KOPTEKSTEN_LIJST.put(KOLMAAT, "maat");

		KOPTEKSTEN_LIJST.put(KOLBESCHRIJVING, "Beschrijving");
		KOPTEKSTEN_LIJST.put(KOLFOTO, "Foto");

		KOLOM_LETTERS.put(KOLM2, "D");
		KOLOM_LETTERS.put(KOLSTUKM2, "E");
		KOLOM_LETTERS.put(KOLAANTAL, "F");
		KOLOM_LETTERS.put(KOLINKOOPPRIJS, "G");
		KOLOM_LETTERS.put(KOLMARGE, "H");
		KOLOM_LETTERS.put(KOLVERKOOPPRIJS, "I");
	}

	public void createOverzichtSheet(BeplantingsPlan plan, Sheet sheet) {
		sheet.createFreezePane(0, 1);// bovenste rij blokeren

		// afdruk bereik

		printSetupInstellen(sheet);

		zetKolomBreedte(sheet);

		for (PlantPlaats plantPlaats : plan.getSortedPlantPlaatsLijst()) {

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
		createInkoopPrijs(row, plant);
		createMarge(row);
		createVerkoopPrijs(row);

		createLeverancier(row, plant);
		createMaat(row, plant);

		createKolTotaal(row);

		Cell beschrijvingCell = row.createCell(KOLBESCHRIJVING);
		XSSFCellStyle terugloopStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
		terugloopStyle.setWrapText(true);
		terugloopStyle.setVerticalAlignment(VerticalAlignment.TOP);
		beschrijvingCell.setCellStyle(terugloopStyle);
		beschrijvingCell.setCellValue(plant.getBeschrijving());

		voegFotoToe(sheet, row, plant);
	}

	private void createMaat(Row row, Plant plant) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLMAAT);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellValue(plant.getHandelsmaat());
	}

	private void createLeverancier(Row row, Plant plant) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLLEVERANCIER);
		cell.setCellStyle(bovenuitlijnenStyle);
		cell.setCellValue(plant.getLeverancier());

	}

	private void createVerkoopPrijs(Row row) {

		String formule = "SUM(X_Y," + "PRODUCT(X_Y,Z_Y))";

		formule = formule.replaceAll("Y", String.valueOf((row.getRowNum() + 1)));
		formule = formule.replaceAll("X", KOLOM_LETTERS.get(KOLINKOOPPRIJS));
		formule = formule.replaceAll("Z", KOLOM_LETTERS.get(KOLMARGE));
		formule = formule.replaceAll("_", "");
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLVERKOOPPRIJS);
		cell.setCellStyle(bovenuitlijnenStyle);

		cell.setCellFormula(formule);

	}

	private void createMarge(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLMARGE);
		cell.setCellStyle(bovenuitlijnenStyle);
		// marge
		cell.setCellValue(0);
		DataFormat df = row.getSheet().getWorkbook().createDataFormat();
		bovenuitlijnenStyle.setDataFormat(df.getFormat("###.##%"));

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

		String formule = "PRODUCT(X_Y,Z_Y)";
		formule = formule.replaceAll("Y", String.valueOf((row.getRowNum() + 1)));
		formule = formule.replaceAll("_", "");
		formule = formule.replaceAll("X", KOLOM_LETTERS.get(KOLAANTAL));
		formule = formule.replaceAll("Z", KOLOM_LETTERS.get(KOLVERKOOPPRIJS));

		cell.setCellFormula(formule);
	}

	private void createKolAantal(Row row) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLAANTAL);
		cell.setCellStyle(bovenuitlijnenStyle);

		String formule = "PRODUCT(X_Y,Z_Y)";

		formule = formule.replaceAll("Y", String.valueOf((row.getRowNum() + 1)));
		formule = formule.replaceAll("_", "");
		formule = formule.replaceAll("X", KOLOM_LETTERS.get(KOLM2));
		formule = formule.replaceAll("Z", KOLOM_LETTERS.get(KOLSTUKM2));
		cell.setCellFormula(formule);

	}

	public static void main(String[] args) {

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

	private void createInkoopPrijs(Row row, Plant plant) {
		XSSFCellStyle bovenuitlijnenStyle = bovenuitlijnen(row);
		Cell cell = row.createCell(KOLINKOOPPRIJS);
		cell.setCellStyle(bovenuitlijnenStyle);
		if (null != plant.getInkoopprijs()) {
			cell.setCellValue(plant.getInkoopprijs());
		}
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

		boldStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(231, 230, 230)));
		boldStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

		defaultFont.setColor(IndexedColors.ORANGE.getIndex());

		for (Entry<Integer, String> entry : KOPTEKSTEN_LIJST.entrySet()) {
			Cell cell = regel1.createCell(entry.getKey());
			cell.setCellValue(entry.getValue());
			cell.setCellStyle(boldStyle);
		}
	}

}
