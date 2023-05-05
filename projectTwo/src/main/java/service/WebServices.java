package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import model.dto.TicketDTO;
import model.dto.TranInfoDTO;


public class WebServices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WebServices() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service = request.getParameter("service");
		//System.out.println(service);
		switch (service) {
		case "GetAllTranInfo":
			GetAllTranInfo(request, response);
			break;
		case "GetAllTicketInfo":
			GetAllTicketInfo(request, response);
			break;
		case "DeleteTicketInfo":
			DeleteTicketInfo(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void GetAllTranInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String params = request.getParameter("params");
			BookingDAO bookingDAO = new BookingDAOImpl();
			List<TranInfoDTO> tranInfos = bookingDAO.getAllTranInfo();
			String json = new Gson().toJson(tranInfos);
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void GetAllTicketInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDAO ticketDao = new TicketDAOImpl();
			List<TicketDTO> tks = ticketDao.getAllTicketInfo();
			String json = new Gson().toJson(tks);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void DeleteTicketInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			TicketDAO ticketDao = new TicketDAOImpl();
			int rowCnt = ticketDao.deleteTicketInfo(Integer.parseInt(id));
			String json = String.format("{\"msg\":\"%s(id=%s)\"}", rowCnt>0? "刪除成功":"刪除失敗", id);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
