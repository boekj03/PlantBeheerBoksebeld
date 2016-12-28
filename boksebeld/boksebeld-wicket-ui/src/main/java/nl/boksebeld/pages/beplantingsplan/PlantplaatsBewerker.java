package nl.boksebeld.pages.beplantingsplan;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import nl.boksebeld.applicatie.util.Constanten;
import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;
import nl.boksebeld.domein.service.PlannenService;
import nl.boksebeld.domein.service.PlantenService;
import nl.boksebeld.pages.planten.zoeken.PlantContainer;
import nl.boksebeld.pages.planten.zoeken.PlantZoekForm;
import nl.boksebeld.pages.planten.zoeken.SelectPlantTabel;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public abstract class PlantplaatsBewerker extends MasterPage {
	private static final long serialVersionUID = -5089148965125528192L;

	@SpringBean
	private PlannenService plannenService;
	@SpringBean
	private PlantenService plantenService;

	private BeplantingsPlan beplantingsPlan;
	private List<Plant> actuelePlantLijst = new ArrayList<Plant>();
	private final List<PlantContainer> plantSelectContainerLijst = new ArrayList<PlantContainer>();

	private MaakKiesPlantForm kiezenPlantFrom;
	private PlantZoekForm plantZoekForm;
	private Model<String> plantnaammessage;

	protected void initBewerker(PlantPlaats plaats) {
		// uit de sessie halen

		CompoundPropertyModel<PlantPlaats> plantPlaatsModel = new CompoundPropertyModel<PlantPlaats>(plaats);
		setDefaultModel(plantPlaatsModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		MaakPlantPlaatsForm plantPlaatsForm = new MaakPlantPlaatsForm("maakPlantPlaatsForm", plantPlaatsModel);
		add(plantPlaatsForm);

	}

	@SuppressWarnings("serial")
	public final class MaakPlantPlaatsForm extends Form<PlantPlaats> {

		public MaakPlantPlaatsForm(final String id, final IModel<PlantPlaats> plantPlaatsModel) {
			super(id, plantPlaatsModel);

			plantPlaatsGegevens(plantPlaatsModel);

			final CompoundPropertyModel<PlantZoekItem> plantZoekModel = new CompoundPropertyModel<PlantZoekItem>(
					getPlantzoekItem());

			// hier binnen deze form ook nog een lijst me de resultaten vona de
			// plantzoekform

			kiezenPlantFrom = new MaakKiesPlantForm("kiesplantForm", plantPlaatsModel);
			kiezenPlantFrom.setVisible(false);
			add(kiezenPlantFrom);

			// hier voer ik een zoek form toe
			plantZoekForm = new PlantZoekForm("maakPlantZoekForm", plantZoekModel);
			// plantZoekForm.setVisible(false);
			// de zoekform heeft een submit button
			plantZoekForm.add(new Button("submitzoek") {

				@Override
				public void onSubmit() {
					final PlantZoekItem plantZoekItem = plantZoekModel.getObject();
					actuelePlantLijst.clear();
					actuelePlantLijst = plantenService.getPlantLijst(plantZoekItem);
					werkPlantSelectContainerLijstBij();
					kiezenPlantFrom.setVisible(true);
				}
			});

			add(plantZoekForm);
			add(backLink("backLink"));
		}

		private void plantPlaatsGegevens(IModel<PlantPlaats> plantPlaatsModel) {
			TextField<String> naam = new TextField<String>("naam");
			naam.setRequired(true);
			naam.add(StringValidator.maximumLength(30));
			add(naam);

			TextField<Float> vierkantemeters = new TextField<Float>("vierkanteMeters");
			vierkantemeters.setRequired(true);
			add(vierkantemeters);

			if (null != plantPlaatsModel.getObject().getPlant()) {
				plantnaammessage = Model.of(plantPlaatsModel.getObject().getPlant().getNederlandseNaam());
			} else {
				plantnaammessage = Model.of("");
			}

			Label nederlandsenaam = new Label("plant.nederlandseNaam", plantnaammessage);
			nederlandsenaam.setOutputMarkupId(true);
			add(nederlandsenaam);
		}

		@Override
		public final void onSubmit() {

			final PlantPlaats plantPlaats = getModelObject();

			beplantingsPlan.addPlantPlaats(plantPlaats);
			// Pass success message to next page:
			BeplantingsPlan updatedBeplantingsPlan = plannenService.updateBeplantingsPlan(beplantingsPlan);
			getSession().info("Het plan '" + beplantingsPlan.getNaam() + "' was saved!");

			setResponsePage(new DetailBeplantingsPlan(updatedBeplantingsPlan));

		}
	}

	@SuppressWarnings("serial")
	public final class MaakKiesPlantForm extends Form<PlantPlaats> {
		private SelectPlantTabel selectPlantTabel;

		public MaakKiesPlantForm(final String id, final IModel<PlantPlaats> plantPlaatsModel) {
			super(id, plantPlaatsModel);
			selectPlantTabel = new SelectPlantTabel("plantContainerLijst", plantSelectContainerLijst);
			add(new Button("submitplantform") {

				@Override
				public void onSubmit() {
					int count = 0;
					Plant geselecteerdePlant = null;
					for (Plant plant : actuelePlantLijst) {
						if (plant.isSelected()) {
							count++;
							geselecteerdePlant = plant;
						}
					}
					if (count == 0) {
						getSession().error("Selecteer een plant!!");
					} else if (count > 1) {
						getSession().error("Te veel items geselecteerd selecteer er 1!!");
					} else {
						getSession().info("De gekozen plant is " + geselecteerdePlant.getNederlandseNaam());
						plantPlaatsModel.getObject().setPlant(geselecteerdePlant);
						getKiezenPlantFrom().setVisible(false);

						plantnaammessage.setObject(geselecteerdePlant.getNederlandseNaam());

					}
				}
			});

			add(selectPlantTabel);
		}
	}

	protected void setBeplantingsPlan(BeplantingsPlan beplantingsPlan) {
		this.beplantingsPlan = beplantingsPlan;
	}

	public Link<Void> backLink(final String name) {

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
				setResponsePage(new DetailBeplantingsPlan(beplantingsPlan));
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

	private void werkPlantSelectContainerLijstBij() {
		this.plantSelectContainerLijst.clear();
		for (int i = 0; i < actuelePlantLijst.size(); i++) {
			int k = 1 + i;
			int remainder = (k % 3);
			if (remainder == 1) {
				plantSelectContainerLijst.add(new PlantContainer());
				PlantContainer plantContainer = plantSelectContainerLijst.get(plantSelectContainerLijst.size() - 1);
				plantContainer.setPlantEen(actuelePlantLijst.get(i));
			}
			if (remainder == 2) {
				PlantContainer plantContainer = plantSelectContainerLijst.get(plantSelectContainerLijst.size() - 1);
				plantContainer.setPlantTwee(actuelePlantLijst.get(i));
			}
			if (remainder == 0) {
				PlantContainer plantContainer = plantSelectContainerLijst.get(plantSelectContainerLijst.size() - 1);
				plantContainer.setPlantDrie(actuelePlantLijst.get(i));
			}
		}
	}

	private MaakKiesPlantForm getKiezenPlantFrom() {
		return kiezenPlantFrom;
	}

}
