package nl.boksebeld.applicatie.util;

import org.apache.wicket.request.resource.ContextRelativeResource;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public final class Icons {

	private Icons() {
		super();
	}

	/**
	 * Thin delete icon.
	 */
	public static final ContextRelativeResource DELETE = new ContextRelativeResource("mmi/rijk/icons/icon_delete.gif");

	public static final ContextRelativeResource UPDATE = new ContextRelativeResource("mmi/rijk/icons/icon_edit.gif");

}
