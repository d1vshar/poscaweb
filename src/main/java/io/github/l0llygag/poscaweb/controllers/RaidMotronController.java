package io.github.l0llygag.poscaweb.controllers;

import io.github.l0llygag.poscaweb.database.NationRepositoryCustom;
import io.github.l0llygag.poscaweb.database.models.Nation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/RaidMotron")
public class RaidMotronController {

    private final NationRepositoryCustom nationRepositoryCustom;

    public RaidMotronController(NationRepositoryCustom nationRepositoryCustom) {
        this.nationRepositoryCustom = nationRepositoryCustom;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String get(@RequestParam(required = false) Integer score,
                       @RequestParam(required = false) Integer allianceId,
                       @RequestParam(required = false) String includeBeige,
                       @RequestParam(required = false) String includeVM,
                       @RequestParam(required = false) String includeSlotted,
                       Model model) {

        Integer aid = -1;
        boolean beige = false, vm = false, slotted = false;

        if (score != null) {

            double high = score * 1.750;
            double low = score * 0.750;

            if (allianceId != null) aid = allianceId;
            if (includeBeige != null) beige = true;
            if (includeVM != null) vm = true;
            if (includeSlotted != null) slotted = true;

            List<Nation> nations = nationRepositoryCustom.findByScoreAndAllianceId(low, high, aid, beige, vm, slotted);

            model.addAttribute("nations", nations);
        }
        String title = "RaidMotron";
        model.addAttribute("title", title);
        return "RaidMotron";
    }
}
