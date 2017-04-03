package nl.boksebeld.pages.beplantingsplan;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import nl.boksebeld.applicatie.web.MasterPage;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.service.PlannenService;

public abstract class AbstractBeplantingsplanUpdater extends MasterPage {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1671109154042920576L;

	@SpringBean
	protected PlannenService plannenService;

	@SuppressWarnings("serial")
	public final class MaakBeplantingsPlanForm extends Form<BeplantingsPlan> {

		public MaakBeplantingsPlanForm(final String id, IModel<BeplantingsPlan> beplantingsPlanModel) {
			super(id, beplantingsPlanModel);

			// naw gegevens
			planNaamGegevens();

			add(backLink("backLink"));
		}

		private void planNaamGegevens() {
			TextField<String> naam = new TextField<String>("naam");
			naam.setRequired(true);
			naam.add(StringValidator.maximumLength(30));
			add(naam);

		}

		@Override
		public final void onSubmit() {

			final BeplantingsPlan beplantingsPlan = getModelObject();

			// Pass success message to next page:
			werkBeplantingsplanBij(beplantingsPlan);
			getSession().info("Het plan '" + beplantingsPlan.getNaam() + "' was saved!");

			setResponsePage(new BeplantingsPlannenPagina());

		}

	}

	public abstract void werkBeplantingsplanBij(final BeplantingsPlan beplantingsPlan);

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
				setResponsePage(new BeplantingsPlannenPagina());
			}
		};
	}

}
