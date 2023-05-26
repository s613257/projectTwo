package db.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import db.dao.HighSpeedRailService;
import db.dao.TicketDAO;
import model.HibernateUtil;
import model.dto.HighSpeedRailTicket;
import model.dto.StationInfoDTO;
import model.dto.TicketDTO;

public class HighSpeedRailServiceImpl implements HighSpeedRailService {

	public HighSpeedRailServiceImpl() {
		
	}
	
	@Override
	public HighSpeedRailTicket getBookingTkById(int ticketID) {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		List<StationInfoDTO> stationInfos = (new BookingDAOImpl()).getAllStationInfo(); 
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfoDTO stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		TicketDTO ticketInfo = (new TicketDAOImpl()).getTicketInfoById(session,ticketID);
		HighSpeedRailTicket result = new HighSpeedRailTicket(ticketInfo);
		result.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
		result.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		return result;
	}

	@Override
	public List<HighSpeedRailTicket> getAllBookingTk() {
		Session session = HibernateUtil.getInstance();
		session.beginTransaction();
		List<TicketDTO> ticketInfoLst = (new TicketDAOImpl()).getAllTicketInfo(session);
		
		List<StationInfoDTO> stationInfos = (new BookingDAOImpl()).getAllStationInfo(); 
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfoDTO stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		
		List<HighSpeedRailTicket> result = new ArrayList<>();
		for (TicketDTO ticketInfo : ticketInfoLst) {
			HighSpeedRailTicket bookingTk = new HighSpeedRailTicket(ticketInfo);
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
