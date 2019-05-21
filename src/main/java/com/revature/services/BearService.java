package com.revature.services;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;

import com.revature.entities.Bear;
import com.revature.util.HibernateUtil;

public class BearService {

	Logger logger = Logger.getRootLogger();

	public List<Bear> getAllBears() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method fulfills JPA standard for merge
	 * @param bear
	 * @return
	 */
	public Bear mergeBear(Bear bear) {
		logger.warn("Updating bear: " + bear.toString());
		Bear retrievedBear = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			retrievedBear = session.get(Bear.class, bear.getId());
			logger.warn("Bears are same: " + (bear == retrievedBear));
			bear = (Bear) session.merge(bear);
			bear.setFavoriteFood("Pizza");
			logger.warn("Bears are same: " + (bear == retrievedBear));
			tx.commit();
		}
		return retrievedBear;
	}

	public Bear updateBear(Bear bear) {
		logger.warn("Updating bear: " + bear.toString());

		SessionFactory sf = HibernateUtil.getSessionFactory();
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
//			Bear retrievedBear = session.get(Bear.class, bear.getId());

			session.update(bear);
//			bear.setFavoriteFood("Ice Cream");
			tx.commit();
		}
		return bear;
	}

	public Bear getBearById(int id) {
		logger.warn("Getting bear by id: " + id);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Bear bear = null;
		try (Session session = sf.openSession()) {
			bear = session.get(Bear.class, id);
		}
		return bear;
	}

	/**
	 * the Session.load method will generally return a proxy object. A proxy object
	 * is a special object managed by Hibernate that stands in place of the actual
	 * object. A proxy is not initialized until it is used.
	 * 
	 * Exceptions associated with proxies: * LazyInitializationException - Is thrown
	 * when a proxy attempts to initialize itself after the session that is
	 * associated with it has closed. * ObjectNotFoundException - A proxy attempts
	 * to initialize itself to find that it doesn't exist. Oh no!
	 * 
	 * @param id
	 * @return
	 */
	public Bear loadBearById(int id) {
		logger.warn("Loading bear by id: " + id);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Bear bear = null;
		try (Session session = sf.openSession()) {
			bear = session.load(Bear.class, id);
			logger.warn("Getting bear breed");

			// Hibernate.initialize can be used to load in a proxy, or any getter method
			// for the entity state other than id will also load it in
			Hibernate.initialize(bear);

		}
		return bear;
	}

	public Bear saveBear(Bear bear) {
		logger.warn(bear);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			// Save - saves 'transient' entity returning an serializable identifier
			// Persist - JPA - saves entity, making it 'persistent'
			session.persist(bear);
			logger.warn("Setting favorite food");
			bear.setFavoriteFood("Penguins");
			logger.warn("Committing");
			// Automatic dirty checking - Persistent Entity will automatically
			// be updated to current state at the commit
			tx.commit();
			logger.warn("Session preparing to close");
		}

		return bear;
	}

	public void delete(int id) {
		logger.warn("Deleting bear by id: " + id);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			Bear bear = session.get(Bear.class, id);
			session.delete(bear);
			tx.commit();
		}
	}
	
	public List<Bear> getBearsByFavoriteFood(String food) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		try(Session session = sf.openSession()) {
			// Select by entity
			String hql = "select b from Bear b where b.favoriteFood like :food";
			List<Bear> list = session.createQuery(hql, Bear.class)
					.setParameter("food", food, StringType.INSTANCE)
					.list();
			return list;
		}
	}
	
	public List<Bear> getBearsByLocation(String location) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		try(Session session = sf.openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Bear> bearQuery = cb.createQuery(Bear.class);
			Root<Bear> root = bearQuery.from(Bear.class);
			
			bearQuery.select(root)
				.where(cb.equal(root.get("location"), location));
			
			Query query = session.createQuery(bearQuery);
			List<Bear> results = (List<Bear>) query.getResultList();
			return results;
		}
	}
	/**
	 * HQL - Hibernate Query Language - Hibernate Query Language is similar to SQL, but largely
	 * 			relies on class and field names (Java) rather than the underlying SQL names. 
	 * 			Further, it supports named parameters, and an abbreviated syntax.
	 * 
	 * Criteria - Fully object-oriented method of querying the database. 
	 * 
	 */

}
