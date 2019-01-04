package com.apextrio.peak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class TeamController {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private TeamRepository teamRepo;

    //Gets the team from the database based on the id in the path. Returns that to be parsed/rendered.
    @GetMapping("/teams/{id}")
    public String showTeam(@PathVariable long id, Model m, Principal p) {
        Team t = teamRepo.findById(id).get();
        if (p != null) {
            AppUser user = (AppUser) (((UsernamePasswordAuthenticationToken) p).getPrincipal());
            user = userRepo.findById(user.getId()).get();
            if (t.users.contains(user)) {
                m.addAttribute("inGroup", true);
            } else {
                m.addAttribute("inGroup", false);
            }
        }
        m.addAttribute("team", t);
        return "team";
    }


    //Takes in the team id from the path and the principal of the request. Adds that user as a member of that team
    @PostMapping("/teams/{id}")
    public RedirectView addUser(@PathVariable long id, Principal p) {
        if (p==null) {
            RedirectView rv = new RedirectView("/error");
            rv.setStatusCode(HttpStatus.FORBIDDEN);
            return rv;
        }
        Team t = teamRepo.findById(id).get();
        AppUser joining = (AppUser) (((UsernamePasswordAuthenticationToken) p).getPrincipal());
        t.users.add(userRepo.findById(joining.getId()).get());
        teamRepo.save(t);
        return new RedirectView("/teams/" + id);
    }

    @GetMapping("/newteam")
    public String teamForm(@RequestParam long resortId) {
        return "teamForm";
    }
}
