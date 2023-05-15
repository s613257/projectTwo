package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		// System.out.println(service);

		switch (service) {
		case "GetTranInfo":
			GetTranInfo(request, response);
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

	private void GetTranInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String departure_ST = request.getParameter("departure_ST");
			String destination_ST = request.getParameter("destination_ST");
			String departure_time = request.getParameter("departure_time");

			BookingDAO bookingDAO = new BookingDAOImpl();
			List<TicketDTO> tranInfos = bookingDAO.getAllTranInfo();
			List<TicketDTO> tranTimeLst = new ArrayList<TicketDTO>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

			Date departureTime = sdf.parse(departure_time);
			for (TicketDTO tranInfo : tranInfos) {
				if (tranInfo.getDeparture_ST().equals(departure_ST) && 
						tranInfo.getDestination_ST().equals(destination_ST)) {
					Date departureStTime = sdf.parse(tranInfo.getDeparture_time());
					if(departureTime.compareTo(departureStTime) < 0) {
						tranTimeLst.add(tranInfo);
					}
				}
			}
			String json = new Gson().toJson(tranTimeLst);
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
			String json = String.format("{\"msg\":\"%s(id=%s)\"}", rowCnt > 0 ? "刪除成功" : "刪除失敗", id);
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
