package springapp.controller;

import java.sql.Time;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.command.ClientCommand;
import springapp.domain.Appointment;
import springapp.domain.Client;
import springapp.service.AppointmentService;
import springapp.service.ClientService;

/**
 * This controller handles all client related pages
 *
 * Notice the @PreAuthorize annotations on the methods
 */
@Controller
@RequestMapping("/appointments") //notice that this path is set at the class level.
public class AppointmentController {

    private Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    // Inject in a ClientService claass
	@Autowired
	AppointmentService appointmentService;

    /**
     * Returns the name of the view template that should be used along witht the model to draw the list of clients
     *
     * Note that no addiontal path is specified, and that this method only handles a GET
     * @param model the model to populate for merging  with the view
     * @return the client list page template
     */
	 @PreAuthorize("hasAuthority('LIST_CLIENTS')")
	 @GetMapping
	 public String listAppointments(Model model) {      
		Map<String,Appointment> todaysAppointments = appointmentService.getTodayAppointments();		
		model.addAttribute("TodayAppointments",todaysAppointments);
        return "appointments/listAppointments";
    }


   

}
