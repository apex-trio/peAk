package com.apextrio.peak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getHomePage() {
        return "index_test";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login_test";
    }

    @GetMapping("/sign-up")
    public String getSignup() {
        return "sign-up_test";
    }

    @GetMapping("/myProfile")
    public String getMyProfile() {
        return "myProfile_test";
    }


    ///////////////////////////////// -- POST routes


    @RequestMapping(value = "/newAppUser", method = RequestMethod.POST)
    public RedirectView addUsers(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String bio,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName) {

        String hashedPassword = bCryptPasswordEncoder.encode(password);
        AppUser newUser = new AppUser(username, hashedPassword, bio, firstName, lastName);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null,
                new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        appUserRepo.save(newUser);
        return new RedirectView("/");
    }

}
