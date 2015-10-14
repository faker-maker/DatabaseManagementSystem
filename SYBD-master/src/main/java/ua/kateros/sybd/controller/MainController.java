package ua.kateros.sybd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping("/")
    public String mainMenu() {
        return "mainmenu";
    }
    @RequestMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}