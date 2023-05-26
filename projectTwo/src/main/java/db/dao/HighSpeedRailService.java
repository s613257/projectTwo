package db.dao;

import java.util.List;

import model.dto.HighSpeedRailTicket;
import model.dto.TicketDTO;

public interface HighSpeedRailService {
	public HighSpeedRailTicket getBookingTkById(int ticketID);
	public List<HighSpeedRailTicket> getAllBookingTk();
	public boolean updateTicketInfo(TicketDTO ticketDto);
	public boolean insertTicketInfo(TicketDTO ticketDto);
}
