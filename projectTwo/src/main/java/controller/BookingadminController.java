package controller;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import hibernate.BookingTkService;
import hibernate.HibernateUtil;
import hibernate.bean.BookingTk;
import hibernate.impl.BookingTkServiceImpl;
import model.Utils;
import model.dto.TicketDTO;

@WebServlet("/BookingadminController")
public class BookingadminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingadminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action = " + action);
		if (action == null) {
			action = "";
		}
		switch (action) {
		case Utils.ACTION_INSERT:
			insert(request, response);
			break;
		case "doInsert":
			doInsert(request, response);
			break;
		case Utils.ACTION_UPDATE:
			update(request, response);
			break;
		case "doUpdate":
			doUpdate(request, response);
			break;
		default:
			index(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookingDAO bookingDAO = new BookingDAOImpl();
			request.setAttribute("stationList", bookingDAO.getAllStationInfo());
			request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
			request.getRequestDispatcher("/html/BookingAdminDataPage.jsp").forward(request, response); // 這裡就跳轉到新增畫面了
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void doInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDTO tketDto = getTicketDTOByReq(request);
			BookingTkService bts = new BookingTkServiceImpl();
			bts.insertTicketInfo(tketDto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1. 在BookingAdmin.jsp點擊事件。 2. 跳轉BookingAdminDataPage.jsp，show 當前編輯資料。 3.
		 * 在BookingAdminDataPage編輯完成，跳轉回BookingAdmin.jsp頁面。 4. !!!注意參數名稱!!!
		 */

		Session session = HibernateUtil.getInstance();
		try {
			session.beginTransaction();
			String id = request.getParameter("id");

			TicketDTO ticketDto = (new TicketDAOImpl()).getTicketInfoById(session, Integer.parseInt(id));
			BookingDAO bookingDAO = new BookingDAOImpl();
			request.setAttribute("stationList", bookingDAO.getAllStationInfo());
			request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
			request.setAttribute("ticketDto", ticketDto);
			request.getRequestDispatcher("/html/BookingAdminDataPage.jsp").forward(request, response);
			session.getTransaction().commit();
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDTO tketDto = getTicketDTOByReq(request);
			BookingTkService bts = new BookingTkServiceImpl();
			bts.updateTicketInfo(tketDto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();

		}
	}

	private TicketDTO getTicketDTOByReq(HttpServletRequest request) {
		TicketDTO bookingTk = new TicketDTO();
		String ticketID = request.getParameter("ticketID");
		bookingTk.setTicketID(Integer.parseInt(ticketID));
		String tranNo = request.getParameter("tranNo");
		bookingTk.setTranNo(tranNo);
		String seat = request.getParameter("seat");
		bookingTk.setSeat(seat);
		String departureST = request.getParameter("departureST");
		bookingTk.setDepartureST(departureST);
		String destinationST = request.getParameter("destinationST");
		bookingTk.setDestinationST(destinationST);
		String depturedate = request.getParameter("depturedate");
		bookingTk.setdeparturedate(depturedate);
		String departuretime = request.getParameter("departuretime");
		bookingTk.setDeparturetime(departuretime);
		String arrivaltime = request.getParameter("arrivaltime");
		bookingTk.setArrivaltime(arrivaltime);
		String price = request.getParameter("price");
		bookingTk.setPrice(Integer.parseInt(price));
		String bookingdate = request.getParameter("bookingdate");
		bookingTk.setbookingdate(bookingdate);
		return bookingTk;
	}
}
