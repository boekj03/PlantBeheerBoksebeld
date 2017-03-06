package nl.boksebeld.pages.planten.zoeken;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

import nl.boksebeld.domein.plant.PlantZoekItem;
import nl.boksebeld.pages.planten.checkbox.BloeitijdCheckBoxChoice;
import nl.boksebeld.pages.planten.checkbox.GrondsoortCheckBoxChoice;
import nl.boksebeld.pages.planten.checkbox.HoogteCheckBoxChoice;
import nl.boksebeld.pages.planten.checkbox.KleurCheckBoxChoice;
import nl.boksebeld.pages.planten.checkbox.LichtbehoefteCheckBoxChoice;
import nl.boksebeld.pages.planten.checkbox.PlantsoortCheckBoxChoice;
import nl.boksebeld.pages.planten.radio.BladhoudendCheckChoice;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class PlantZoekForm extends Form<PlantZoekItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4473658422113438498L;

	public PlantZoekForm(final String id, IModel<PlantZoekItem> plantZoekModel) {
		super(id, plantZoekModel);

		// naw gegevens
		nederlandseNaamGegevens(plantZoekModel);
		beschrijvingGegevens(plantZoekModel);
		botanischeNaamGegevens(plantZoekModel);
		hoogteGegevens(plantZoekModel);

		bladhoudendGegevens(plantZoekModel);

		bloeitijdLijstGegevens(plantZoekModel);
		grondsoortLijstGegevens(plantZoekModel);
		kleurLijstGegevens(plantZoekModel);
		plantsoortLijstGegevens(plantZoekModel);
		lichtbehoefteLijstGegevens(plantZoekModel);

	}

	private void bladhoudendGegevens(IModel<PlantZoekItem> plantZoekModel) {
		BladhoudendCheckChoice bladhoudendRC = new BladhoudendCheckChoice("bladhoudendLijst");
		add(bladhoudendRC);
	}

	private void nederlandseNaamGegevens(IModel<PlantZoekItem> plantZoekModel) {
		TextField<String> nederlandseNaam = new TextField<String>("nederlandseNaam");

		nederlandseNaam.add(StringValidator.maximumLength(30));
		add(nederlandseNaam);
	}

	private void beschrijvingGegevens(IModel<PlantZoekItem> plantZoekModel) {
		TextField<String> beschrijving = new TextField<String>("beschrijving");

		beschrijving.add(StringValidator.maximumLength(30));
		add(beschrijving);
	}

	private void botanischeNaamGegevens(IModel<PlantZoekItem> plantZoekModel) {
		TextField<String> botanischeNaam = new TextField<String>("botanischeNaam");

		botanischeNaam.add(StringValidator.maximumLength(30));
		add(botanischeNaam);
	}

	private void hoogteGegevens(IModel<PlantZoekItem> plantZoekModel) {
		HoogteCheckBoxChoice hoogteCb = new HoogteCheckBoxChoice("hoogteLijst");
		add(hoogteCb);
	}

	private void bloeitijdLijstGegevens(IModel<PlantZoekItem> plantZoekModel) {
		BloeitijdCheckBoxChoice bloeitijdCb = new BloeitijdCheckBoxChoice("bloeitijdLijst");
		add(bloeitijdCb);
	}

	private void grondsoortLijstGegevens(IModel<PlantZoekItem> plantZoekModel) {
		GrondsoortCheckBoxChoice grondsoortCb = new GrondsoortCheckBoxChoice("grondsoortLijst");
		add(grondsoortCb);
	}

	private void kleurLijstGegevens(IModel<PlantZoekItem> plantZoekModel) {
		KleurCheckBoxChoice kleurCb = new KleurCheckBoxChoice("kleurLijst");
		add(kleurCb);
	}

	private void plantsoortLijstGegevens(IModel<PlantZoekItem> plantZoekModel) {
		PlantsoortCheckBoxChoice plantsoortCb = new PlantsoortCheckBoxChoice("plantsoortLijst");
		add(plantsoortCb);
	}

	private void lichtbehoefteLijstGegevens(IModel<PlantZoekItem> plantZoekModel) {
		LichtbehoefteCheckBoxChoice lichtbehoefteCb = new LichtbehoefteCheckBoxChoice("lichtbehoefteLijst");
		add(lichtbehoefteCb);
	}

}
