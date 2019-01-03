package com.apextrio.peak;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
public class ResortController {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private ResortRepository resortRepo;

    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/resorts/{id}")
    public Resort getResort(@PathVariable long id){
        return resortRepo.findById(id).get();
    }

    //Route should be based on  a resorts id so the group can be assigned to the proper resort. Will take in name, capacity, and difficulty.
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
