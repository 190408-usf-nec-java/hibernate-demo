package com.revature.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.entities.Bear;
import com.revature.entities.Cave;
import com.revature.entities.HoneyJar;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static void configure() {
		// If already configured, don't reconfigure
		if (sessionFactory != null) return;
		
		// Loads configuration properties from a hibernate configuration location
		// this includes: hibernate.cfg.xml
		Configuration configuration = new Configuration()
				.configure()
				.addAnnotatedClass(Bear.class)
				.addAnnotatedClass(Cave.class)
				.addAnnotatedClass(HoneyJar.class);
		
		// Compiles that configuration into a format that can be accepted by 
		// the configuration to build a session factory
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
