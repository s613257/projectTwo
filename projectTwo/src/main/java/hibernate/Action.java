package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.bean.BookingTk;
import hibernate.impl.BookingTkServiceImpl;

public class Action {

	public static void main(String[] args) {
		Session session = HibernateUtil.getInstance();
		
		try {
			session.beginTransaction();

			BookingTkService service = new BookingTkServiceImpl();
			//新增單筆
//			BookingTk bookingTk = new BookingTk();
//			
//			bookingTk.setTicketID(200);
//			bookingTk.setTranNo("102");
//			bookingTk.setSeat("01A");
//			bookingTk.setDepartureST("1");
//			bookingTk.setDestinationST("2");
//			bookingTk.setdeparturedate("2023-05-19");
//			bookingTk.setDeparturetime("06:00");
//			bookingTk.setArrivaltime("06:15");
//			bookingTk.setPrice(100);
//			bookingTk.setbookingdate("2023-05-19");
//			
//			service.insert(bookingTk);
			
			//selectAll
			List<BookingTk> lists = service.getAllBookingTk();
			for(BookingTk list : lists) {
				System.out.println(list);
				}
			
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
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
	
