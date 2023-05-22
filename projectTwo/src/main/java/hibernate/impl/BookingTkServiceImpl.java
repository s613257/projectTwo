package hibernate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import hibernate.BookingTkService;
import hibernate.bean.BookingTk;
import model.dto.StationInfoDTO;
import model.dto.TicketDTO;

public class BookingTkServiceImpl  implements  BookingTkService{
	
	private BookingTkDAOImpl bkDao;
	
	public BookingTkServiceImpl(Session session) {
		bkDao = new BookingTkDAOImpl(session);
	}

	@Override
	public int insert(BookingTk booking) {
		return bkDao.insert(booking);
	}

	@Override
	public BookingTk selectById(int ticketID) {
		return bkDao.selectById(ticketID);
	}

	@Override
	public List<BookingTk> selectAll() {
		List<TicketDTO> ticketInfoLst = (new TicketDAOImpl()).getAllTicketInfo();
		List<StationInfoDTO>  stationInfos = (new BookingDAOImpl()).getAllStationInfo(); // 命名不太好 XD 太接近了
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for(StationInfoDTO stationInfo: stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		List<BookingTk> result = new ArrayList<>();
		for(TicketDTO ticketInfo: ticketInfoLst) {
			BookingTk bookingTk = new BookingTk(ticketInfo);
			bookingTk.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
			bookingTk.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
			result.add(bookingTk);
		}
		
		return result;
	}

	@Override
	public int update(BookingTk booking) {
		return bkDao.update(booking);
	}

	@Override
	public int delete(int ticketID) {
		return bkDao.delete(ticketID);
	}
}
