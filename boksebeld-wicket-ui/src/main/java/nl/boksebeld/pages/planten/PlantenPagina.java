package nl.boksebeld.pages.planten;

import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.applicatie.util.Icons;
import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;
import nl.boksebeld.domein.service.PlantenService;

public class PlantenPagina extends MasterPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -816564040090186384L;

	@SpringBean
	private PlantenService plantenService;

	private PlantZoekItem plantZoekItem = new PlantZoekItem();
	private CompoundPropertyModel<PlantZoekItem> plantZoekModel = new CompoundPropertyModel<PlantZoekItem>(
			plantZoekItem);

	private List<Plant> plantLijstje = null;

	/**
	 * Page constructor
	 */
	public PlantenPagina() {

		// Add a FeedbackPanel for displaying our messages
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);

		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		add(dataContainer);

		IModel plantLijstModel = new LoadableDetachableModel() {

			@Override
			protected Object load() {
				return getPlantenLijst();

			}
		};

		PageableListView<Plant> listView = createListView(plantLijstModel);
		dataContainer.add(listView);

		PlantenFilterForm maakPlantZoekForm = new PlantenFilterForm("plantZoekForm", plantZoekModel);
		final PagingNavigator pagingNavigator = new PagingNavigator("pageNavigator", listView);
		pagingNavigator.setOutputMarkupId(true);
		// Ajax submit
		maakPlantZoekForm.add(new AjaxFormSubmitBehavior("onsubmit") {
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				plantLijstje = plantenService.getPlantLijst(plantZoekItem);
				target.add(dataContainer);
				target.add(pagingNavigator);
			}
		});

		add(maakPlantZoekForm);

		add(pagingNavigator);

		// Create new Contact link
		add(maakNiewePlantLink("maakNieuwePlantLink"));
		add(maakNiewePlantLink("maakNieuwePlantLinkboven"));

	}

	private Object getPlantenLijst() {
		if (plantLijstje == null) {
			return plantenService.getPlanten();
		}
		return plantLijstje;
	}

	private PageableListView<Plant> createListView(IModel plantLijstModel) {
		return new PageableListView<Plant>("plantLijst", plantLijstModel, 15) {

			@Override
			protected void populateItem(ListItem<Plant> plantItem) {

				final Plant plant = plantItem.getModelObject();

				plantItem.add(new Label("botanischeNaam", plant.getBotanischeNaam()));
				Label bloeitijdLabel = new Label("bloeitijd", plant.getBloeitijdWeergave());
				bloeitijdLabel.setEscapeModelStrings(false);
				plantItem.add(bloeitijdLabel);
				plantItem.add(new Label("bladhoudend", plant.getBladhoudend()));
				plantItem.add(new Label("hoogte", plant.getHoogte()));

				IResource imageResource = new DynamicImageResource() {
					@Override
					protected byte[] getImageData(IResource.Attributes attributes) {
						return plant.getImage();
					}
				};

				plantItem.add(new Image("image", imageResource));

				Image updateImage = getUpdateImage(plant);
				plantItem.add(updateImage);

				Image verwijderImage = getVerwijderImage(plant);
				plantItem.add(verwijderImage);

				// bij doubleclick bewerk plant
				plantItem.add(new AjaxEventBehavior("ondblclick") {
					private static final long serialVersionUID = 8221576966543536298L;

					@Override
					protected void onEvent(final AjaxRequestTarget target) {
						setResponsePage(new BewerkPlant(plant));
					}

				});

				Image copyImage = getCopyImage(plant);
				plantItem.add(copyImage);
			}

		};
	}

	private Image getCopyImage(final Plant plant) {
		Image copyImage = new Image("copyimage", Icons.COPY);
		copyImage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = -7405084475489414877L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				Plant copy = plant.createCopy();
				Plant gesavedePlant = plantenService.savePlant(copy);
				setResponsePage(new BewerkPlant(gesavedePlant));
			}

		});
		return copyImage;

	}

	private Link maakNiewePlantLink(String wickedId) {
		return new Link(wickedId) {
			private static final long serialVersionUID = -9006998536614010390L;

			@Override
			public void onClick() {

				setResponsePage(new MaakNieuwePlant());
			}
		};
	}

	protected Image getVerwijderImage(final Plant plant) {
		Image verwijderimage = new Image("verwijderimage", Icons.DELETE);
		verwijderimage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = 7631329075000639362L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {

				plantenService.deletePlant(plant);
				setResponsePage(new PlantenPagina());

			}

		});
		return verwijderimage;
	}

	private Image getUpdateImage(final Plant plant) {
		Image updateImage = new Image("updateimage", Icons.UPDATE);
		updateImage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = 6548427206677586418L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				setResponsePage(new BewerkPlant(plant));
			}

		});
		return updateImage;
	}

}