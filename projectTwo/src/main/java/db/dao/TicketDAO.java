package db.dao;

import java.util.List;

import org.hibernate.Session;

import model.dto.TicketDTO;

public interface TicketDAO {

//	public List<TicketDTO> getAllTicketInfo();
//	public TicketDTO GetTicketInfoById(String id);
//	public int insertTicketInfo(TicketDTO data);
//	public int updateTicketInfo(TicketDTO dataSource);
//	public int deleteTicketInfo(int TicketID);
	public TicketDTO getTicketInfoById(Session session, int ticketID);
	public List<TicketDTO> getAllTicketInfo(Session session);
	public boolean insertTicketInfo(Session session, TicketDTO ticket);
	public boolean updateTicketInfo(Session session, TicketDTO ticket);
	public boolean deleteTicketInfo(Session session, int ticketID);
}
