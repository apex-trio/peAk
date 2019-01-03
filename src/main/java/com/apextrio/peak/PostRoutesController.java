package com.apextrio.peak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//THIS FILE IS TEMPORARY. IT ONLY HOLDS POST HANDLERS TO BE USED IN OTHER CONTROLLERS AS THEY BECOME NEEDED
@Controller
public class PostRoutesController {
    @Autowired
    public AppUserRepository userRepo;

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
    @PostMapping("/resorts/{id}")
    public RedirectView createTeam(Team t, @PathVariable long id, @RequestParam String dateGoing, Principal p) {
        AppUser user = (AppUser) (((UsernamePasswordAuthenticationToken) p).getPrincipal());
        user = userRepo.findById(user.getId()).get();
        Resort r = resortRepo.findById(id).get();
        LocalDateTime now = LocalDateTime.now();
        t.resort = r;
        t.setDateCreated(now.format(DateTimeFormatter.ofPattern("M/d/yy h:mma")));
        LocalDateTime going = LocalDateTime.parse(dateGoing);
        t.setDateGoing(going.format(DateTimeFormatter.ofPattern("M/d/yy h:mma")));
        t.users.add(user);
         t = teamRepo.save(t);
        return new RedirectView("/teams/" + t.getId());
    }
}

