package controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import db.dao.HighSpeedRailService;
import db.dao.TicketDAO;
import model.dto.HighSpeedRailTicket;
import model.dto.TicketDTO;

@Controller
@RequestMapping("/highSpeedRail")
public class HighSpeedRailController {

	@Autowired
	private BookingDAO bookingDAO;

	@Autowired
	private TicketDAO ticketDAO;

	@Autowired
	private HighSpeedRailService highSpeedRailService;

	@GetMapping("")
	public String index(Model model) {
		return "bookingAdmin";
	}

	@GetMapping("/insert")
	public String showInsertForm(Model model) {
		model.addAttribute("stationList", bookingDAO.getAllStationInfo());
		model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
		model.addAttribute("ticketDto", new TicketDTO());
		return "bookingAdminDataPage";
	}

	@PostMapping("/insert")
    public String insert(@ModelAttribute("ticketDto") TicketDTO ticketDto) {
        highSpeedRailService.insertTicketInfo(ticketDto);
        return "redirect:/bookingadmin";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model, @RequestParam("id") int id) {
        TicketDTO ticketDto = ticketDAO.getTicketInfoById(null, id);
        model.addAttribute("stationList", bookingDAO.getAllStationInfo());
        model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
        model.addAttribute("ticketDto", ticketDto);
        return "bookingAdminDataPage";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("ticketDto") TicketDTO ticketDto) {
        highSpeedRailService.updateTicketInfo(ticketDto);
        return "redirect:/bookingadmin";
    }
}
