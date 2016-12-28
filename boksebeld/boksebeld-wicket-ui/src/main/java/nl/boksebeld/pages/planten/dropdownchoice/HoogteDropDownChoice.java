package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Hoogte;

public class HoogteDropDownChoice extends DropDownChoice<Hoogte> {

	private static List<Hoogte> hoogteList = Arrays.asList(Hoogte.values());

	private static final long serialVersionUID = 3433963006205059371L;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public HoogteDropDownChoice(final String id) {
		super(id, HoogteDropDownChoice.hoogteList, new ChoiceRenderer<Hoogte>("hoogteAsString", "hoogte"));
	}

}
