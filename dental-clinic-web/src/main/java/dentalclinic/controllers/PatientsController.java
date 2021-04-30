package dentalclinic.controllers;

import dentalclinic.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patients")
public class PatientsController {

    private final PatientService patientService;

    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping({"/patients", "/pacient", "/patients/index.html"})
    public String index(Model model) {

        model.addAttribute("patients", patientService.findAll());
        return "/patients/index";
    }
    @RequestMapping("/patients/find")
    public String findPatients(Model model){
        return "NotImplemented";
    }
    @GetMapping("/{patientId}")
    public ModelAndView showPatient(@PathVariable("patientId") Long patientId){
        ModelAndView mav = new ModelAndView("patients/patientDetails");
        mav.addObject(patientService.findById(patientId));
        return mav;
    }
}
