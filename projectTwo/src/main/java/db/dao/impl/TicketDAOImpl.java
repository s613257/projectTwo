package db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import db.dao.TicketDAO;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.dto.TicketInfo;

@Repository
@Transactional
public class TicketDAOImpl  implements TicketDAO {

	@Autowired
	private SessionFactory factory;
	
	@PersistenceContext
	private Session session;

	public TicketDAOImpl() {
	}

	@Override
	public boolean insertTicketInfo(TicketInfo ticket) {
		session = factory.openSession();
		session.beginTransaction();
		try {
			session.persist(ticket);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		}finally {
			session.close();
		}
		return true;
	}
	
	@Override
	public boolean insertTicketInfo(Session session, TicketInfo ticket) {
		try {
			session.persist(ticket);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	@Override
	public TicketInfo getTicketInfoById(int ticketID) {
		session = factory.openSession();
		return session.get(TicketInfo.class, ticketID);
	}
	
	@Override
	public List<TicketInfo> getAllTicketInfo() {
		session = factory.openSession();
		Query<TicketInfo> query = session.createQuery("from TicketInfo", TicketInfo.class);
		return query.list();
	}

	@Override
	public boolean updateTicketInfo(TicketInfo ticket) {
		session = factory.openSession();
		session.beginTransaction();
		try {
			session.merge(ticket);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		}finally {
			session.close();
		}
		return true;
	}

	@Override
	public boolean deleteTicketInfo(int ticketID) {
		session = factory.openSession();
		session.beginTransaction();
		TicketInfo bean = session.get(TicketInfo.class, ticketID);
		if (bean != null) {
			session.remove(bean);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		session.getTransaction().rollback();
		session.close();
		return false;
	}

}

