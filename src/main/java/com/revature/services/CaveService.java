package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.entities.Bear;
import com.revature.entities.Cave;
import com.revature.util.HibernateUtil;

public class CaveService {
	private Logger logger = Logger.getRootLogger();
	
	public Cave create(Cave cave) {
		// validation 
	
		SessionFactory sf = HibernateUtil.getSessionFactory();
		try (Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.save(cave);
			tx.commit();
		}
		return cave;
	}

	public Cave findById(int id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		try (Session session = sf.openSession()) {
			Cave cave = session.get(Cave.class, id);
			List<Bear> bears = cave.getOccupants();
			logger.warn(bears.size());
			return cave;
		}
	}

}
