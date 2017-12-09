package estateagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexGet() {
        return "views/index";
    }

    @GetMapping("/other")
    public String getOther() {
        return "views/other";
    }
}
