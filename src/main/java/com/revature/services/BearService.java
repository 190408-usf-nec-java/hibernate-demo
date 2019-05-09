package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.entities.Bear;
import com.revature.util.HibernateUtil;

public class BearService {

	Logger logger = Logger.getRootLogger();
	
	public List<Bear> getAllBears() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bear getBearById(int id) {
		logger.warn("Getting bear by id: " + id);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Bear bear = null;
		try(Session session = sf.openSession()) {
			bear = session.get(Bear.class, id);
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
			session.save(bear);
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

}
