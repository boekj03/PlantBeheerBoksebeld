package nl.boksebeld.pages.planten.radio;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;

import nl.boksebeld.domein.plant.Bloeitijd;

public class BloeitijdRadioChoice extends RadioChoice<Bloeitijd> {

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
	public BloeitijdRadioChoice(final String id) {
		super(id, BloeitijdRadioChoice.bloeitijdList, new ChoiceRenderer<Bloeitijd>("bloeitijdAsString", "bloeitijd"));
	}
}
