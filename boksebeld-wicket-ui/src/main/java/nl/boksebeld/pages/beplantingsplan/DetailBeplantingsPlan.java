package nl.boksebeld.pages.beplantingsplan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

import nl.boksebeld.applicatie.excel.BeplantingsPlanToExcel;
import nl.boksebeld.applicatie.util.Icons;
import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plaats.PlantPlaats;
import nl.boksebeld.domein.service.PlannenService;
import nl.boksebeld.pages.planten.zoeken.PlantenSelectie;

public class DetailBeplantingsPlan extends MasterPage {

	private static final long serialVersionUID = 3233284220087569916L;
	@SpringBean
	private PlannenService plannenService;

	public DetailBeplantingsPlan(final BeplantingsPlan plan) {
		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));
		// de plan naam.
		add(new Label("plannaam", plan.getNaam()));

		List<PlantPlaats> plantPlaatsLijst = new ArrayList<PlantPlaats>(plan.getPlantPlaatsLijst());
		final PageableListView<PlantPlaats> listView;

		add(listView = new PageableListView<PlantPlaats>("plantPlaatsLijst", plantPlaatsLijst, 9) {

			@Override
			protected void populateItem(final ListItem<PlantPlaats> plantPlaatsItem) {
				final PlantPlaats plantPlaats = plantPlaatsItem.getModelObject();

				plantPlaatsItem.add(new Label("naam", plantPlaats.getNaam()));
				plantPlaatsItem.add(new Label("vierkantemeters", plantPlaats.getVierkanteMeters()));

				if (null != plantPlaats.getPlant()) {
					plantPlaatsItem
							.add(new Label("plant.nederlandseNaam", plantPlaats.getPlant().getNederlandseNaam()));
				} else {
					plantPlaatsItem.add(new Label("plant.nederlandseNaam"));
				}
				plantPlaatsItem.add(getUpdatePlantPlaatsBehavior(plantPlaats));

				Image updateImage = getUpdateImage(plantPlaats);
				plantPlaatsItem.add(updateImage);

				Image verwijderImage = getVerwijderImage(plantPlaats);
				plantPlaatsItem.add(verwijderImage);

				plantPlaatsItem.setOutputMarkupId(true);
			}

		});

		listView.setOutputMarkupId(true);

		add(new PagingNavigator("pageNavigator", listView));

		// Create new Contact link
		add(new Link("maakNieuwePlantPlaatsLink") {

			@Override
			public void onClick() {
				setResponsePage(new MaakNieuwPlantPlaats(plan));
			}

		});
		add(addPlantenLink("addplanten", plan));

		IModel<File> fileModel = new AbstractReadOnlyModel<File>() {
			private static final long serialVersionUID = -7869743404096248857L;

			@Override
			public File getObject() {
				return BeplantingsPlanToExcel.plantToExcel(plan);
			}
		};

		DownloadLink dynamicDownloadlink = new DownloadLink("excellink", fileModel) {

			private static final long serialVersionUID = 6751445861368618721L;

			@Override
			public void onClick() {

				File file = (File) getModelObject();
				IResourceStream resourceStream = new FileResourceStream(new org.apache.wicket.util.file.File(file));

				getRequestCycle().scheduleRequestHandlerAfterCurrent(
						new ResourceStreamRequestHandler(resourceStream, file.getName()));
			}
		};

		add(dynamicDownloadlink);

	}

	public Link<Void> addPlantenLink(String name, final BeplantingsPlan plan) {
		return new Link<Void>(name) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1175962863080219481L;

			/**
			 * @see org.apache.wicket.markup.html.link.Link#onClick()
			 */
			@Override
			public void onClick() {
				setResponsePage(new PlantenSelectie(plan));
			}
		};
	}

	private AjaxEventBehavior getUpdatePlantPlaatsBehavior(final PlantPlaats plantPlaats) {
		AjaxEventBehavior behavior = new AjaxEventBehavior("ondblclick") {

			@Override

			protected void onEvent(final AjaxRequestTarget target) {

				setResponsePage(new UpdatePlantPlaats(plantPlaats));
			}

		};
		return behavior;

	}

	private Image getVerwijderImage(final PlantPlaats plantplaats) {
		Image verwijderimage = new Image("verwijderimage", Icons.DELETE);
		verwijderimage.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = 1944953946500280433L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				BeplantingsPlan beplantingsPlan = plantplaats.getBeplantingsPlan();
				beplantingsPlan.getPlantPlaatsLijst().remove(plantplaats);
				BeplantingsPlan updatedBeplantingsPlan = plannenService.updateBeplantingsPlan(beplantingsPlan);
				setResponsePage(new DetailBeplantingsPlan(updatedBeplantingsPlan));
			}

		});
		return verwijderimage;
	}

	private Image getUpdateImage(final PlantPlaats plantplaats) {
		Image updateImage = new Image("updateimage", Icons.UPDATE);
		updateImage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = 1944953946500280433L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				setResponsePage(new UpdatePlantPlaats(plantplaats));
			}

		});
		return updateImage;
	}

}
