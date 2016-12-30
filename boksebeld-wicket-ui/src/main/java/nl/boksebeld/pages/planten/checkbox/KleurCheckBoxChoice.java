package nl.boksebeld.pages.planten.checkbox;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import nl.boksebeld.domein.plant.Kleur;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class KleurCheckBoxChoice extends CheckBoxMultipleChoice<Kleur> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2885919657683259498L;
	/**
	 * 
	 */
	private static List<Kleur> kleurList = Arrays.asList(Kleur.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public KleurCheckBoxChoice(final String id) {
		super(id, KleurCheckBoxChoice.kleurList, new ChoiceRenderer<Kleur>("kleurAsString", "kleur"));
	}

}
