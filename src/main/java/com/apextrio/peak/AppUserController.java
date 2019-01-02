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
    public String getSignup() {
        return "sign_up";
    }

    @GetMapping("/myProfile")
    public String getMyProfile() {
        return "my_profile";
    }


    ///////////////////////////////// -- POST routes


    @RequestMapping(value = "/newAppUser", method = RequestMethod.POST)
    public RedirectView addUsers(AppUser user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        appUserRepo.save(user);
        return new RedirectView("/");
    }

    @RequestMapping(value = "/signup/{teamId}", method = RequestMethod.GET)
    public String signUpFromGroupPage(@PathVariable long teamId, Model m) {
        m.addAttribute("teamId", teamId);
        return "sign_up";
    }
}
