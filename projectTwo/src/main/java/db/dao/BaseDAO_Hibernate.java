package db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseDAO_Hibernate {

	@Autowired
	private SessionFactory sessionFactory;
	private Session INSTANCE = null;
	
	public Session getSession() {
		if(INSTANCE == null) {
			INSTANCE =  sessionFactory.openSession();
		}
		return INSTANCE;
	}
}
