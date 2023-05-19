package hibernate.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import db.dao.TicketDAO;
import db.dao.impl.TicketDAOImpl;
import hibernate.BookingTkDAO;
import hibernate.bean.BookingTk;
import model.dto.TicketDTO;

public class BookingTkDAOImpl implements BookingTkDAO {

	private Session session;

	public BookingTkDAOImpl() {
	}

	public BookingTkDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public int insert(BookingTk booking) { //參數太多用物件

		TicketDTO ticket = new TicketDTO(); 
		ticket.setTicketID(booking.getTicketID());
		ticket.setTranNo(booking.getTranNo());
		ticket.setSeat(booking.getSeat());
		ticket.setDepartureST(booking.getDepartureST());
		ticket.setDestinationST(booking.getDestinationST());
		ticket.setdeparturedate(booking.getDeparturedate());
		ticket.setDeparturetime(booking.getDeparturetime());
		ticket.setArrivaltime(booking.getArrivaltime());
		ticket.setPrice(booking.getPrice());
		ticket.setbookingdate(booking.getBookingdate());
//		TicketDTO result = session.get(TicketDTO.class, ticket.getTicketID());
		return (new TicketDAOImpl()).insertTicketInfo(ticket);

	}

	@Override
	public BookingTk selectById(int ticketID) {
		return session.get(BookingTk.class, ticketID);
	}

	@Override
	public List<BookingTk> selectAll() {
		Query<BookingTk> query = session.createQuery("from BookingTk", BookingTk.class);
		return query.list();
	}

	@Override
	public int update(BookingTk booking) {
//		BookingTk result = session.get(BookingTk.class , ticketID);
		TicketDTO ticket = new TicketDTO();

		ticket.setTicketID(booking.getTicketID());
		ticket.setTranNo(booking.getTranNo());
		ticket.setSeat(booking.getSeat());
		ticket.setDepartureST(booking.getDepartureST());
		ticket.setDestinationST(booking.getDestinationST());
		ticket.setdeparturedate(booking.getDeparturedate());
		ticket.setDeparturetime(booking.getDeparturetime());
		ticket.setArrivaltime(booking.getArrivaltime());
		ticket.setPrice(booking.getPrice());
		ticket.setbookingdate(booking.getBookingdate());
		return (new TicketDAOImpl()).updateTicketInfo(ticket);

	}

	@Override
	public int delete(int ticketID) {
//		BookingTk bean = session.get(BookingTk.class , ticketID);
		return (new TicketDAOImpl()).deleteTicketInfo(ticketID);
	}

}
