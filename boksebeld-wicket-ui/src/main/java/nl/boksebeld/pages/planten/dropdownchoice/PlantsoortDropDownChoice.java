package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Plantsoort;

public class PlantsoortDropDownChoice extends DropDownChoice<Plantsoort> {

	private static List<Plantsoort> plantsoortList = Arrays.asList(Plantsoort.values());

	private static final long serialVersionUID = 3433963006205059371L;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public PlantsoortDropDownChoice(final String id) {
		super(id, PlantsoortDropDownChoice.plantsoortList,
				new ChoiceRenderer<Plantsoort>("plantsoortAsString", "plantsoort"));
	}

}
