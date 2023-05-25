package hibernate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import hibernate.BookingTkDAO;
import hibernate.bean.BookingTk;
import model.dto.StationInfoDTO;
import model.dto.TicketDTO;

public class BookingTkDAOImpl implements BookingTkDAO {

	private Session session;

	public BookingTkDAOImpl() {
	}

	public BookingTkDAOImpl(Session session) {
		this.session = session;
	}

	@Override
	public TicketDTO insert(BookingTk booking) { //參數太多用物件

		TicketDTO ticket = new TicketDTO(); 
		ticket.setTicketID(booking.getTicketID());
		ticket.setTranNo(booking.getTranNo());
		ticket.setSeat(booking.getSeat());
		ticket.setDepartureST(booking.getDepartureST());
		ticket.setDestinationST(booking.getDestinationST());
		ticket.setdeparturedate(booking.getDeparturedate());
		ticket.setDeparturetime(booking.getDeparturetime());
		ticket.setArrivaltime(booking.getArrivaltime());
		ticket.setPrice(booking.getPrice());
		ticket.setbookingdate(booking.getBookingdate());
//		TicketDTO result = session.get(TicketDTO.class, ticket.getTicketID());
		return (new TicketDAOImpl()).insertTicketInfo(ticket);

	}

	@Override
	public BookingTk selectById(int ticketID) {
		return session.get(BookingTk.class, ticketID);
	}

	@Override
	public List<BookingTk> selectAll() {
		// (new TicketDAOImpl()).getAllTicketInfo(); 這拿到的是 單純 TicketInfo 表的所有資料
		List<TicketDTO> ticketInfoLst = (new TicketDAOImpl()).getAllTicketInfo();
		
		// (new BookingDAOImpl()).getAllStationInfo(); 這拿到的是 單純 StationInfo 表的所有資料
		List<StationInfoDTO>  stationInfos = (new BookingDAOImpl()).getAllStationInfo();
		
		// 把兩個表的資料拼起來
		Map<Integer, String> stationMap = new HashMap<Integer, String>();
		for(StationInfoDTO stationInfo: stationInfos) {
			stationMap.put(stationInfo.getStationID(), stationInfo.getStationName());
		}
		List<BookingTk> result = new ArrayList<>();
		for(TicketDTO ticketInfo: ticketInfoLst) {
			BookingTk bookingTk = new BookingTk(ticketInfo);
			// 上面我有點偷懶  正常來說這樣不太好 應該要用
//			setTicketID(tickDto.getTicketID());
//			setTranNo(tickDto.getTranNo());
//			setSeat(tickDto.getSeat());
//			setDepartureST(tickDto.getDepartureST());
//			setDestinationST(tickDto.getDestinationST());
//			setdeparturedate(tickDto.getDeparturedate());
//			setDeparturetime(tickDto.getDeparturetime());
//			setArrivaltime(tickDto.getArrivaltime());
//			setPrice(tickDto.getPrice());
//			setbookingdate(tickDto.getBookingdate());
			// 把他一個一個拼起來
			
			// 不過之前有設計過 TicketDTO和 BookingTk根本超像
			// 所以我就偷懶這樣寫 最後再把ID的String換成站名
			bookingTk.setDepartureST(stationMap.get(Integer.parseInt(ticketInfo.getDepartureST())));
			bookingTk.setDestinationST(stationMap.get(Integer.parseInt(ticketInfo.getDestinationST())));
		} 
		// 拚完了
		return result;
//		Query<BookingTk> query = session.createQuery("from BookingTk", BookingTk.class);
//		return query.list();
	}

	@Override
	public TicketDTO update(BookingTk booking) {
//		BookingTk result = session.get(BookingTk.class , ticketID);
		TicketDTO ticket = new TicketDTO();

		ticket.setTicketID(booking.getTicketID());
		ticket.setTranNo(booking.getTranNo());
		ticket.setSeat(booking.getSeat());
		ticket.setDepartureST(booking.getDepartureST());
		ticket.setDestinationST(booking.getDestinationST());
		ticket.setdeparturedate(booking.getDeparturedate());
		ticket.setDeparturetime(booking.getDeparturetime());
		ticket.setArrivaltime(booking.getArrivaltime());
		ticket.setPrice(booking.getPrice());
		ticket.setbookingdate(booking.getBookingdate());
		return (new TicketDAOImpl()).updateTicketInfo(ticket);

	}

	@Override
	public boolean delete(int ticketID) {
//		BookingTk bean = session.get(BookingTk.class , ticketID);
		return (new TicketDAOImpl()).deleteTicketInfo(ticketID);
	}

}
