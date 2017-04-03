package nl.boksebeld.pages.beplantingsplan;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import nl.boksebeld.domein.plaats.BeplantingsPlan;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class BewerkBeplantingsplan extends AbstractBeplantingsplanUpdater {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6268212889096647660L;

	public BewerkBeplantingsplan(BeplantingsPlan plan) {
		CompoundPropertyModel<BeplantingsPlan> beplantingsPlanModel = new CompoundPropertyModel<BeplantingsPlan>(plan);
		setDefaultModel(beplantingsPlanModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		// Add a create Contact form to the page
		add(new MaakBeplantingsPlanForm("maakBeplantingsPlanForm", beplantingsPlanModel));
	}

	@Override
	public void werkBeplantingsplanBij(BeplantingsPlan beplantingsPlan) {
		plannenService.updateBeplantingsPlan(beplantingsPlan);
	}
}
