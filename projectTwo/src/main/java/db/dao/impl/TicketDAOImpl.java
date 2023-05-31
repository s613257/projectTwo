package db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import db.dao.TicketDAO;
import jakarta.transaction.Transactional;
import model.dto.TicketInfo;

@Repository
@Transactional
public class TicketDAOImpl  implements TicketDAO {

	@Autowired
	private SessionFactory factory;

	public TicketDAOImpl() {
	}

	@Override
	public boolean insertTicketInfo(TicketInfo ticket) {
		Session session = factory.openSession();
		TicketInfo tkDto = new TicketInfo();
		tkDto.setTicketID(ticket.getTicketID());
		tkDto.setTranNo(ticket.getTranNo());
		tkDto.setSeat(ticket.getSeat());
		tkDto.setDepartureST(ticket.getDepartureST());
		tkDto.setDestinationST(ticket.getDestinationST());
		tkDto.setDeparturedate(ticket.getDeparturedate());
		tkDto.setDeparturetime(ticket.getDeparturetime());
		tkDto.setArrivaltime(ticket.getArrivaltime());
		tkDto.setPrice(ticket.getPrice());
		tkDto.setBookingdate(ticket.getBookingdate());
		try {
			session.persist(ticket);
			session.flush();
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public TicketInfo getTicketInfoById(int ticketID) {
		Session session = factory.openSession();
		return session.get(TicketInfo.class, ticketID);
	}
	
	@Override
	public List<TicketInfo> getAllTicketInfo() {
		Session session = factory.openSession();
		Query<TicketInfo> query = session.createQuery("from TicketInfo", TicketInfo.class);
		return query.list();
	}

	

	@Override
	public boolean updateTicketInfo(TicketInfo ticket) {
		Session session = factory.openSession();
		TicketInfo tkDto = new TicketInfo();
		tkDto.setTicketID(ticket.getTicketID());
		tkDto.setTranNo(ticket.getTranNo());
		tkDto.setSeat(ticket.getSeat());
		tkDto.setDepartureST(ticket.getDepartureST());
		tkDto.setDestinationST(ticket.getDestinationST());
		tkDto.setDeparturedate(ticket.getDeparturedate());
		tkDto.setDeparturetime(ticket.getDeparturetime());
		tkDto.setArrivaltime(ticket.getArrivaltime());
		tkDto.setPrice(ticket.getPrice());
		tkDto.setBookingdate(ticket.getBookingdate());
		try {
			session.merge(ticket);
			session.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteTicketInfo(int ticketID) {
		Session session = factory.openSession();
		TicketInfo bean = session.get(TicketInfo.class, ticketID);
		if (bean != null) {
			session.remove(bean);
			session.close();
			return true;
		}
		return false;
	}

}

