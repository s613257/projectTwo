package hibernate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import hibernate.BookingTkService;
import hibernate.HibernateUtil;
import hibernate.bean.BookingTk;
import model.dto.StationInfoDTO;
import model.dto.TicketDTO;

public class BookingTkServiceImpl implements BookingTkService {

	public BookingTkServiceImpl() {
		
	}
	
	@Override
	public BookingTk getBookingTkById( int ticketID) {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		List<StationInfoDTO> stationInfos = (new BookingDAOImpl()).getAllStationInfo(); // 命名不太好 XD 太接近了
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfoDTO stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		TicketDTO ticketInfo = (new TicketDAOImpl()).getTicketInfoById(session,ticketID);
		BookingTk result = new BookingTk(ticketInfo);
		result.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
		result.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		return result;
	}

	@Override
	public List<BookingTk> getAllBookingTk() {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		List<TicketDTO> ticketInfoLst = (new TicketDAOImpl()).getAllTicketInfo(session);
		List<StationInfoDTO> stationInfos = (new BookingDAOImpl()).getAllStationInfo(); // 命名不太好 XD 太接近了
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		
		for (StationInfoDTO stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		
		List<BookingTk> result = new ArrayList<>();
		for (TicketDTO ticketInfo : ticketInfoLst) {
			BookingTk bookingTk = new BookingTk(ticketInfo);
			bookingTk.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
			bookingTk.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
			result.add(bookingTk);
		}
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		return result;
	}
	
	
	
	@Override
	public boolean insertTicketInfo(TicketDTO ticketDto) {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		TicketDAO ticketDao = new TicketDAOImpl();
		
		if(ticketDao.insertTicketInfo(session, ticketDto)) {
			session.getTransaction().commit();
		}else {
			session.getTransaction().rollback();
		}
		HibernateUtil.closeSessionFactory();
		return true;
	}
	@Override
	public boolean updateTicketInfo(TicketDTO ticketDto) {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		TicketDAO ticketDao = new TicketDAOImpl();
		
		if(ticketDao.updateTicketInfo(session, ticketDto)) {
			session.getTransaction().commit();
		}else {
			session.getTransaction().rollback();
		}
		HibernateUtil.closeSessionFactory();
		return true;
	}
}
