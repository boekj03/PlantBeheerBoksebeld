package nl.boksebeld.pages.planten.checkbox;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import nl.boksebeld.domein.plant.Lichtbehoefte;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class LichtbehoefteCheckBoxChoice extends CheckBoxMultipleChoice<Lichtbehoefte> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -693181411684131271L;
	/**
	 * 
	 */
	private static List<Lichtbehoefte> lichtbehoefteList = Arrays.asList(Lichtbehoefte.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public LichtbehoefteCheckBoxChoice(final String id) {
		super(id, LichtbehoefteCheckBoxChoice.lichtbehoefteList,
				new ChoiceRenderer<Lichtbehoefte>("lichtbehoefteAsString", "lichtbehoefte"));
	}

}
