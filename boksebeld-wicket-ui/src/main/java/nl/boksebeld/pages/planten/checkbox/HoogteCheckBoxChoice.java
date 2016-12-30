package nl.boksebeld.pages.planten.checkbox;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import nl.boksebeld.domein.plant.Hoogte;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class HoogteCheckBoxChoice extends CheckBoxMultipleChoice<Hoogte> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2285136699967598988L;
	/**
	 * 
	 */
	private static List<Hoogte> hoogteList = Arrays.asList(Hoogte.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public HoogteCheckBoxChoice(final String id) {
		super(id, HoogteCheckBoxChoice.hoogteList, new ChoiceRenderer<Hoogte>("hoogteAsString", "hoogte"));
	}

}
