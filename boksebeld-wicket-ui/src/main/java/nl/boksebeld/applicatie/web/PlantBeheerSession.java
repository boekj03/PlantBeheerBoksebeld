package nl.boksebeld.applicatie.web;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class PlantBeheerSession extends AuthenticatedWebSession {

	private boolean ingelogd = false;

	private static final long serialVersionUID = 9054479928602918846L;

	public PlantBeheerSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {
		if ("boksw00".equals(username) && "WB1970Hol3".equals(password)) {
			ingelogd = true;
			return true;
		}
		return false;
	}

	@Override
	public Roles getRoles() {
		if (ingelogd) {
			return new Roles(Roles.ADMIN);
		}
		return null;
	}
}
