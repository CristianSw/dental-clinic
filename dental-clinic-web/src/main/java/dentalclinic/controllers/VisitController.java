package dentalclinic.controllers;

import dentalclinic.model.Patient;
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
import java.util.Map;

@Controller
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


    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("patientId") Long patientId, Map<String, Object> model) {
        Patient patient = patientService.findById(patientId);
        model.put("patient", patient);
        Visit visit = new Visit();
        patient.getVisits().add(visit);
        visit.setPatient(patient);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/patients/*/{patientId}/visits/new")
    public String initNewVisitForm(@PathVariable("patientId") Long patientId, Model model) {
        return "patients/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/patients/{patientId}/visits/new")
    public String processNewVisitForm(@Validated Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "patients/createOrUpdateVisitForm";
        } else {
            Visit savedVisit = visitService.save(visit);

            return "redirect:/patients/" + savedVisit.getId();
        }
    }
}
