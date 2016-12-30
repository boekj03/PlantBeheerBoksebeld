package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Kleur;

public class KleurDropDownChoice extends DropDownChoice<Kleur> {

	private static List<Kleur> kleurList = Arrays.asList(Kleur.values());

	private static final long serialVersionUID = 3433963006205059371L;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public KleurDropDownChoice(final String id) {
		super(id, KleurDropDownChoice.kleurList, new ChoiceRenderer<Kleur>("kleurAsString", "kleur"));
	}

}
