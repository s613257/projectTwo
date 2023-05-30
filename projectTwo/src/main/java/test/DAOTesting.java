package test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;

import java.util.Set;

import db.dao.BookingDAO;
import db.dao.BaseDAO_Hibernate;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import model.dto.PriceInfo;
import model.dto.StationInfo;
import model.dto.Ticket;
import model.dto.TranInfo;

public class DAOTesting {
//	public static void testTicketInfoDAO() {
//		Session session = BaseDAO_Hibernate.getInstance();
//		session.beginTransaction();
//		TicketDAO tk_dao = new TicketDAOImpl();
//		List<TicketDTO> ticketList = tk_dao.getAllTicketInfo(session);
//		session.getTransaction();
//		BaseDAO_Hibernate.closeSessionFactory();
//		for (TicketDTO tk : ticketList) {
//			System.out.println(tk.toString());
//		}
//	}

	public static void testBookingDAOByTran() {
		BookingDAO bk_dao = new BookingDAOImpl();
		List<Ticket> TranList = bk_dao.getAllTranInfo();
		for (Ticket bk : TranList) {
			System.out.println(bk.toString());
		}
	}

//	public static void testBookingDAOByStation() {
//		Session session = BaseDAO_Hibernate.getInstance();
//		session.beginTransaction();
//		BookingDAO bk_dao = new BookingDAOImpl();
//		List<StationInfoDTO> StationList = bk_dao.getAllStationInfo(session);
//		session.getTransaction();
//		BaseDAO_Hibernate.closeSessionFactory();
//		for (StationInfoDTO bk : StationList) {
//			System.out.println(bk.toString());
//		}
//	}

	public static void testBookingDAOByPrice() {
		BookingDAO bk_dao = new BookingDAOImpl();
		Map<Set<String>, Integer> pirceList = bk_dao.getAllPriceInfo();
		for (Entry<Set<String>, Integer> pk : pirceList.entrySet()) {
			System.out.println("key:" + pk.getKey() + "value:" + pk.getValue());
		}
	}
}
