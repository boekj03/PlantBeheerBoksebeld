package nl.boksebeld.applicatie.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import nl.boksebeld.domein.plant.Kleur;

public class KleurenConstant {

	private static Map<Kleur, String> kleurLijst = new HashMap<Kleur, String>();

	static {
		kleurLijst.put(Kleur.WIT, "bkg-wit");
		kleurLijst.put(Kleur.BLAUW, "bkg-blauw");
		kleurLijst.put(Kleur.PAARS, "bkg-paars");
		kleurLijst.put(Kleur.VIOLET, "bkg-violet");
		kleurLijst.put(Kleur.ROOD, "bkg-rood");
		kleurLijst.put(Kleur.GEEL, "bkg-geel");
		kleurLijst.put(Kleur.ORANJE, "bkg-oranje");
		kleurLijst.put(Kleur.ROZE, "bkg-roze");

	}

	public static IModel<String> getKleur(Kleur kleur) {
		if (kleur == null) {
			return Model.of("bkg-default");
		}
		IModel<String> cssModel = Model.of(kleurLijst.get(kleur));

		return cssModel;
	}

}
