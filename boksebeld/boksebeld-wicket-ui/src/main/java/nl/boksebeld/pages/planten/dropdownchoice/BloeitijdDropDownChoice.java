package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Bloeitijd;

public class BloeitijdDropDownChoice extends DropDownChoice<Bloeitijd> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5171134185813100979L;
	private static List<Bloeitijd> bloeitijdList = Arrays.asList(Bloeitijd.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public BloeitijdDropDownChoice(final String id) {
		super(id, BloeitijdDropDownChoice.bloeitijdList,
				new ChoiceRenderer<Bloeitijd>("bloeitijdAsString", "bloeitijd"));
	}

}
