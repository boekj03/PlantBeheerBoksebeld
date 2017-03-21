package nl.boksebeld.applicatie.excel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import nl.boksebeld.domein.plant.Plant;

public class WordOefenKlasse {
	// public static void main(String[] args) throws IOException {
	// // Blank Document
	// XWPFDocument document = new XWPFDocument();
	// // Write the Document in file system
	// FileOutputStream out = new FileOutputStream(new
	// File("createparagraph.docx"));
	//
	// // create Paragraph
	// XWPFParagraph paragraph = document.createParagraph();
	// XWPFRun run = paragraph.createRun();
	// run.setText("At tutorialspoint.com, we strive hard to " + "provide
	// quality tutorials for self-learning "
	// + "purpose in the domains of Academics, Information "
	// + "Technology, Management and Computer Programming Languages.");
	//
	// document.write(out);
	// out.close();
	// System.out.println("createparagraph.docx written successfully");
	// }

	public static void main(String[] args) throws Exception {

		Plant plant = new Plant();
		plant.setBeschrijving("sdfasdj fafasfas ddfasdfasfasdfasfas asdfasdfasdfasd asdfsafasdf");
		// Blank Document
		XWPFDocument document = new XWPFDocument();

		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File("create_table.docx"));

		// create table
		maakTable(plant, document);
		// Add a break between the tables
		document.createParagraph().createRun().addBreak();
		maakTable(plant, document);
		document.write(out);
		out.close();
		System.out.println("create_table.docx written successully");
	}

	private static void maakTable(Plant plant, XWPFDocument document) {

		int[] cols = { 489226, 182272, 402232, 1728

		};
		XWPFTable table = document.createTable(4, 4);

		// CTTblLayoutType type = table.getCTTbl().getTblPr().addNewTblLayout();
		// type.setType(STTblLayoutType.FIXED);
		for (int i = 0; i < 4; i++) {
			XWPFTableRow row = table.getRow(i);
			XWPFTableCell cell = row.getCell(i);
			cell.setText("fff" + i);

		}

	}
}
