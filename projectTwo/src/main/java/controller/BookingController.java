package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import model.Utils;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) {
			action = "";
		}
		switch (action) {
		case Utils.ACTION_CREATE:
			create(request, response);
			break;
		case Utils.ACTION_QUERY:
			query(request, response);
			break;
		case Utils.ACTION_UPDATE:
			update(request, response);
			break;
		case Utils.ACTION_DELETE:
			delete(request, response);
			break;

		default:
			index(request, response);
			break;
		}		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingDAO bookingDAO = new BookingDAOImpl();
		request.setAttribute("stationList", bookingDAO.getAllStationInfo());
		request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
		request.getRequestDispatcher("/html/ChoosePage.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void query(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
}
