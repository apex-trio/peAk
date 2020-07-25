package com.apextrio.peak.resort;


import com.apextrio.peak.team.Team;
import com.apextrio.peak.team.TeamRepository;
import com.apextrio.peak.appuser.AppUser;
import com.apextrio.peak.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
public class ResortController {

    //---Repo variables for access to the database---
    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private ResortRepository resortRepo;

    @Autowired
    private AppUserRepository userRepo;

    //---Route Mapping methods---

    //Utilized by the ajax call on the front end, retrieves information for a specific resort and returns it in JSON format
    @GetMapping("/resorts/{id}")
    public Resort getResort(@PathVariable long id){
        return resortRepo.findById(id).get();
    }

    //Route used for adding a team to a specific resort and adding it to the database
    //It creates a team, grabs the user who made the request and adds them to the users on the team, then saves the team to the database
    //Afterwords, it will redirect the user to the detail page for that new team
    @PostMapping("/resorts/{id}")
    public RedirectView createTeam(Team t, @PathVariable long id, @RequestParam String dateGoing, Principal p) {
        if (p==null) {
           RedirectView rv = new RedirectView("/error");
           rv.setStatusCode(HttpStatus.FORBIDDEN);
           return rv;
        }
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
