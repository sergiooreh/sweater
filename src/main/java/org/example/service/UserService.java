package org.example.service;

import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service                                
public class UserService implements UserDetailsService {    //UserDetailsService interface is used to retrieve user-related data.
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //It is used by the DaoAuthenticationProvider to load details about the user during authentication.
        return userRepo.findByUsername(username);
    }
}
