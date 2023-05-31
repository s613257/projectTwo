package test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import db.dao.BaseDAO_Hibernate;
import db.dao.TicketDAO;
import db.dao.impl.TicketDAOImpl;
import model.dto.HighSpeedRailTicket;
import model.dto.TicketInfo;

//public class MainTesting {
//
//	public static void main(String[] args) {
//		-----Ticket-----
//		DAOTesting.testTicketInfoDAO();
//		TicketDTO tkdto = new TicketDTO();
//		-----Insert-----
//		tkdto.setTicketID(200);
//		tkdto.setTranNo("201");
//		tkdto.setSeat("02A");
//		tkdto.setDeparture_ST("2");
//		tkdto.setDestination_ST("1");
//		Date dt = new Date();
//		tkdto.setdeparture_date(dt);
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
//		Date dt = new Date();
//		tkdto.setdeparture_date(dt);
//		tkdto.setDeparture_time("08:15");
//		tkdto.setArrival_time("09:15");
//		tkdto.setPrice(130);
//		Date d = new Date();
//		tkdto.setbooking_date(d);

//		TicketDAO tkaDao = new TicketDAOImpl();
//		tkaDao.insertTicketInfo(tkdto);
//		tkaDao.updateTicketInfo(tkdto);
//		tkaDao.deleteTicketInfo(200);
//		tkaDao.getAllTicketInfo();
//		tkaDao.getTicketInfoById(666);
//		tkaDao.getAllTicketInfo();
//		-----Booking-----
//		DAOTesting.testBookingDAOByTran();
//		DAOTesting.testBookingDAOByStation();
//		DAOTesting.testBookingDAOByPrice();
		
//		Session session = BaseDAO_Hibernate.getInstance();
//
//		try {
//			session.beginTransaction();
//
//			TicketDAO tkaDao = new TicketDAOImpl();
////			tkaDao.getAllTicketInfo();
////			System.out.println(tkaDao.getAllTicketInfo());
////			tkaDao.getTicketInfoById(1);
////			System.out.println(tkaDao.getTicketInfoById(1));
//			TicketDTO tkDto = new TicketDTO(); 
////			tkDto.setTicketID(1000);
////			tkDto.setTranNo("103");
////			tkDto.setSeat("01A");
////			tkDto.setDepartureST("1");
////			tkDto.setDestinationST("2");
////			tkDto.setdeparturedate("2023-04-18");
////			tkDto.setDeparturetime("06:15");
////			tkDto.setArrivaltime("06:20");
////			tkDto.setPrice(500);
////			tkDto.setbookingdate("2023-04-18");
////			tkaDao.insertTicketInfo(tkDto);
////			System.out.println(tkaDao.insertTicketInfo(tkDto));
////			tkDto.setTicketID(1001);
////			tkDto.setTranNo("155");
////			tkDto.setSeat("05A");
////			tkDto.setDepartureST("1");
////			tkDto.setDestinationST("2");
////			tkDto.setdeparturedate("2023-04-18");
////			tkDto.setDeparturetime("06:15");
////			tkDto.setArrivaltime("06:20");
////			tkDto.setPrice(500);
////			tkDto.setbookingdate("2023-04-18");
////			tkaDao.updateTicketInfo(tkDto);
////			System.out.println(tkaDao.updateTicketInfo(tkDto));
////			tkaDao.deleteTicketInfo(1001);
//
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		} finally {
//			BaseDAO_Hibernate.closeSessionFactory();
//		}
//	}

//}
