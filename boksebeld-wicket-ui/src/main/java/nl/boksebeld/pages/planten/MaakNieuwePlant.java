package nl.boksebeld.pages.planten;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.service.PlantenService;

public class MaakNieuwePlant extends AbstractPlantUpdater {
	private static final long serialVersionUID = 1L;
		
	@SpringBean
	private PlantenService plantenService;
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
	public MaakNieuwePlant() {
		
		Plant plant = new Plant();
		CompoundPropertyModel<Plant> plantModel = new CompoundPropertyModel<Plant>(plant);
		setDefaultModel(plantModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		// Add a create Contact form to the page
		add(new MaakPlantForm("maakPlantForm", plantModel));
		
	}
	@Override
	public void werkPlantenLijstBij(Plant plant) {
		plantenService.savePlant(plant);
		
	}
	
}
