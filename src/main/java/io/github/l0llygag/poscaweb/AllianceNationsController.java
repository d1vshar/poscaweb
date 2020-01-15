package io.github.l0llygag.poscaweb;

import io.github.adorableskullmaster.pw4j.PoliticsAndWar;
import io.github.adorableskullmaster.pw4j.domains.subdomains.NationMilitaryContainer;
import io.github.adorableskullmaster.pw4j.domains.subdomains.SNationContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/aa-nations")
public class AllianceNationsController {

    @Autowired
    PoliticsAndWar politicsAndWar;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String get() {
        return "aa-nations";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private String getNations(@PathVariable int id, Model model) {
        List<AllianceNation> nations = new ArrayList<>();
        try {
            List<SNationContainer> nationsByAlliance = politicsAndWar.getNationsByAlliance(true, id).getNationsContainer();
            List<NationMilitaryContainer> allMilitaries = politicsAndWar.getAllMilitaries().getNationMilitaries();

            for (SNationContainer nation : nationsByAlliance) {
                NationMilitaryContainer nationMilitaryContainer = allMilitaries.stream()
                        .filter(container -> container.getNationId() == nation.getNationId())
                        .findFirst()
                        .orElse(new NationMilitaryContainer());

                AllianceNation allianceNation = new AllianceNation(nation, nationMilitaryContainer);
                nations.add(allianceNation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("nations", nations);
        return "aa-nations";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    private String redirectFromHome(@RequestParam("id") int id) {
        return "redirect:/aa-nations/" + id;
    }

    @RequestMapping(value = "/**", method = RequestMethod.POST)
    private String redirect(@RequestParam("id") int id) {
        return "redirect:/aa-nations/" + id;
    }


}
