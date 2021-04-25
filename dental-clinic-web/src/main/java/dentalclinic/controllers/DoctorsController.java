package dentalclinic.controllers;

import dentalclinic.services.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DoctorsController {

    private final DoctorService doctorService;

    public DoctorsController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @RequestMapping({"/doctors/index.html", "/docs.html", "/doctors"})
    public String index(Model model) {

        model.addAttribute("doctors", doctorService.findAll());
        return "/doctors/index";
    }
}
