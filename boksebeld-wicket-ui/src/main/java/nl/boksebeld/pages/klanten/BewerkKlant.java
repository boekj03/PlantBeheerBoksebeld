package nl.boksebeld.pages.klanten;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.domein.klant.Klant;
import nl.boksebeld.domein.service.KlantService;

public class BewerkKlant extends AbstractKlantUpdater {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private KlantService klantService;

	public BewerkKlant(PageParameters parameters) {

		Integer klantid = parameters.get("klantId").toInteger();
		Klant klant = klantService.getKlant(klantid);
		CompoundPropertyModel<Klant> klantModel = new CompoundPropertyModel<Klant>(klant);
		setDefaultModel(klantModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		// Add a create Contact form to the page
		add(new MaakKlantForm("maakKlantForm", klantModel));

	}

	@Override
	public void werkKlantenLijstBij(Klant klant) {
		klantService.updateKlant(klant);

	}

}