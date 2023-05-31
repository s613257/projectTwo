package db.dao;

import java.util.List;

import model.dto.TicketInfo;


public interface TicketDAO {

	public TicketInfo getTicketInfoById(int ticketID);
	public List<TicketInfo> getAllTicketInfo();
	public boolean insertTicketInfo(TicketInfo ticket);
	public boolean updateTicketInfo(TicketInfo ticket);
	public boolean deleteTicketInfo(int ticketID);
	
//	public TicketInfo getTicketInfoById(Session session, int ticketID);
//	public List<TicketInfo> getAllTicketInfo(Session session);
//	public boolean insertTicketInfo(Session session, TicketInfo ticket);
//	public boolean updateTicketInfo(Session session, TicketInfo ticket);
//	public boolean deleteTicketInfo(Session session, int ticketID);
}
