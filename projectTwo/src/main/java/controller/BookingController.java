package controller;

import java.io.IOException;
import db.dao.BookingDAO;
import db.dao.impl.BookingDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		request.getRequestDispatcher("/html/SelectPage.jsp").forward(request, response);
	}

}
