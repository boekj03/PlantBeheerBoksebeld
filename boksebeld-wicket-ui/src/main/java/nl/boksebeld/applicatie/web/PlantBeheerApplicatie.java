package nl.boksebeld.applicatie.web;

import java.lang.reflect.InvocationTargetException;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.pages.SignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import nl.boksebeld.pages.beplantingsplan.BeplantingsPlannenPagina;
import nl.boksebeld.pages.beplantingsplan.DetailBeplantingsPlan;
import nl.boksebeld.pages.beplantingsplan.MaakNieuwBeplantingsPlan;
import nl.boksebeld.pages.beplantingsplan.MaakNieuwPlantPlaats;
import nl.boksebeld.pages.beplantingsplan.UpdatePlantPlaats;
import nl.boksebeld.pages.klanten.BewerkKlant;
import nl.boksebeld.pages.klanten.KlantenPagina;
import nl.boksebeld.pages.klanten.MaakNieuweKlant;
import nl.boksebeld.pages.planten.BewerkPlant;
import nl.boksebeld.pages.planten.MaakNieuwePlant;
import nl.boksebeld.pages.planten.PlantenPagina;
import nl.boksebeld.pages.planten.zoeken.PlantenSelectie;

public class PlantBeheerApplicatie extends AuthenticatedWebApplication {
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080, 8181)));

		// sprint integratie
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));

		super.init();
		mountPage("/klanten", KlantenPagina.class);
		mountPage("/planten", PlantenPagina.class);
		mountPage("/createKlant", MaakNieuweKlant.class);
		mountPage("/updateKlant", BewerkKlant.class);
		mountPage("/createPlant", MaakNieuwePlant.class);
		mountPage("/bewerkPlant", BewerkPlant.class);

		mountPage("/plantselectie", PlantenSelectie.class);

		mountPage("/plannen", BeplantingsPlannenPagina.class);
		mountPage("/detailplan", DetailBeplantingsPlan.class);

		mountPage("/maaknieuwplan", MaakNieuwBeplantingsPlan.class);
		mountPage("/maaknieuweplaats", MaakNieuwPlantPlaats.class);
		mountPage("/updateplaats", UpdatePlantPlaats.class);

		getRequestCycleListeners().add(new AbstractRequestCycleListener() {

			public IRequestHandler onException(RequestCycle cycle, Exception ex) {
				Throwable cause = ex;
				if (ex instanceof WicketRuntimeException) {
					WicketRuntimeException wre = (WicketRuntimeException) ex;
					cause = wre.getCause();
				}
				if (cause instanceof InvocationTargetException) {
					cause = ((InvocationTargetException) cause).getCause();
				}
				// hier misschien nog een andere exceptie afhandelen
				return null;
			}
		});
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new PlantBeheerSession(request);
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return PlantBeheerSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {

		return KlantenPagina.class;
	}
}