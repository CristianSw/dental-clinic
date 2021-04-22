package dentalclinic.controllers;

import dentalclinic.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PatientsController {

    private final PatientService patientService;

    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping({"/patients", "/pacient", "/patients/index"})
    public String index(Model model) {

        model.addAttribute("patients", patientService.findAll());
        return "/patients/index";
    }
}
