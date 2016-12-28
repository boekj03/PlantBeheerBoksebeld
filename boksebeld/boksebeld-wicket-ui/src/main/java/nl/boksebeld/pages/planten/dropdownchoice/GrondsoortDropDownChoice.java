package nl.boksebeld.pages.planten.dropdownchoice;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import nl.boksebeld.domein.plant.Grondsoort;

public class GrondsoortDropDownChoice extends DropDownChoice<Grondsoort> {

	private static List<Grondsoort> grondsoortList = Arrays.asList(Grondsoort.values());

	private static final long serialVersionUID = 3433963006205059371L;

	/**
	 * Constructor.
	 *
	 * @param id
	 *            component id
	 */
	public GrondsoortDropDownChoice(final String id) {
		super(id, GrondsoortDropDownChoice.grondsoortList,
				new ChoiceRenderer<Grondsoort>("grondsoortAsString", "grondsoort"));
	}

}
