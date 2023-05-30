package db.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db.dao.BaseDAO_Hibernate;
import db.service.HighSpeedRailService;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import jakarta.transaction.Transactional;
import model.dto.HighSpeedRailTicket;
import model.dto.StationInfo;
import model.dto.Ticket;

@Service
@Transactional
public class HighSpeedRailServiceImpl extends BaseDAO_Hibernate implements HighSpeedRailService {

	
	public HighSpeedRailServiceImpl() {
		
	}
	
	@Override
	public HighSpeedRailTicket getBookingTkById(int ticketID) {
		Session session = getSession();
		session.beginTransaction();
		List<StationInfo> stationInfos = (new BookingDAOImpl()).getAllStationInfo(session); 
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		Ticket ticketInfo = (new TicketDAOImpl()).getTicketInfoById(session,ticketID);
		HighSpeedRailTicket result = new HighSpeedRailTicket(ticketInfo);
		result.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
		result.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		session.getTransaction().commit();
		return result;
	}

	@Override
	public List<HighSpeedRailTicket> getAllBookingTk() {
		Session session = getSession();
		session.beginTransaction();
		List<Ticket> ticketInfoLst = (new TicketDAOImpl()).getAllTicketInfo(session);
//		session.getTransaction().getStatus() == TransactionStatus.ACTIVE
		List<StationInfo> stationInfos = (new BookingDAOImpl()).getAllStationInfo(session); 
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		
		List<HighSpeedRailTicket> result = new ArrayList<>();
		for (Ticket ticketInfo : ticketInfoLst) {
			HighSpeedRailTicket bookingTk = new HighSpeedRailTicket(ticketInfo);
			bookingTk.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
			bookingTk.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
			result.add(bookingTk);
		}
		session.getTransaction().commit();
		return result;
	}
	
	@Override
	public boolean insertTicketInfo(Ticket ticketDto) {
		Session session = getSession();
		session.beginTransaction();
		TicketDAO ticketDao = new TicketDAOImpl();
		
		if(ticketDao.insertTicketInfo(session,ticketDto)) {
			session.getTransaction().commit();
		}else {
			session.getTransaction().rollback();
		}
		return true;
	}
	
	@Override
	public boolean updateTicketInfo(Ticket ticketDto) {
		Session session = getSession();
		session.beginTransaction();
		TicketDAO ticketDao = new TicketDAOImpl();
		
		if(ticketDao.updateTicketInfo(session,ticketDto)) {
			session.getTransaction().commit();
		}else {
			session.getTransaction().rollback();
		}
		return true;
	}
}
