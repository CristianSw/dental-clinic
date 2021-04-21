package dentalclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PatientsController {
    @RequestMapping({"/patients","/pacient","/patients/index"})
    public String index(){
        return "/patients/index";
    }
}
