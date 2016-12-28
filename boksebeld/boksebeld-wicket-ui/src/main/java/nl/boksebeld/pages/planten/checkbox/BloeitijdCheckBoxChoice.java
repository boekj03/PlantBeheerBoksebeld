package nl.boksebeld.pages.planten.checkbox;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import nl.boksebeld.domein.plant.Bloeitijd;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class BloeitijdCheckBoxChoice extends CheckBoxMultipleChoice<Bloeitijd> {
	/**
	* 
	*/
	private static final long serialVersionUID = 3435334596337384761L;
	/**
	* 
	*/
	private static List<Bloeitijd> bloeitijdList = Arrays.asList(Bloeitijd.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public BloeitijdCheckBoxChoice(final String id) {
		super(id, BloeitijdCheckBoxChoice.bloeitijdList,
				new ChoiceRenderer<Bloeitijd>("bloeitijdAsString", "bloeitijd"));
	}

}
