package hibernate;

import java.util.List;

import hibernate.bean.BookingTk;
import model.dto.TicketDTO;

public interface BookingTkDAO {

	public TicketDTO insert(BookingTk booking);
	public BookingTk selectById(int ticketID);
	public List<BookingTk> selectAll();
	public TicketDTO update(BookingTk booking);
	public boolean delete(int ticketID);
}
