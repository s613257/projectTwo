package hibernate;

import java.util.List;

import hibernate.bean.BookingTk;

public interface BookingTkDAO {

	public int insert(BookingTk booking);
	public BookingTk selectById(int ticketID);
	public List<BookingTk> selectAll();
	public int update(BookingTk booking);
	public int delete(int ticketID);
}
