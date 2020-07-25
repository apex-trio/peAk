package com.apextrio.peak;

import com.apextrio.peak.appuser.AppUser;
import com.apextrio.peak.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Implementation of the UserDetailService for proper security/auth with spring
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AppUserRepository appRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = this.appRepo.findByUsername(username);

        if(user == null){
            System.out.println("User: " + username + " not found.");
            throw new UsernameNotFoundException("User (" + username + ") was not found in database.");
        }

        System.out.println("Found User: " + user);

        return user;
    }

}