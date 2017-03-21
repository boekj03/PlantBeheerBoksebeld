package nl.boksebeld.applicatie.excel;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

public class LogoRegelCreator {
	public static final Integer ROW_EEN = 0;
	public static final Integer ROW_TWEE = 1;
	private static final Integer ROW_DRIE = 2;
	private static final Integer ROW_VIER = 3;
	private static final Integer ROW_VIJF = 5;

	private static final String DATUM = "Datum: ";

	public void createLogoRegel(Sheet sheet, String planNaam) throws IOException {

		InputStream stream = getClass().getResourceAsStream("/bg-tuinen.jpg");
		byte[] bytes = IOUtils.toByteArray(stream);
		Workbook wb = sheet.getWorkbook();
		int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
		stream.close();
		// Returns an object that handles instantiating concrete classes
		CreationHelper helper = wb.getCreationHelper();
		// Creates the top-level drawing patriarch.
		Drawing drawing = sheet.createDrawingPatriarch();

		// Create an anchor that is attached to the worksheet
		ClientAnchor anchor = helper.createClientAnchor();

		// create an anchor with upper left cell _and_ bottom right cell
		anchor.setCol1(BeplantingsPlanToExcel.KOL_EEN);
		anchor.setRow1(ROW_EEN);
		anchor.setCol2(BeplantingsPlanToExcel.KOL_TWEE);
		anchor.setRow2(ROW_VIJF);

		// Creates a picture
		Picture pict = drawing.createPicture(anchor, pictureIdx);

		// Create the Cell B3
		Row row1 = sheet.createRow(ROW_EEN);
		Cell cell = row1.createCell(BeplantingsPlanToExcel.KOL_EEN);

		sheet.setColumnWidth(BeplantingsPlanToExcel.KOL_EEN, 20 * 256);
		sheet.setColumnWidth(BeplantingsPlanToExcel.KOL_TWEE, 35 * 256);
		sheet.setColumnWidth(BeplantingsPlanToExcel.KOL_DRIE, 15 * 256);
		sheet.setColumnWidth(BeplantingsPlanToExcel.KOL_VIER, 15 * 256);

		Row row4 = sheet.createRow(ROW_VIER);
		Cell cell2 = row4.createCell(BeplantingsPlanToExcel.KOL_TWEE);

		String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		cell2.setCellValue(DATUM + format);

		sheet.addMergedRegion(new CellRangeAddress(ROW_EEN, ROW_EEN, BeplantingsPlanToExcel.KOL_DRIE,
				BeplantingsPlanToExcel.KOL_VIER));

		Cell cell3 = row4.createCell(BeplantingsPlanToExcel.KOL_DRIE);
		cell3.setCellValue(planNaam);

		sheet.createRow(ROW_TWEE);
		sheet.createRow(ROW_DRIE);
		sheet.createRow(ROW_VIER);
	}

}
