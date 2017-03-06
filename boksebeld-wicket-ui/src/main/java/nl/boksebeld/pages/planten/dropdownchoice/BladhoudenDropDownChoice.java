package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Bladhoudend;

public class BladhoudenDropDownChoice extends DropDownChoice<Bladhoudend> {

	private static List<Bladhoudend> bladhoudendList = Arrays.asList(Bladhoudend.values());

	private static final long serialVersionUID = 3433963006205059371L;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public BladhoudenDropDownChoice(final String id) {
		super(id, BladhoudenDropDownChoice.bladhoudendList,
				new ChoiceRenderer<Bladhoudend>("bladhoudendAsString", "bladhoudend"));
	}

}
