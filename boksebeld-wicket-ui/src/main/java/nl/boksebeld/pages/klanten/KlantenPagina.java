package nl.boksebeld.pages.klanten;

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
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.applicatie.util.Icons;
import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.klant.Klant;
import nl.boksebeld.domein.service.KlantService;

public class KlantenPagina extends MasterPage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -816564040090186384L;

	@SpringBean
	private KlantService klantService;

	/**
	 * Page constructor
	 */
	public KlantenPagina() {

		// Add a FeedbackPanel for displaying our messages
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);

		// List all Contacts

		List<Klant> klantLijst = klantService.getKlanten(true);
		final PageableListView<Klant> listView;

		add(listView = new PageableListView<Klant>("klantLijst", klantLijst, 9) {

			@Override
			protected void populateItem(ListItem<Klant> klantItem) {

				final Klant klant = klantItem.getModelObject();

				klantItem.add(new Label("voornaam", klant.getVoornaam()));
				klantItem.add(new Label("achternaam", klant.getAchternaam()));

				// bij doubleclick bewerk klant
				klantItem.add(new AjaxEventBehavior("ondblclick") {

					@Override
					protected void onEvent(final AjaxRequestTarget target) {
						PageParameters pageParameters = new PageParameters();
						pageParameters.set("klantId", klant.getId());

						setResponsePage(BewerkKlant.class, pageParameters);
					}

				});

				Image updateImage = getUpdateImage(klant);
				klantItem.add(updateImage);

				Image verwijderImage = getVerwijderImage(klant);
				klantItem.add(verwijderImage);

			}

			private Image getUpdateImage(final Klant klant) {
				Image updateImage = new Image("updateimage", Icons.UPDATE);
				updateImage.add(new AjaxEventBehavior("onclick") {

					private static final long serialVersionUID = 4785137338131018679L;

					@Override
					protected void onEvent(final AjaxRequestTarget target) {
						PageParameters pageParameters = new PageParameters();
						pageParameters.set("klantId", klant.getId());

						setResponsePage(BewerkKlant.class, pageParameters);
					}

				});
				return updateImage;
			}

			private Image getVerwijderImage(final Klant klant) {
				Image verwijderimage = new Image("verwijderimage", Icons.DELETE);
				verwijderimage.add(new AjaxEventBehavior("onclick") {

					private static final long serialVersionUID = -4479893659810110007L;

					@Override
					protected void onEvent(final AjaxRequestTarget target) {

						klantService.deleteKlant(klant);
						setResponsePage(KlantenPagina.class);

					}

				});
				return verwijderimage;
			}

		});

		add(new PagingNavigator("pageNavigator", listView));

		// Create new Contact link
		add(new Link("maakNieuweKlantLink") {
			@Override
			public void onClick() {

				setResponsePage(MaakNieuweKlant.class);
			}
		});

	}

}