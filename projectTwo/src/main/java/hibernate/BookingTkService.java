package hibernate;

import java.util.List;

import hibernate.bean.BookingTk;
import model.dto.TicketDTO;

public interface BookingTkService {
	public BookingTk getBookingTkById(int ticketID);
	public List<BookingTk> getAllBookingTk();
	public boolean updateTicketInfo(TicketDTO ticketDto);
	public boolean insertTicketInfo(TicketDTO ticketDto);
}
