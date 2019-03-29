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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springapp.command.AppointmentCommand;
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
	 public String listAppointments(@RequestParam(name="date",required=false) String date,Model model) {
		 String currentDate = appointmentService.getTodayDate();
		 if(date != null){			 
			 Map<String,Appointment> appoinments = appointmentService.getAppointmentForGivenDate(date);
			 model.addAttribute("TodayAppointments",appoinments);			 
			 model.addAttribute("currentDate",appointmentService.getFormattedDate(date));			
			 model.addAttribute("dates",appointmentService.getPastCurrentFutureDate(date));
		 }
		 else {			 
			 Map<String,Appointment> todaysAppointments = appointmentService.getTodayAppointments();		
			 model.addAttribute("TodayAppointments",todaysAppointments);						 
			 model.addAttribute("currentDate",appointmentService.getFormattedDate(currentDate));
			 model.addAttribute("dates",appointmentService.getPastCurrentFutureDate(currentDate));
		 }		
		
        return "appointments/listAppointments";
    }
	 
	    /**
	     * Generates the model for rendering the specific client page
	     * @param id the id of the client we want to render, if the value is 'new' that is for creating a new client
	     * @param model the model to populate for merging with the view
	     * @return the client edit page template
	     */
		 @PreAuthorize("hasAuthority('GET_CLIENT')")
		 @GetMapping("/{id}")
		 public String getAppointment(@PathVariable("id") String id, Model model, boolean saved) {

			 model.addAttribute("saved", saved);
			
		    if(id.equals("new")) {
		        // create an empty command object to merge with the view template
				model.addAttribute("command", new AppointmentCommand(null));	
			} else {
		        // since we have a valid id, get the client object from the service
				Appointment appointment = appointmentService.getAppointment(id);
				// we create a client command that can be used when the browser sends the save object
				model.addAttribute("command", new AppointmentCommand(appointment));				
			}
			return "appointments/editAppointment";
		}

	 /**
     * Saves the updates to a client based on the command that was sent from the client side
     * @param command the command corresponding with how the client object should be updated/created
     * @param redirectAttributes holds the attribtues that we may want to pass to the get page after a save
     * @return the edit client view template
     */
	 @PreAuthorize("hasAuthority('SAVE_APPOINTMENT')")
	 @PostMapping
	 public String saveAppointment(AppointmentCommand command, RedirectAttributes redirectAttributes) {

	     //NOTE: if we want to capture errors correctly, we would wrap the following code in a try/catch
         // and the catch would add a nice error message to the mode
         // then the view template would render a nice error message

	     // we pass the command to the service, and it nows how update/create a appointment
         // the service returns the new appointment object back to us after the save
	     Appointment appointment =appointmentService.saveAppointment(command);
	     
	     // we add in a "saved" attribute so we can print a nice message indicating a save was successfull
		 redirectAttributes.addAttribute("saved", true);


	     return "redirect:/appointments/"+appointment.getId();
		  
     }
	 
    /**
     * Deletes a appointment and redirects to appointments page
     * @param id the id of the appointment to delete
     * @param redirectAttributes we use this instead of a Model object, because we want to pass
     *                           some attributes to the list page
     * @return redirect path to the list appointment page
     */
     @PreAuthorize("hasAuthority('DELETE_APPOINTMENT')")
	 @GetMapping("/{id}/delete")
	 public String deleteAppointment(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
         // NOTE to handle exceptions, we would wrap the following code in a try/catch
         // and in the catch forward to a different page

         // send the id passed in to the appointment service
         appointmentService.deleteAppointment(id);

         // add an attribute to the list page, so a nice message can be shown
         redirectAttributes.addFlashAttribute("deleted", true);
		 // test

         // redirect to list appointment path/page
         return "redirect:/appointments";
    }
     

}
