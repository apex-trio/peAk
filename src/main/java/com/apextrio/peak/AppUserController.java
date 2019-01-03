package com.apextrio.peak;

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

    @GetMapping("/")
    public String getHomePage(Model m) {
        m.addAttribute("locations", resortRepo.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup(@RequestParam(required = false) Long teamId) {
        return "sign_up";
    }

    @GetMapping("/myProfile")
    public String showMyProfile(Principal p, Model m) {
        AppUser currentUser = appUserRepo.findByUsername(p.getName());
        m.addAttribute("user", currentUser);
        return "my_profile";
    }


    ///////////////////////////////// -- POST routes


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

    @RequestMapping(value = "/signup/{teamId}", method = RequestMethod.GET)
    public String signUpFromGroupPage(@PathVariable long teamId, Model m) {
        m.addAttribute("teamId", teamId);
        return "sign_up";
    }

}
