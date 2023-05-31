package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import db.dao.BaseDAO_Hibernate;
import db.service.HighSpeedRailService;
import db.service.impl.HighSpeedRailServiceImpl;
import model.dto.HighSpeedRailTicket;
import model.dto.TicketInfo;

public class Action {
//

	public static void main(String[] args) {
		
	}
//		Session session = BaseDAO_Hibernate.getInstance();
//		
//		try {
//			session.beginTransaction();
//
//			HighSpeedRailService service = new HighSpeedRailServiceImpl();
//			//新增單筆
//			TicketDTO ticket = new TicketDTO();
//			
//			ticket.setTicketID(200);
//			ticket.setTranNo("102");
//			ticket.setSeat("01A");
//			ticket.setDepartureST("1");
//			ticket.setDestinationST("2");
//			ticket.setdeparturedate("2023-05-19");
//			ticket.setDeparturetime("06:00");
//			ticket.setArrivaltime("06:15");
//			ticket.setPrice(100);
//			ticket.setbookingdate("2023-05-19");
//			
//			service.insertTicketInfo(ticket);
//			
			//selectAll
//			List<HighSpeedRailTicket> lists = service.getAllBookingTk();
//			for(HighSpeedRailTicket list : lists) {
//				System.out.println(list);
//				}
			
			//selectById
//			TicketDTO result = (new TicketDAOImpl()).GetTicketInfoById("200");
//			if(result != null) {
//				System.out.println("oridata===============================");
//				System.out.println(result.getTicketID() + " " + result.getTranNo()+" "+ result.getSeat()+" "+
//			result.getDepartureST()+" "+result.getDestinationST()+" "+result.getDeparturedate()+" "+
//			result.getDeparturetime()+" "+result.getArrivaltime()+" "+result.getPrice()+" "+result.getBookingdate());
//			}
//			
//			//update
//			result.setTranNo("200");
//			
//			BookingTk bookingTk = new BookingTk();
//			bookingTk.setTicketID(result.getTicketID());
//			bookingTk.setTranNo(result.getTranNo());
//			bookingTk.setSeat(result.getSeat());
//			bookingTk.setDepartureST(result.getDepartureST());
//			bookingTk.setDestinationST(result.getDestinationST());
//			bookingTk.setdeparturedate(result.getDeparturedate());
//			bookingTk.setDeparturetime(result.getDeparturetime());
//			bookingTk.setArrivaltime(result.getArrivaltime());
//			bookingTk.setPrice(result.getPrice());
//			bookingTk.setbookingdate(result.getBookingdate());
//			service.update(bookingTk);
//			
//			bookingTk = service.selectById(200);
//			if(result != null) {
//				System.out.println("updatadata===============================");
//				System.out.println(result.getTicketID() + " " + result.getTranNo()+" "+ result.getSeat()+" "+
//			result.getDepartureST()+" "+result.getDestinationST()+" "+result.getDeparturedate()+" "+
//			result.getDeparturetime()+" "+result.getArrivaltime()+" "+result.getPrice()+" "+result.getBookingdate());
//			}

		
			
			//delete
			//int status = service.delete(200);
			//System.out.println("status:" + status);
			
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}finally {
//			BaseDAO_Hibernate.closeSessionFactory();
//		}
//	}

}
	
