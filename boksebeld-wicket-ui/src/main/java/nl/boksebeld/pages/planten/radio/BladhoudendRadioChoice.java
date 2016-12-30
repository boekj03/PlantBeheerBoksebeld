package nl.boksebeld.pages.planten.radio;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;

public class BladhoudendRadioChoice extends RadioChoice<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5171134185813100979L;
	private static List<Boolean> booleanList = Arrays.asList(Boolean.TRUE, Boolean.FALSE);

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public BladhoudendRadioChoice(final String id) {
		super(id, BladhoudendRadioChoice.booleanList, new ChoiceRenderer<Boolean>());
	}
}
