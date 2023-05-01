package db.dao;

import java.util.List;

import model.dto.TicketDTO;

public interface TicketDAO {

	public List<TicketDTO> getAllTicketInfo();
	public int insertTicketInfo(TicketDTO data);
	public int updateTicketInfo(TicketDTO dataSource);
	public int deleteTicketInfo(int TicketID);
}
