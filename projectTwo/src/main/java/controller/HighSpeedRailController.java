package controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import db.dao.BookingDAO;
import db.dao.BaseDAO_Hibernate;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import db.service.HighSpeedRailService;
import db.service.impl.HighSpeedRailServiceImpl;
import model.dto.HighSpeedRailTicket;
import model.dto.Ticket;

@Controller
@RequestMapping("/highSpeedRail")
public class HighSpeedRailController {

	@Autowired
	@Qualifier("bookingDAOImpl")
	private BookingDAO bookingDAO;

	@Autowired
	@Qualifier("ticketDAOImpl")
	private TicketDAO ticketDAO;

	@Autowired
	@Qualifier("highSpeedRailServiceImpl")
	private HighSpeedRailService highSpeedRailService;

	@GetMapping("")
	public String index(Model model) {
		return "BookingAdmin";
	}

	@GetMapping("/insert")
	public String insert(Model model) {

		model.addAttribute("stationList", bookingDAO.getAllStationInfo());
		model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
		//model.addAttribute("ticketDto", new TicketDTO());
		return "BookingAdminDataPage";
	}

	@PostMapping("/doinsert")
    public String doinsert(@ModelAttribute("ticketDto") Ticket ticketDto) {
        highSpeedRailService.insertTicketInfo(ticketDto);
        return "redirect:/BookingAdmin";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Ticket ticketDto = ticketDAO.getTicketInfoById(id);
        model.addAttribute("stationList", bookingDAO.getAllStationInfo());
        model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
        model.addAttribute("ticketDto", ticketDto);
        return "BookingAdminDataPage";
    }

    @PostMapping("/doupdate")
    public String doupdate(@ModelAttribute("ticketDto") Ticket ticketDto) {
        highSpeedRailService.updateTicketInfo(ticketDto);
        return "redirect:/BookingAdmin";
    }
}
