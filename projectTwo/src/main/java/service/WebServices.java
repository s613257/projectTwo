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
import db.dao.impl.BookingDAOImpl;
import model.dto.TranInfoDTO;

public class WebServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WebServices() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String params = request.getParameter("params");
		
		
		BookingDAO bookingDAO = new BookingDAOImpl();
		List<TranInfoDTO> tranInfos = bookingDAO.getAllTranInfo();
		String json = new Gson().toJson(tranInfos );
		response.getWriter().append(json);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
