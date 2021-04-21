package dentalclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DoctorsController {
    @RequestMapping({"/doctors/index.html", "/docs", "/doctors"})
    public String index() {
        return "/doctors/index";
    }
}
