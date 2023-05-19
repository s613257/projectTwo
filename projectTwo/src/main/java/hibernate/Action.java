package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import db.dao.TicketDAO;
import db.dao.impl.TicketDAOImpl;
import hibernate.bean.BookingTk;
import hibernate.impl.BookingTkDAOImpl;
import hibernate.impl.BookingTkServiceImpl;
import model.dto.TicketDTO;

public class Action {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			BookingTkDAO hDao = new BookingTkDAOImpl(session);
			//新增單筆
			BookingTk bookingTk = new BookingTk();
			bookingTk.setTicketID(200);
			bookingTk.setTranNo("102");
			bookingTk.setSeat("01A");
			bookingTk.setDepartureST("1");
			bookingTk.setDestinationST("2");
			bookingTk.setdeparturedate("2023-05-19");
			bookingTk.setDeparturetime("06:00");
			bookingTk.setArrivaltime("06:15");
			bookingTk.setPrice(100);
			bookingTk.setbookingdate("2023-05-19");
			hDao.insert(bookingTk);
			
			
			//查詢全部
//			List<BookingTk> lists = hDao.selectAll();
//			for(BookingTk list : lists) {
//				System.out.println(list.getTicketID()+" "+ list.getTranNo()+" "+list.getSeat()+" "+list.getDepartureST()+
//						" " +list.getDestinationST()+" "+list.getDeparturedate()+" " +list.getDeparturetime()+
//						" " +list.getArrivaltime()+" "+list.getPrice()+" "+list.getBookingdate());
//			}
			
			//selectById
			BookingTkService service = new BookingTkServiceImpl(session);
			TicketDTO result = (new TicketDAOImpl()).GetTicketInfoById("200");
			if(result != null) {
				System.out.println("oridata===============================");
				System.out.println(result.getTicketID() + " " + result.getTranNo()+" "+ result.getSeat()+" "+
			result.getDepartureST()+" "+result.getDestinationST()+" "+result.getDeparturedate()+" "+
			result.getDeparturetime()+" "+result.getArrivaltime()+" "+result.getPrice()+" "+result.getBookingdate());
			}
			
			result.setTranNo("200");
			
			bookingTk.setTicketID(result.getTicketID());
			bookingTk.setTranNo(result.getTranNo());
			bookingTk.setSeat(result.getSeat());
			bookingTk.setDepartureST(result.getDepartureST());
			bookingTk.setDestinationST(result.getDestinationST());
			bookingTk.setdeparturedate(result.getDeparturedate());
			bookingTk.setDeparturetime(result.getDeparturetime());
			bookingTk.setArrivaltime(result.getArrivaltime());
			bookingTk.setPrice(result.getPrice());
			bookingTk.setbookingdate(result.getBookingdate());
			service.update(bookingTk);
			
			bookingTk = service.selectById(200);
			if(result != null) {
				System.out.println("updatadata===============================");
				System.out.println(result.getTicketID() + " " + result.getTranNo()+" "+ result.getSeat()+" "+
			result.getDepartureST()+" "+result.getDestinationST()+" "+result.getDeparturedate()+" "+
			result.getDeparturetime()+" "+result.getArrivaltime()+" "+result.getPrice()+" "+result.getBookingdate());
			}
		
			
			//delete
//			int status = service.delete(200);
//			System.out.println("status:" + status);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
	
