package db.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import model.dto.HighSpeedRailTicket;
import model.dto.Ticket;


public interface HighSpeedRailService {
	public HighSpeedRailTicket getBookingTkById(int ticketID);
	public List<HighSpeedRailTicket> getAllBookingTk();
	public boolean updateTicketInfo(Ticket ticketDto);
	public boolean insertTicketInfo(Ticket ticketDto);
}
