package dentalclinic.controllers;

import dentalclinic.model.Patient;
import dentalclinic.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/patients")
public class PatientsController {

    private final PatientService patientService;

    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findPatients(Model model) {
        model.addAttribute("patient", Patient.builder().build() );
        return "/patients/findPatients";
    }

    @GetMapping
    public String processFindForm(Patient patient, BindingResult result, Model model) {
        if (patient.getLastName() == null) {
            patient.setLastName("");
        }

        List<Patient> results = patientService.findByLastNameLike("%" + patient.getLastName() + "%");
        if (results.isEmpty()) {
            //no patients found
            result.rejectValue("LastName", "Not Found", "Not Found");
            return "patients/findPatients";
        } else if (results.size() == 1) {
            //1 patient found
            patient = results.get(0);
            return "redirect:/patients/" + patient.getId();
        } else {
            model.addAttribute("selections", results);
            return "patients/patientsList";
        }

    }

    @GetMapping("/{patientId}")
    public ModelAndView showPatient(@PathVariable("patientId") Long patientId) {
        ModelAndView mav = new ModelAndView("patients/patientDetails");
        mav.addObject(patientService.findById(patientId));
        return mav;
    }
}
