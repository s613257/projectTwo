package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
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
			TicketDAO tkaDao = new TicketDAOImpl();
			TicketDTO tkdto = getTicketDTOByReq(request);
			tkaDao.insertTicketInfo(tkdto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		/*   1. 在BookingAdmin.jsp點擊事件。
		 *   2. 跳轉BookingAdminDataPage.jsp，show 當前編輯資料。
		 * 	 3. 在BookingAdminDataPage編輯完成，跳轉回BookingAdmin.jsp頁面。
		 */
		
		try {
			String id = request.getParameter("id");
			TicketDAO tkaDao = new TicketDAOImpl();
			BookingDAO bookingDAO = new BookingDAOImpl();
			TicketDTO tkdto = tkaDao.GetTicketInfoById(id);
		
			request.setAttribute("stationList", bookingDAO.getAllStationInfo());
			request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
			request.setAttribute("tkdto", tkdto);
			request.getRequestDispatcher("/html/BookingAdminDataPage.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		
	}
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDAO tkaDao = new TicketDAOImpl();
			TicketDTO tkdto = getTicketDTOByReq(request);
			tkaDao.updateTicketInfo(tkdto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	private TicketDTO getTicketDTOByReq(HttpServletRequest request) {
		TicketDTO tkdto = new TicketDTO();
		String ticketID = request.getParameter("ticketID");
		tkdto.setTicketID(Integer.parseInt(ticketID));
		String tranNo = request.getParameter("tranNo");
		tkdto.setTranNo(tranNo);
		String seat = request.getParameter("seat");
		tkdto.setSeat(seat);
		String departure_ST = request.getParameter("departure_ST");
		tkdto.setDeparture_ST(departure_ST);
		String destination_ST = request.getParameter("destination_ST");
		tkdto.setDestination_ST(destination_ST);
		String depture_date = request.getParameter("depture_date");
		tkdto.setdeparture_date(depture_date);
		String departure_time = request.getParameter("departure_time");
		tkdto.setDeparture_time(departure_time);
		String arrival_time = request.getParameter("arrival_time");
		tkdto.setArrival_time(arrival_time);
		String price = request.getParameter("price"); 
		tkdto.setPrice(Integer.parseInt(price));
		String booking_date = request.getParameter("booking_date");
		tkdto.setbooking_date(booking_date);
		return tkdto;
	}
}
