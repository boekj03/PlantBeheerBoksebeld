package nl.boksebeld.pages.planten;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

import nl.boksebeld.domein.plant.PlantZoekItem;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class PlantenFilterForm extends Form<PlantZoekItem> {

	private static final long serialVersionUID = -5012987864217281863L;

	public PlantenFilterForm(final String id, IModel<PlantZoekItem> plantZoekModel) {
		super(id, plantZoekModel);

		botanischeNaamGegevens(plantZoekModel);

	}

	private void botanischeNaamGegevens(IModel<PlantZoekItem> plantZoekModel) {
		TextField<String> botanischeNaam = new TextField<String>("botanischeNaam");
		botanischeNaam.add(StringValidator.maximumLength(30));
		add(botanischeNaam);
	}

}
