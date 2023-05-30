package controller;

import java.io.IOException;

import org.hibernate.Session;

import db.dao.BookingDAO;
import db.dao.BaseDAO_Hibernate;
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
//		default:
//			index(request, response);
//			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
//	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Session session = BaseDAO_Hibernate.getInstance();
//		try {
//			session.beginTransaction();
//		BookingDAO bookingDAO = new BookingDAOImpl();
//		request.setAttribute("stationList", bookingDAO.getAllStationInfo(session));
//		request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
//		request.getRequestDispatcher("/WEB-INF/pages/SelectPage.jsp").forward(request, response);
//		} catch (IOException | ServletException e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			BaseDAO_Hibernate.closeSessionFactory();
//		}
//	}

}
