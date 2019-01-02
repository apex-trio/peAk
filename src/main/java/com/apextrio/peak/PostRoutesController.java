package com.apextrio.peak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//THIS FILE IS TEMPORARY. IT ONLY HOLDS POST HANDLERS TO BE USED IN OTHER CONTROLLERS AS THEY BECOME NEEDED
@Controller
public class PostRoutesController {
    @Autowired
    public AppUserRepository appUserRepo;

    @Autowired
    public TeamRepository teamRepo;

    @Autowired
    public ResortRepository resortRepo;

    //Will need to take in [String name, float latitude, float longitude, String website] on instantiation
    @PostMapping("/resorts")
    public RedirectView createResort(Resort r) {
        resortRepo.save(r);
        return new RedirectView("/");
    }

    //Route should be based on  a resorts id so the group can be assigned to the proper resort. Will take in name, capacity, and difficulty.
    @PostMapping("/resorts/{id}/teams")
    public RedirectView createTeam(Team t, @PathVariable long id) {
        Resort r = resortRepo.findById(id).get();
        LocalDateTime now = LocalDateTime.now();
        t.resort = r;
        t.setDateCreated(now.format(DateTimeFormatter.ofPattern("MM/dd/yy hh:mma")));
        t.setDateGoing(now.format(DateTimeFormatter.ofPattern("MM/dd/yy hh:mma")));
        teamRepo.save(t);
        return new RedirectView("/");
    }
}

