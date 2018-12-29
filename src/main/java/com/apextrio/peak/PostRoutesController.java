package com.apextrio.peak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

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
        t.resort = r;
        t.setDateCreated(new Date());
        t.setDateGoing(new Date());
        teamRepo.save(t);
        return new RedirectView("/");
    }

    //This should eventually use the principal instead of a user ID when proper setup
    @PostMapping("/teams/{id}")
    public RedirectView addUser(@RequestParam long userId, @PathVariable long id) {
        Team t = teamRepo.findById(id).get();
        t.users.add(appUserRepo.findById(userId).get());
        teamRepo.save(t);
        return new RedirectView("/");
    }
}

