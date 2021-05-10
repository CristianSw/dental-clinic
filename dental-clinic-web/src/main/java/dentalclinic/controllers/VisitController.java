package dentalclinic.controllers;

import dentalclinic.model.Visit;
import dentalclinic.services.PatientService;
import dentalclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;


@Controller
@RequestMapping("/patients/{patientId}/visits")
public class VisitController {
    private final VisitService visitService;
    private final PatientService patientService;

    public VisitController(VisitService visitService, PatientService patientService) {
        this.visitService = visitService;
        this.patientService = patientService;

    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }


    @GetMapping("/new")
    public String initNewVisitForm(@PathVariable("patientId") Long patientId, Model model) {
        model.addAttribute("visit", new Visit());
        model.addAttribute("patient", patientService.findById(patientId));
        return "patients/createOrUpdateVisitForm";
    }


    @PostMapping("/new")
    public String processNewVisitForm(@Validated Visit visit, @PathVariable("patientId") Long patientId
            , BindingResult result) {
        if (result.hasErrors()) {
            return "patients/createOrUpdateVisitForm";
        } else {
            visit.setPatient(patientService.findById(patientId));
            Visit savedVisit = visitService.save(visit);
            return "redirect:/patients/" + savedVisit.getPatient().getId();
        }
    }
}
