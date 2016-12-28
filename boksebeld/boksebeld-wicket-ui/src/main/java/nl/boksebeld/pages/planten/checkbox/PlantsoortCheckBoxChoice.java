package nl.boksebeld.pages.planten.checkbox;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import nl.boksebeld.domein.plant.Plantsoort;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class PlantsoortCheckBoxChoice extends CheckBoxMultipleChoice<Plantsoort> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3174498578525496140L;
	/**
	 * 
	 */
	private static List<Plantsoort> plantsoortList = Arrays.asList(Plantsoort.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public PlantsoortCheckBoxChoice(final String id) {
		super(id, PlantsoortCheckBoxChoice.plantsoortList,
				new ChoiceRenderer<Plantsoort>("plantsoortAsString", "plantsoort"));
	}

}
