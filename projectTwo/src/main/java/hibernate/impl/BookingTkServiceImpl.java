package hibernate.impl;

import java.util.List;

import org.hibernate.Session;

import hibernate.BookingTkService;
import hibernate.bean.BookingTk;

public class BookingTkServiceImpl  implements  BookingTkService{
	
	private BookingTkDAOImpl bkDao;
	
	public BookingTkServiceImpl(Session session) {
		bkDao = new BookingTkDAOImpl(session);
	}

	@Override
	public int insert(BookingTk booking) {
		return bkDao.insert(booking);
	}

	@Override
	public BookingTk selectById(int ticketID) {
		return bkDao.selectById(ticketID);
	}

	@Override
	public List<BookingTk> selectAll() {
		return bkDao.selectAll();
	}

	@Override
	public int update(BookingTk booking) {
		return bkDao.update(booking);
	}

	@Override
	public int delete(int ticketID) {
		return bkDao.delete(ticketID);
	}

	
}
