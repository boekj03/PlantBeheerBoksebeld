package nl.boksebeld.pages.planten.radio;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;

import nl.boksebeld.domein.plant.Bladhoudend;

public class BladhoudendCheckChoice extends CheckBoxMultipleChoice<Bladhoudend> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5171134185813100979L;
	private static List<Bladhoudend> bladhoudendLijst = Arrays.asList(Bladhoudend.values());

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public BladhoudendCheckChoice(final String id) {
		super(id, BladhoudendCheckChoice.bladhoudendLijst,
				new ChoiceRenderer<Bladhoudend>("bladhoudendAsString", "bladhoudend"));
	}
}
