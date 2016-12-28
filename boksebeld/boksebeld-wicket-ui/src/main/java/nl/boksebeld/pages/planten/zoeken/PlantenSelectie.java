package nl.boksebeld.pages.planten.zoeken;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.applicatie.util.Constanten;
import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;
import nl.boksebeld.domein.plant.PlantZoekResultaatContainer;
import nl.boksebeld.domein.service.PlannenService;
import nl.boksebeld.domein.service.PlantenService;
import nl.boksebeld.pages.beplantingsplan.DetailBeplantingsPlan;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class PlantenSelectie extends MasterPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1260447704663539594L;
	/**
	 * 
	 * 
	 */
	private List<Plant> actuelePlantLijst = new ArrayList<Plant>();
	private List<PlantContainer> plantContainerLijst = new ArrayList<PlantContainer>();

	@SpringBean
	private PlantenService plantenService;
	@SpringBean
	private PlannenService plannenService;

	public PlantenSelectie(BeplantingsPlan beplantingsPlan) {

		PlantZoekItem plantZoekItem = getPlantzoekItem();

		CompoundPropertyModel<PlantZoekItem> plantZoekModel = new CompoundPropertyModel<PlantZoekItem>(plantZoekItem);
		setDefaultModel(plantZoekModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		add(maakPlantZoekForm(plantZoekModel));

		// Add a create Contact form to the page
		CompoundPropertyModel<BeplantingsPlan> beplantingsPlanModel = new CompoundPropertyModel<BeplantingsPlan>(
				beplantingsPlan);

		add(new MaakKiesPlantForm("kiesplantForm", beplantingsPlanModel));
	}

	private PlantZoekForm maakPlantZoekForm(CompoundPropertyModel<PlantZoekItem> plantZoekModel) {
		return new PlantZoekForm("plantZoekForm", plantZoekModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2201812589243329140L;

			@Override
			public final void onSubmit() {

				final PlantZoekItem plantZoekItem = getModelObject();
				actuelePlantLijst.clear();
				actuelePlantLijst = plantenService.getPlantLijst(plantZoekItem);
				getPlantContainerLijst();
				// Pass success message to next page:
				getSession().info("Er zijn '" + actuelePlantLijst.size() + "' planten gevonden!");

				getSession().setAttribute(Constanten.PLANTZOEKITEM, plantZoekItem);

				PlantZoekResultaatContainer pzrContainer = new PlantZoekResultaatContainer();
				pzrContainer.setPlantLijstje(actuelePlantLijst);
				getSession().setAttribute(Constanten.PLANTZOEKRESULTAAT, pzrContainer);
			}

		};
	}

	@SuppressWarnings("serial")
	public final class MaakKiesPlantForm extends Form<BeplantingsPlan> {
		private SelectPlantTabel selectPlantTabel;

		public MaakKiesPlantForm(final String id, IModel<BeplantingsPlan> beplantingsPlanModel) {
			super(id, beplantingsPlanModel);
			selectPlantTabel = new SelectPlantTabel("plantContainerLijst", getPlantContainerLijst());

			add(selectPlantTabel);
			add(backLink("backLink", getModelObject()));

		}

		@Override
		public final void onSubmit() {
			// het onderhanden plan
			BeplantingsPlan beplantingsPlan = getModelObject();

			for (Plant plant : actuelePlantLijst) {
				if (plant.isSelected()) {
					PlantPlaats plaats = new PlantPlaats();
					plaats.setNaam(plant.getCode());
					plaats.setVierkanteMeters(1);
					plaats.setPlant(plant);
					beplantingsPlan.addPlantPlaats(plaats);
				}
			}

			plannenService.updateBeplantingsPlan(beplantingsPlan);
			setResponsePage(new DetailBeplantingsPlan(beplantingsPlan));
		}

	}

	public Link<Void> backLink(final String name, final BeplantingsPlan plan) {

		return new Link<Void>(name) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6292030475522230791L;

			/**
			 * @see org.apache.wicket.markup.html.link.Link#onClick()
			 */
			@Override
			public void onClick() {
				setResponsePage(new DetailBeplantingsPlan(plan));
			}
		};
	}

	/**
	 * Haal het item van de sesie.
	 * 
	 * @return
	 */
	private PlantZoekItem getPlantzoekItem() {
		PlantZoekItem sesiePlantZoekItem = (PlantZoekItem) Session.get().getAttribute(Constanten.PLANTZOEKITEM);
		if (null != sesiePlantZoekItem) {
			return sesiePlantZoekItem;
		}
		return new PlantZoekItem();
	}

	private List<PlantContainer> getPlantContainerLijst() {
		plantContainerLijst.clear();
		for (int i = 0; i < actuelePlantLijst.size(); i++) {
			int k = 1 + i;
			int remainder = (k % 3);
			if (remainder == 1) {
				plantContainerLijst.add(new PlantContainer());
				PlantContainer plantContainer = getLaatste(plantContainerLijst);
				plantContainer.setPlantEen(actuelePlantLijst.get(i));
			}
			if (remainder == 2) {
				PlantContainer plantContainer = getLaatste(plantContainerLijst);
				plantContainer.setPlantTwee(actuelePlantLijst.get(i));
			}
			if (remainder == 0) {
				PlantContainer plantContainer = getLaatste(plantContainerLijst);
				plantContainer.setPlantDrie(actuelePlantLijst.get(i));
			}
		}
		return plantContainerLijst;
	}

	private PlantContainer getLaatste(List<PlantContainer> retVal) {
		PlantContainer plantContainer = retVal.get(retVal.size() - 1);
		return plantContainer;
	}

}