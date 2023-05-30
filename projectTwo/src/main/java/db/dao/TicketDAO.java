package db.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import model.dto.Ticket;


public interface TicketDAO {

//	public List<TicketDTO> getAllTicketInfo();
//	public TicketDTO GetTicketInfoById(String id);
//	public int insertTicketInfo(TicketDTO data);
//	public int updateTicketInfo(TicketDTO dataSource);
//	public int deleteTicketInfo(int TicketID);
	public Ticket getTicketInfoById(int ticketID);
	public Ticket getTicketInfoById(Session session, int ticketID);
	
	public List<Ticket> getAllTicketInfo();
	public List<Ticket> getAllTicketInfo(Session session);
	
	public boolean insertTicketInfo(Ticket ticket);
	public boolean insertTicketInfo(Session session, Ticket ticket);
	
	public boolean updateTicketInfo(Ticket ticket);
	public boolean updateTicketInfo(Session session, Ticket ticket);
	
	public boolean deleteTicketInfo(int ticketID);
	public boolean deleteTicketInfo(Session session, int ticketID);
}
