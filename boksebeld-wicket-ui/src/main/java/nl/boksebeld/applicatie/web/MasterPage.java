package nl.boksebeld.applicatie.web;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
@AuthorizeInstantiation("ADMIN")
public class MasterPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589449156897321282L;

	/**
	 * Constructor.
	 */
	public MasterPage() {
	}

}
