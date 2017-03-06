package nl.boksebeld.pages.klanten;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.klant.Klant;

public abstract class AbstractKlantUpdater extends MasterPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1847922306605479537L;

	@SuppressWarnings("serial")
	public final class MaakKlantForm extends Form<Klant> {

		public MaakKlantForm(final String id, IModel<Klant> klantModel) {
			super(id, klantModel);

			// naw gegevens
			nawGegevens();
			adresGegevens();
			overigeGegevens();
			add(backLink("backLink"));
		}

		private void nawGegevens() {
			TextField<String> voornaam = new TextField<String>("voornaam");
			voornaam.setRequired(true);
			voornaam.add(StringValidator.maximumLength(30));
			add(voornaam);

			TextField<String> achternaam = new TextField<String>("achternaam");
			achternaam.setRequired(true);
			achternaam.add(StringValidator.maximumLength(30));
			add(achternaam);
		}

		private void adresGegevens() {
			TextField<String> straat = new TextField<String>("straat");

			straat.add(StringValidator.maximumLength(30));
			add(straat);

			TextField<String> huisnummer = new TextField<String>("huisnummer");

			huisnummer.add(StringValidator.maximumLength(10));
			add(huisnummer);

			TextField<String> postcode = new TextField<String>("postcode");

			postcode.add(StringValidator.maximumLength(10));
			add(postcode);

			TextField<String> plaats = new TextField<String>("plaats");

			plaats.add(StringValidator.maximumLength(30));
			add(plaats);
		}

		private void overigeGegevens() {
			TextField<String> klantennummer = new TextField<String>("klantennummer");

			klantennummer.add(StringValidator.maximumLength(30));
			add(klantennummer);

			TextField<String> telefoonnummer = new TextField<String>("telefoonnummer");

			telefoonnummer.add(StringValidator.maximumLength(10));
			add(telefoonnummer);

			TextField<String> mailadres = new TextField<String>("mailadres");

			mailadres.add(EmailAddressValidator.getInstance());
			add(mailadres);
		}

		@Override
		public final void onSubmit() {

			final Klant klant = getModelObject();

			werkKlantenLijstBij(klant);

			// Pass success message to next page:
			getSession().info("The klant '" + klant.getVoornaam() + "' was saved!");

			setResponsePage(KlantenPagina.class);

		}

	}

	public abstract void werkKlantenLijstBij(final Klant klant);

	public Link<Void> backLink(final String name) {

		return new Link<Void>(name) {
			/**
			 * @see org.apache.wicket.markup.html.link.Link#onClick()
			 */
			@Override
			public void onClick() {
				setResponsePage(KlantenPagina.class);
			}
		};
	}
}