package nl.boksebeld.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import nl.boksebeld.domein.plant.Bladhoudend;
import nl.boksebeld.domein.plant.Bloeitijd;
import nl.boksebeld.domein.plant.Grondsoort;
import nl.boksebeld.domein.plant.Hoogte;
import nl.boksebeld.domein.plant.Kleur;
import nl.boksebeld.domein.plant.Lichtbehoefte;
import nl.boksebeld.domein.plant.PlantZoekItem;
import nl.boksebeld.domein.plant.Plantsoort;

/**
 * 
 * @author Hans te Boekhorst
 *
 */
public class PlantCriterionLijstCreator {

	private static final String WILDCARD = "%";

	/**
	 * getPlantCriterionLijst.
	 * 
	 * @return List<Criterion>.
	 */
	public List<Criterion> getPlantCriterionLijst(PlantZoekItem plantZoekItem, Criteria criteria) {
		List<Criterion> criteriaLijst = new ArrayList<Criterion>();
		voegCriterionToe(criteriaLijst, getBotanischeNaamCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getCodeCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getNederlandseNaamCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getBeschrijvingCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getHoogteCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getBloeitijdCriterium(plantZoekItem, criteria));
		voegCriterionToe(criteriaLijst, getGrondsoortCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getKleurCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getPlantsoortCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getLichtbehoefteCriterium(plantZoekItem));
		voegCriterionToe(criteriaLijst, getBladhoudendCriterium(plantZoekItem));
		return criteriaLijst;
	}

	private static void voegCriterionToe(List<Criterion> criteriaLijst, Criterion toeTevoegenCriterion) {
		if (toeTevoegenCriterion != null) {
			criteriaLijst.add(toeTevoegenCriterion);
		}
	}

	public Criterion getBotanischeNaamCriterium(PlantZoekItem plantZoekItem) {
		Criterion toeTeVoegenCriterium = null;
		if (plantZoekItem.getBotanischeNaam() != null) {
			toeTeVoegenCriterium = Restrictions.like("botanischeNaam",
					WILDCARD + plantZoekItem.getBotanischeNaam() + WILDCARD);
		}
		return toeTeVoegenCriterium;
	}

	public Criterion getBeschrijvingCriterium(PlantZoekItem plantZoekItem) {
		Criterion toeTeVoegenCriterium = null;
		if (plantZoekItem.getBeschrijving() != null) {
			toeTeVoegenCriterium = Restrictions.like("beschrijving",
					WILDCARD + plantZoekItem.getBeschrijving() + WILDCARD);
		}
		return toeTeVoegenCriterium;
	}

	public Criterion getCodeCriterium(PlantZoekItem plantZoekItem) {
		Criterion toeTeVoegenCriterium = null;
		if (plantZoekItem.getCode() != null) {
			toeTeVoegenCriterium = Restrictions.like("code", WILDCARD + plantZoekItem.getCode() + WILDCARD);
		}
		return toeTeVoegenCriterium;
	}

	public Criterion getNederlandseNaamCriterium(PlantZoekItem plantZoekItem) {
		Criterion toeTeVoegenCriterium = null;
		if (plantZoekItem.getNederlandseNaam() != null) {
			toeTeVoegenCriterium = Restrictions.like("nederlandseNaam",
					WILDCARD + plantZoekItem.getNederlandseNaam() + WILDCARD);
		}
		return toeTeVoegenCriterium;
	}

	private Criterion getHoogteCriterium(PlantZoekItem plantZoekItem) {

		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getHoogteLijst().size();
		if (size == 0) {
			return null;
		}
		Criterion[] toeTeVoegenLijst = new Criterion[size];
		for (int i = 0; i < size; i++) {
			Hoogte hoogte = plantZoekItem.getHoogteLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("hoogte", hoogte);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);
		return toeTeVoegenCriterium;
	}

	private Criterion getBladhoudendCriterium(PlantZoekItem plantZoekItem) {
		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getBladhoudendLijst().size();
		if (size == 0) {
			return null;
		}
		Criterion[] toeTeVoegenLijst = new Criterion[size];
		for (int i = 0; i < size; i++) {
			Bladhoudend bladhoudend = plantZoekItem.getBladhoudendLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("bladhoudend", bladhoudend);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);
		return toeTeVoegenCriterium;
	}

	private Criterion getBloeitijdCriterium(PlantZoekItem plantZoekItem, Criteria criteria) {

		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getBloeitijdLijst().size();
		if (size == 0) {
			return null;
		}
		// dit werkt vraag niet aan hans waarom
		Criteria createAlias = criteria.createAlias("bloeitijdLijst", "bloeitijdLijstAlias", JoinType.INNER_JOIN);
		Criterion[] toeTeVoegenLijst = new Criterion[size];

		for (int i = 0; i < size; i++) {
			Bloeitijd bloeitijd = plantZoekItem.getBloeitijdLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("bloeitijdLijstAlias.elements", bloeitijd);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);

		return toeTeVoegenCriterium;
	}

	private Criterion getGrondsoortCriterium(PlantZoekItem plantZoekItem) {

		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getGrondsoortLijst().size();
		if (size == 0) {
			return null;
		}
		Criterion[] toeTeVoegenLijst = new Criterion[size];
		for (int i = 0; i < size; i++) {
			Grondsoort grondsoort = plantZoekItem.getGrondsoortLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("grondsoort", grondsoort);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);
		return toeTeVoegenCriterium;
	}

	private Criterion getKleurCriterium(PlantZoekItem plantZoekItem) {

		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getKleurLijst().size();
		if (size == 0) {
			return null;
		}
		Criterion[] toeTeVoegenLijst = new Criterion[size];
		for (int i = 0; i < size; i++) {
			Kleur kleur = plantZoekItem.getKleurLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("kleur", kleur);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);
		return toeTeVoegenCriterium;
	}

	private Criterion getPlantsoortCriterium(PlantZoekItem plantZoekItem) {

		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getPlantsoortLijst().size();
		if (size == 0) {
			return null;
		}
		Criterion[] toeTeVoegenLijst = new Criterion[size];
		for (int i = 0; i < size; i++) {
			Plantsoort plantsoort = plantZoekItem.getPlantsoortLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("plantsoort", plantsoort);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);
		return toeTeVoegenCriterium;
	}

	private Criterion getLichtbehoefteCriterium(PlantZoekItem plantZoekItem) {

		Criterion toeTeVoegenCriterium = null;
		int size = plantZoekItem.getLichtbehoefteLijst().size();
		if (size == 0) {
			return null;
		}
		Criterion[] toeTeVoegenLijst = new Criterion[size];
		for (int i = 0; i < size; i++) {
			Lichtbehoefte lichtbehoefte = plantZoekItem.getLichtbehoefteLijst().get(i);
			toeTeVoegenLijst[i] = Restrictions.eq("lichtbehoefte", lichtbehoefte);

		}
		toeTeVoegenCriterium = Restrictions.or(toeTeVoegenLijst);
		return toeTeVoegenCriterium;
	}
}
