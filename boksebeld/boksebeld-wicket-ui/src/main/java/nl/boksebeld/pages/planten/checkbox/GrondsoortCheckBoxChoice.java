package nl.boksebeld.pages.planten.checkbox;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import nl.boksebeld.domein.plant.Grondsoort;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class GrondsoortCheckBoxChoice extends CheckBoxMultipleChoice<Grondsoort> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6705099466316724987L;
	/**
	 * 
	 */
	private static List<Grondsoort> grondsoortList = Arrays.asList(Grondsoort.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public GrondsoortCheckBoxChoice(final String id) {
		super(id, GrondsoortCheckBoxChoice.grondsoortList,
				new ChoiceRenderer<Grondsoort>("grondsoortAsString", "grondsoort"));
	}

}
