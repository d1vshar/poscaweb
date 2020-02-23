package io.github.l0llygag.poscaweb.controllers;

import io.github.l0llygag.poscaweb.database.NationRepository;
import io.github.l0llygag.poscaweb.database.models.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/AllianceStats")
public class AllianceStatsController {

    private final NationRepository nationRepository;

    @Autowired
    public AllianceStatsController(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String getNations(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            List<Nation> nations = nationRepository.findByAllianceIdOrderByScoreDesc(id);
            model.addAttribute("nations", nations);
        }
        model.addAttribute("title", "Alliance Nations");
        return "AllianceStats";
    }

}
