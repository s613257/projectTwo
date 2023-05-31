package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.service.HighSpeedRailService;
import model.dto.TicketInfo;

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
	private HighSpeedRailService ticketService;

	@GetMapping("")
	public String index(Model model) {
		return "BookingAdmin";
	}

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("stationList", bookingDAO.getAllStationInfo());
		model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
//		model.addAttribute("ticketDto", new TicketInfo());
		return "BookingAdminDataPage";
	}

    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") String id) {
        TicketInfo ticketDto = ticketDAO.getTicketInfoById(Integer.parseInt(id));
        model.addAttribute("stationList", bookingDAO.getAllStationInfo());
        model.addAttribute("priceInfos", bookingDAO.getAllPriceInfo());
        model.addAttribute("ticketDto", ticketDto);
        return "BookingAdminDataPage";
    }

    @PostMapping("/doAction")
    public String doinsert(@ModelAttribute("ticketDto") TicketInfo ticketDto, @RequestParam("action")String action) {
    	if(action.equals("doInsert")) {
    		ticketService.insertTicketInfo(ticketDto);
    	}else if(action.equals("doUpdate")) {
    		ticketService.updateTicketInfo(ticketDto);
    	}
        return "redirect:/highSpeedRail";
    }

}
