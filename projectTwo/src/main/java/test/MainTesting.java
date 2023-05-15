package test;

import java.util.Date;

import db.dao.TicketDAO;
import db.dao.impl.TicketDAOImpl;
import model.dto.TicketDTO;

public class MainTesting {

	public static void main(String[] args) {
//		-----Ticket-----
//		DAOTesting.testTicketInfoDAO();
		TicketDTO tkdto = new TicketDTO();
//		-----Insert-----
//		tkdto.setTicketID(200);
//		tkdto.setTranNo("201");
//		tkdto.setSeat("02A");
//		tkdto.setDeparture_ST("2");
//		tkdto.setDestination_ST("1");
//		tkdto.setDeparture_time("08:15");
//		tkdto.setArrival_time("09:15");
//		tkdto.setPrice(130);
//		Date d = new Date();
//		tkdto.setbooking_date(d);
//		-----Update-----
//		tkdto.setTicketID(200);
//		tkdto.setTranNo("200");
//		tkdto.setSeat("03A");
//		tkdto.setDeparture_ST("2");
//		tkdto.setDestination_ST("1");
//		tkdto.setDeparture_time("08:15");
//		tkdto.setArrival_time("09:15");
//		tkdto.setPrice(130);
//		Date d = new Date();
//		tkdto.setDate(d);
		
		TicketDAO tkaDao = new TicketDAOImpl();
		tkaDao.insertTicketInfo(tkdto);
//		tkaDao.updateTicketInfo(tkdto);
//		tkaDao.deleteTicketInfo(200);
		tkaDao.getAllTicketInfo();
//		-----Booking-----
		DAOTesting.testBookingDAOByTran();
		DAOTesting.testBookingDAOByStation();
		DAOTesting.testBookingDAOByPrice();
	}
}
