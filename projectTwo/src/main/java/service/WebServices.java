package service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import model.dto.TicketDTO;


public class WebServices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WebServices() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String service = request.getParameter("service");
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
				if (tranInfo.getDepartureST().equals(departure_ST) && 
						tranInfo.getDestinationST().equals(destination_ST)) {
					Date departureStTime = sdf.parse(tranInfo.getDeparturetime());
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
			Map<String, List<List<String>>> inputMap = new HashMap<String, List<List<String>>>();
			List<List<String>> dataList = new ArrayList<List<String>>();
			inputMap.put("data", dataList);
			for(TicketDTO tk :tks) {
				List<String> tkDataLst = new ArrayList<String>();
				tkDataLst.add(Integer.toString(tk.getTicketID()));
				tkDataLst.add(tk.getTranNo());
				tkDataLst.add(tk.getSeat());
				tkDataLst.add(tk.getDepartureST());
				tkDataLst.add(tk.getDestinationST());
				tkDataLst.add(tk.getDeparturedate());
				tkDataLst.add(tk.getDeparturetime());
				tkDataLst.add(tk.getArrivaltime());
				tkDataLst.add(Integer.toString(tk.getPrice()));
				tkDataLst.add(tk.getBookingdate());
				tkDataLst.add("<button class=\"btn btn-light\" onclick=\"updateTarget(" + tk.getTicketID() + ")\"><i class=\"fa-solid fa-pen-to-square\"></i> </button>");
				tkDataLst.add("<button class=\"btn btn-light\" onclick=\"deleteTarget(" + tk.getTicketID() + ")\"><i class=\"fa-solid fa-trash-can\"></i> </button>");
				dataList.add(tkDataLst);
			}
			
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			String json = new Gson().toJson(inputMap);
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
