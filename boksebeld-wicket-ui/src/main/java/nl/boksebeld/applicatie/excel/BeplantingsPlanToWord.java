package nl.boksebeld.applicatie.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.plant.Plant;

public class BeplantingsPlanToWord {

	public static File plantToWord(BeplantingsPlan plan) {
		// Blank Document
		XWPFDocument document = new XWPFDocument();
		File wordfile = new File(plan.getNaam() + System.currentTimeMillis() + ".docx");
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		try {
			Set<PlantPlaats> plantPlaatsLijst = plan.getPlantPlaatsLijst();
			for (Iterator iterator = plantPlaatsLijst.iterator(); iterator.hasNext();) {

				PlantPlaats plantPlaats = (PlantPlaats) iterator.next();

				Plant plant = plantPlaats.getPlant();
				if (null != plant) {
					// run.getDocument().addPictureData(plant.getImage(),
					// document.PICTURE_TYPE_JPEG);
					run.setText(plant.getBeschrijving());

					run.addBreak();

					InputStream in = new ByteArrayInputStream(plant.getImage());
					run.addPicture(in, XWPFDocument.PICTURE_TYPE_JPEG, "test", Units.toEMU(200), Units.toEMU(200));
					in.close();

				}
			}

			FileOutputStream output = new FileOutputStream(wordfile);
			document.write(output);
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return wordfile;
	}

}
