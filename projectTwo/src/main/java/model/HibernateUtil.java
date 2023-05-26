package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory factory = null;
	private static Session INSTANCE = null;
	
	public static Session getInstance() {
		if(INSTANCE == null) {
			INSTANCE = getSessionFactory().getCurrentSession();
		}
		return INSTANCE;
	}
	
	private static SessionFactory createSessionFactory() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		return factory;
	}
	
	private static void closeSession() {
		if(INSTANCE != null) {
			while (INSTANCE.getTransaction().isActive()) {
				// wait for transaction done
			}
			System.out.println("Session is closed");
			INSTANCE.close();
			INSTANCE = null;
		}
	}
	
	private static SessionFactory getSessionFactory() {
		if(factory == null) {
			factory = createSessionFactory();
		}
		return factory;
	}
	
	public static void closeSessionFactory() {
		if(factory!=null) {
			closeSession();
			factory.close();
			factory = null;
			System.out.println("Factory is closed");
		}
	}
}
