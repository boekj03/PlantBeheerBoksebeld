package nl.boksebeld.pages.beplantingsplan;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import nl.boksebeld.domein.plaats.BeplantingsPlan;

public class MaakNieuwBeplantingsPlan extends AbstractBeplantingsplanUpdater {

	private static final long serialVersionUID = -9152817517464363189L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
	public MaakNieuwBeplantingsPlan() {

		BeplantingsPlan plan = new BeplantingsPlan();
		CompoundPropertyModel<BeplantingsPlan> beplantingsPlanModel = new CompoundPropertyModel<BeplantingsPlan>(plan);
		setDefaultModel(beplantingsPlanModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		// Add a create Contact form to the page
		add(new MaakBeplantingsPlanForm("maakBeplantingsPlanForm", beplantingsPlanModel));

	}

	@Override
	public void werkBeplantingsplanBij(BeplantingsPlan beplantingsPlan) {

		plannenService.saveBeplantingsPlan(beplantingsPlan);

	}

}
