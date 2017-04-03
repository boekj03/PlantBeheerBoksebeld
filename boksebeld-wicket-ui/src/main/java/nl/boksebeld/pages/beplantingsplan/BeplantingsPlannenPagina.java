package nl.boksebeld.pages.beplantingsplan;

import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.applicatie.util.Icons;
import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.service.PlannenService;

public class BeplantingsPlannenPagina extends MasterPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1997352809506653887L;

	@SpringBean
	private PlannenService plannenService;

	public BeplantingsPlannenPagina() {

		// Add a FeedbackPanel for displaying our messages
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);

		// List all Contacts

		List<BeplantingsPlan> beplantingsPlannenLijst = plannenService.getBeplantingsPlannen();

		final PageableListView<BeplantingsPlan> listView;

		add(listView = new PageableListView<BeplantingsPlan>("beplantingsPlannenLijst", beplantingsPlannenLijst, 9) {
			@Override
			protected void populateItem(ListItem<BeplantingsPlan> planItem) {

				final BeplantingsPlan plan = planItem.getModelObject();

				planItem.add(new Label("naam", plan.getNaam()));

				Image updateImage = getUpdateImage(plan);
				planItem.add(updateImage);

				Image renameImage = getRenameImage(plan);
				planItem.add(renameImage);

				Image verwijderImage = getVerwijderImage(plan);
				planItem.add(verwijderImage);

				Image copyImage = getCopyImage(plan);
				planItem.add(copyImage);

				// bij doubleclick bewerk plan
				planItem.add(new AjaxEventBehavior("ondblclick") {

					@Override
					protected void onEvent(final AjaxRequestTarget target) {
						setResponsePage(new DetailBeplantingsPlan(plan));
					}
				});
			}

		});

		listView.setOutputMarkupId(true);
		add(new PagingNavigator("pageNavigator", listView));

		// Create new Contact link
		add(new Link("maakNieuwePlanLink") {
			@Override
			public void onClick() {

				setResponsePage(new MaakNieuwBeplantingsPlan());
			}
		});
	}

	private Image getUpdateImage(final BeplantingsPlan plan) {
		Image updateImage = new Image("updateimage", Icons.UPDATE);
		updateImage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = -7405084475489414877L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				setResponsePage(new DetailBeplantingsPlan(plan));
			}

		});
		return updateImage;
	}

	private Image getCopyImage(final BeplantingsPlan plan) {
		Image copyImage = new Image("copyimage", Icons.COPY);
		copyImage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = -7405084475489414877L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				BeplantingsPlan copy = plan.createCopy();
				plannenService.saveBeplantingsPlan(copy);
				setResponsePage(new BewerkBeplantingsplan(copy));
			}

		});
		return copyImage;
	}

	private Image getRenameImage(final BeplantingsPlan plan) {
		Image renameImage = new Image("renameimage", Icons.RENAME);
		renameImage.add(new AjaxEventBehavior("onclick") {

			private static final long serialVersionUID = -7405084475489414877L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {
				setResponsePage(new BewerkBeplantingsplan(plan));
			}

		});
		return renameImage;
	}

	private Image getVerwijderImage(final BeplantingsPlan plan) {
		Image verwijderimage = new Image("verwijderimage", Icons.DELETE);
		verwijderimage.add(new AjaxEventBehavior("onclick") {
			private static final long serialVersionUID = -7155152525037472421L;

			@Override
			protected void onEvent(final AjaxRequestTarget target) {

				plannenService.deleteBeplantingsPlan(plan);
				setResponsePage(new BeplantingsPlannenPagina());

			}

		});
		return verwijderimage;
	}

}
