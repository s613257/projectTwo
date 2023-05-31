package db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseDAO_Hibernate {

	@Autowired
	private SessionFactory sessionFactory;
	private Session INSTANCE = null;
	
	public Session getSession() {
		if(INSTANCE == null) {
			INSTANCE =  sessionFactory.openSession();
//			INSTANCE =  sessionFactory.getCurrentSession();
		}
		if(INSTANCE.getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE) {
			INSTANCE.beginTransaction();
		}
		return INSTANCE;
	}
	
	public void closeSession() {
	    if (INSTANCE != null && INSTANCE.isOpen()) {
	        if (INSTANCE.getTransaction().isActive()) {
	            INSTANCE.getTransaction().commit();
	        }
	        INSTANCE.close();
	        INSTANCE = null;
	    }
	}
}
