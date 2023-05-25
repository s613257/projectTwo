package db.dao;

import java.util.List;

import hibernate.bean.BookingTk;
import model.dto.TicketDTO;

public interface TicketDAO {

//	public List<TicketDTO> getAllTicketInfo();
//	public TicketDTO GetTicketInfoById(String id);
//	public int insertTicketInfo(TicketDTO data);
//	public int updateTicketInfo(TicketDTO dataSource);
//	public int deleteTicketInfo(int TicketID);
	public TicketDTO insertTicketInfo(TicketDTO ticket);
	public TicketDTO getTicketInfoById(int ticketID);
	public List<TicketDTO> getAllTicketInfo();
	public TicketDTO updateTicketInfo(TicketDTO ticket);
	public boolean deleteTicketInfo(int ticketID);
}
