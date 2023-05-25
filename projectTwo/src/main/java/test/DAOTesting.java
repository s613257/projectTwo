package test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;

import java.util.Set;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import hibernate.HibernateUtil;
import model.dto.PriceInfoDTO;
import model.dto.StationInfoDTO;
import model.dto.TicketDTO;
import model.dto.TranInfoDTO;

public class DAOTesting {
	public static void testTicketInfoDAO() {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		TicketDAO tk_dao = new TicketDAOImpl();
		List<TicketDTO> ticketList = tk_dao.getAllTicketInfo(session);
		session.getTransaction();
		HibernateUtil.closeSessionFactory();
		for (TicketDTO tk : ticketList) {
			System.out.println(tk.toString());
		}
	}

	public static void testBookingDAOByTran() {
		BookingDAO bk_dao = new BookingDAOImpl();
		List<TicketDTO> TranList = bk_dao.getAllTranInfo();
		for (TicketDTO bk : TranList) {
			System.out.println(bk.toString());
		}
	}

	public static void testBookingDAOByStation() {
		BookingDAO bk_dao = new BookingDAOImpl();
		List<StationInfoDTO> StationList = bk_dao.getAllStationInfo();
		for (StationInfoDTO bk : StationList) {
			System.out.println(bk.toString());
		}
	}

	public static void testBookingDAOByPrice() {
		BookingDAO bk_dao = new BookingDAOImpl();
		Map<Set<String>, Integer> pirceList = bk_dao.getAllPriceInfo();
		for (Entry<Set<String>, Integer> pk : pirceList.entrySet()) {
			System.out.println("key:" + pk.getKey() + "value:" + pk.getValue());
		}
	}
}
