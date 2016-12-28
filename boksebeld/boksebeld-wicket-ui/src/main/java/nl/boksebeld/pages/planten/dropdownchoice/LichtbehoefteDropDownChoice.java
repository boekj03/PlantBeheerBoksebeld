package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Lichtbehoefte;

public class LichtbehoefteDropDownChoice extends DropDownChoice<Lichtbehoefte> {

	private static List<Lichtbehoefte> lichtbehoefteList = Arrays.asList(Lichtbehoefte.values());

	private static final long serialVersionUID = 3433963006205059371L;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public LichtbehoefteDropDownChoice(final String id) {
		super(id, LichtbehoefteDropDownChoice.lichtbehoefteList,
				new ChoiceRenderer<Lichtbehoefte>("lichtbehoefteAsString", "lichtbehoefte"));
	}

}
