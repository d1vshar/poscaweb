package io.github.l0llygag.poscaweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    private String get(Model model) {
        String title = "Home";
        model.addAttribute("title", title);
        return "home";
    }
}
