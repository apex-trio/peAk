package com.apextrio.peak.appuser;

import com.apextrio.peak.resort.ResortRepository;
import com.apextrio.peak.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class AppUserController {

    ///////////////////////////////// -- Controller instance variables

    @Autowired
    public AppUserRepository appUserRepo;

    @Autowired
    public TeamRepository teamRepo;

    @Autowired
    public ResortRepository resortRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    ///////////////////////////////// -- simple GET routes

    //Gets all resorts from the database and adds it to the view. These resorts include a list of all teams registered to them and within each team they contain a list of attending users
    @GetMapping("/")
    public String getHomePage(Model m) {
        m.addAttribute("locations", resortRepo.findAll());
        return "index";
    }

    //Gives the login view
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    //Gives the signup view. If coming from a team page, it holds that info as a request query (actually used within the post route)
    @GetMapping("/signup")
    public String getSignup(@RequestParam(required = false) Long teamId) {
        return "sign_up";
    }

    //Grabs the current logged in users info, adds it to the my_profile view and returns the view
    @GetMapping("/myProfile")
    public String showMyProfile(Principal p, Model m) {
        AppUser currentUser = appUserRepo.findByUsername(p.getName());
        m.addAttribute("user", currentUser);
        return "my_profile";
    }


    ///////////////////////////////// -- POST routes

    //Takes in a new users sign up information, adds them to the database and signs them in. If they came from a redirect off a team page, sends them back to that page
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public RedirectView addUsers(AppUser user, @RequestParam(required = false) Long teamId) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        appUserRepo.save(user);

        System.out.println("this is our teamId" + teamId);
        if(teamId == null) {
            return new RedirectView("/");
        } else {
            return new RedirectView("/teams/" + teamId);
        }
    }

    //Takes in the updated bio string from the user and makes that change in the database, returns the user to their profile which will show the updated info
    @PostMapping ("/updateProfile")
    public RedirectView updateProfileBio(@RequestParam String bioUpdate, Principal p) {

        AppUser currentUser = appUserRepo.findByUsername(p.getName());

        currentUser.setBio(bioUpdate);

        appUserRepo.save(currentUser);
        return new RedirectView("/myProfile");
    }

}
