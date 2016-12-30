package nl.boksebeld.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.service.ServiceRegistry;

import nl.boksebeld.domein.User;
import nl.boksebeld.domein.klant.Klant;
import nl.boksebeld.domein.plaats.BeplantingsPlan;
import nl.boksebeld.domein.plant.Plant;
import nl.boksebeld.domein.plant.PlantZoekItem;

/**
 * Created by Florian Salihovic & Aydin Seven on 17/04/14.
 */
public class HibernateUtil {

	private static final Configuration configuration = new Configuration();
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * updates a Contact in the database
	 */
	public static void update(Object object) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.update(object);
			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
	}

	/**
	 * loads a Contact from the database
	 */
	public static Object get(Class clazz, int id) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Object object = session.get(clazz, id);
			transaction.commit();
			return object;
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
		return null;
	}

	/**
	 * saves a new Contact in the database
	 */
	public static Boolean save(Object object) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.save(object);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // TODO: log to file instead
			return false;
		} finally {
			session.close();
		}
	}

	public static List getList(Class clazz) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List list = null;
		try {
			transaction = session.beginTransaction();
			list = session.createQuery("from " + clazz.getName()).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
		return list;
	}

	public static List<Klant> getKlantenList(boolean sorteerAsc) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List list = null;
		try {
			transaction = session.beginTransaction();
			String sortOrder = "asc";
			if (!sorteerAsc) {
				sortOrder = "desc";
			}

			list = session.createQuery("from Klant k order by k.achternaam " + sortOrder).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
		return list;
	}

	public static List<Plant> getPlantenList(boolean sorteerAsc) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List list = null;
		try {
			transaction = session.beginTransaction();
			String sortOrder = "asc";
			if (!sorteerAsc) {
				sortOrder = "desc";
			}

			list = session.createQuery("from Plant p order by p.botanischeNaam " + sortOrder).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * 
	 * @param sorteerAsc
	 * @return
	 */
	public static List<BeplantingsPlan> getPlannenList(boolean sorteerAsc) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List list = null;
		try {
			transaction = session.beginTransaction();
			String sortOrder = "asc";
			if (!sorteerAsc) {
				sortOrder = "desc";
			}

			list = session.createQuery("from BeplantingsPlan bp order by bp.naam " + sortOrder).list();
			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
		return list;
	}

	public static void delete(Object o) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(o);
			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
	}

	public static User getUserByUsername(String username) {

		final Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		User user = new User();
		List<User> list = null;
		try {
			transaction = session.beginTransaction();
			list = session.createQuery("from User where username = '" + username + "' ").list();
			if (list.isEmpty()) {
				user = null;
			} else {
				user = list.get(0);
			}

			transaction.commit();
		} catch (HibernateException e) {
			if (null != transaction) {
				transaction.rollback();
			}
			e.printStackTrace(); // todo: log to file instead
		} finally {
			session.close();
		}
		return user;
	}

	public static List<Plant> getPlantLijst(PlantZoekItem plantZoekItem) {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		PlantCriterionLijstCreator criterionLijstCreator = new PlantCriterionLijstCreator();
		List<Criterion> criteriaLijst = criterionLijstCreator.getPlantCriterionLijst(plantZoekItem);

		Criteria criteria = session.createCriteria(Plant.class);
		for (Criterion criterion : criteriaLijst) {
			criteria.add(criterion);
		}
		criteria.addOrder(Order.asc("botanischeNaam"));

		return criteria.list();

	}

}
