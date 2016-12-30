package nl.boksebeld.pages.klanten;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import nl.boksebeld.domein.klant.Klant;
import nl.boksebeld.domein.service.KlantService;

public class MaakNieuweKlant extends AbstractKlantUpdater {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private KlantService klantService;
	
	
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
	public MaakNieuweKlant() {
		
		Klant klant = new Klant();
		CompoundPropertyModel<Klant> klantModel = new CompoundPropertyModel<Klant>(klant);
		setDefaultModel(klantModel);

		// Create and add feedback panel to page
		add(new FeedbackPanel("feedback"));

		// Add a create Contact form to the page
		add(new MaakKlantForm("maakKlantForm", klantModel));

	}
	@Override
	public void werkKlantenLijstBij(Klant klant) {
		klantService.saveKlant(klant);
		
	}

	
}
