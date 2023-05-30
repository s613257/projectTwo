package db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import db.dao.BaseDAO_Hibernate;
import db.dao.TicketDAO;
import jakarta.transaction.Transactional;
import model.dto.Ticket;

@Repository
@Transactional
public class TicketDAOImpl extends BaseDAO_Hibernate implements TicketDAO {

	public TicketDAOImpl() {
	}

	@Override
	public boolean insertTicketInfo(Ticket ticket) {
		return insertTicketInfo(getSession(), ticket);
	}
	
	@Override
	public boolean insertTicketInfo(Session session, Ticket ticket) {

		Ticket tkDto = new Ticket();
		tkDto.setTicketID(ticket.getTicketID());
		tkDto.setTranNo(ticket.getTranNo());
		tkDto.setSeat(ticket.getSeat());
		tkDto.setDepartureST(ticket.getDepartureST());
		tkDto.setDestinationST(ticket.getDestinationST());
		tkDto.setdeparturedate(ticket.getDeparturedate());
		tkDto.setDeparturetime(ticket.getDeparturetime());
		tkDto.setArrivaltime(ticket.getArrivaltime());
		tkDto.setPrice(ticket.getPrice());
		tkDto.setbookingdate(ticket.getBookingdate());
		try {
			session.persist(ticket);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public Ticket getTicketInfoById(int ticketID) {
		return getTicketInfoById(getSession(), ticketID);
	}

	@Override
	public Ticket getTicketInfoById(Session session, int ticketID) {
		return session.get(Ticket.class, ticketID);
	}
	
	@Override
	public List<Ticket> getAllTicketInfo() {
		return getAllTicketInfo(getSession());
	}

	@Override
	public List<Ticket> getAllTicketInfo(Session session) {
		Query<Ticket> query = session.createQuery("from TicketInfo", Ticket.class);
		return query.list();
	}
	

	@Override
	public boolean updateTicketInfo(Ticket ticket) {
		return updateTicketInfo(getSession(), ticket);
	}

	@Override
	public boolean updateTicketInfo(Session session, Ticket ticket) {
		Ticket tkDto = new Ticket();

		tkDto.setTicketID(ticket.getTicketID());
		tkDto.setTranNo(ticket.getTranNo());
		tkDto.setSeat(ticket.getSeat());
		tkDto.setDepartureST(ticket.getDepartureST());
		tkDto.setDestinationST(ticket.getDestinationST());
		tkDto.setdeparturedate(ticket.getDeparturedate());
		tkDto.setDeparturetime(ticket.getDeparturetime());
		tkDto.setArrivaltime(ticket.getArrivaltime());
		tkDto.setPrice(ticket.getPrice());
		tkDto.setbookingdate(ticket.getBookingdate());
		try {
			session.merge(ticket);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deleteTicketInfo(int ticketID) {
		return deleteTicketInfo(getSession(),ticketID);
	}
	
	@Override
	public boolean deleteTicketInfo(Session session, int ticketID) {
		Ticket bean = session.get(Ticket.class, ticketID);
		if (bean != null) {
			session.remove(bean);
			return true;
		}
		return false;
	}

	

//	public TicketDTO GetTicketInfoById(String id) {
//	List<TicketDTO> resultList = getInfoBySql("SELECT * FROM TicketInfo WHERE ticketID = " + id);
//	System.out.println(resultList);
//	return resultList.get(0);
//}
//	@Override
//	public List<TicketDTO> getAllTicketInfo() {
//		 return getInfoBySql("select * from TicketInfo ORDER BY	TicketID ASC");
////		return getInfoBySql("select"
////				+ "	tic_info.TicketID 'TicketID', " 
////				+ "	tic_info.TranNo 'TranNo', "
////				+ "	tic_info.Seat 'Seat'," 
////				+ "	sta_info_dep.StationName 'DepartureST',"
////				+ "	sta_info_des.StationName 'DestinationST',"
////				+ " tic_info.departuredate 'DepartureDate',"
////				+ "	tic_info. Departuretime 'Departuretime',"
////				+ "	tic_info.Arrivaltime 'Arrivaltime'," 
////				+ "	tic_info.price 'price',"
////				+ "	tic_info.BookingDate 'BookingDate' " 
////				+ "	from TicketInfo tic_info"
////				+ "	left join StationInfo sta_info_des on tic_info.DestinationST = sta_info_des.StationID"
////				+ "	left join StationInfo sta_info_dep on tic_info.DepartureST = sta_info_dep.StationID"
////				+ " ORDER BY	TicketID ASC;");
//	}
//	@Override
//	public int insertTicketInfo(TicketDTO data) {
//		String sql = data.getInsertSql();
//		System.out.println(sql);
//		return executeUpdate(sql);
//
//	}
//
//	@Override
//	public int updateTicketInfo(TicketDTO dataSource) {
//		String sql = dataSource.getUpdataSql();
//		System.out.println(sql);
//		return executeUpdate(sql);
//	}
//
//	@Override
//	public int deleteTicketInfo(int TicketID) {
//		return executeUpdate(String.format("delete from TicketInfo where ticketID = %d;", TicketID));
//	}
//
//	private List<TicketDTO> getInfoBySql(String sql) {
//		List<TicketDTO> resultList = new ArrayList<TicketDTO>();
//		Statement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//			while (rs.next()) {
//				resultList.add(new TicketDTO(rs));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll(st, rs);
//		}
//		return resultList;
//
//	}
//
//	private int executeUpdate(String sql) {
//		Statement st = null;
//		int effectRow = 0;
//		try {
//			st = conn.createStatement();
//			effectRow = st.executeUpdate(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll(st, null);
//		}
//		return effectRow;
//
//	}
}
