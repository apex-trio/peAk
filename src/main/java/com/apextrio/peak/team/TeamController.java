package com.apextrio.peak.team;

import com.apextrio.peak.appuser.AppUser;
import com.apextrio.peak.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
public class TeamController {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private TeamRepository teamRepo;

    //Gets the team from the database based on the id in the path. Returns that to be parsed/rendered.
    @GetMapping("/teams/{id}")
    public String showTeam(@PathVariable long id, Model m, Principal p, HttpServletResponse response) {
        Optional<Team> t = teamRepo.findById(id);
        if(t.isPresent()) {
            Team team = t.get();
            if (p != null) {
                AppUser user = (AppUser) (((UsernamePasswordAuthenticationToken) p).getPrincipal());
                user = userRepo.findById(user.getId()).get();
                if (team.users.contains(user)) {
                    m.addAttribute("inGroup", true);
                } else {
                    m.addAttribute("inGroup", false);
                }
            }
            m.addAttribute("team", team);
            return "team";
        } else {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No team found");
            } catch (IOException e) {
                System.out.println(e);
            }
            return "error";
        }
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

    @DeleteMapping("/teams/{id}")
    public RedirectView removeUser(@PathVariable long id, Principal p) {
        AppUser user = userRepo.findByUsername(p.getName());
        Team t = teamRepo.findById(id).get();
        t.users.remove(user);
        if (t.users.size() == 0) {
            teamRepo.delete(t);
            return new RedirectView("/");
        } else {
            teamRepo.save(t);
            return new RedirectView("/teams/" + id);
        }

    }
}
