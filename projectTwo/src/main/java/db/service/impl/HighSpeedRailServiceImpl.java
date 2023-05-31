package db.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import db.dao.BookingDAO;
import db.service.HighSpeedRailService;
import db.dao.TicketDAO;
import jakarta.transaction.Transactional;
import model.dto.HighSpeedRailTicket;
import model.dto.StationInfo;
import model.dto.TicketInfo;

@Service
@Transactional
public class HighSpeedRailServiceImpl  implements HighSpeedRailService {

	@Autowired
	@Qualifier("bookingDAOImpl")
	private BookingDAO bookingDao;

	@Autowired
	@Qualifier("ticketDAOImpl")
	private TicketDAO ticketDao;

	public HighSpeedRailServiceImpl() {

	}

	@Override
	public HighSpeedRailTicket getBookingTkById(int ticketID) {
		List<StationInfo> stationInfos = bookingDao.getAllStationInfo();
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}

		TicketInfo ticketInfo = ticketDao.getTicketInfoById(ticketID);
		HighSpeedRailTicket result = new HighSpeedRailTicket(ticketInfo);
		result.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
		result.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		return result;
	}

	@Override
	public List<HighSpeedRailTicket> getAllBookingTk() {

		List<TicketInfo> ticketInfoLst = ticketDao.getAllTicketInfo();

		List<StationInfo> stationInfos = bookingDao.getAllStationInfo();
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for (StationInfo stationInfo : stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}

		List<HighSpeedRailTicket> result = new ArrayList<>();
		for (TicketInfo ticketInfo : ticketInfoLst) {
			HighSpeedRailTicket bookingTk = new HighSpeedRailTicket(ticketInfo);
			bookingTk.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
			bookingTk.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
			result.add(bookingTk);
		}
		return result;

	}

	@Override
	public boolean insertTicketInfo(TicketInfo ticketDto) {
		return ticketDao.insertTicketInfo(ticketDto);
	}
	
	@Override
	public boolean updateTicketInfo(TicketInfo ticketDto) {
		return ticketDao.updateTicketInfo(ticketDto);
	}


}
