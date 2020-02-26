package org.example.service;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

//all business logic must be here, not in controller
@Service                                
public class UserService implements UserDetailsService {    //UserDetailsService interface is used to retrieve user-related data.
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //It is used by the DaoAuthenticationProvider to load details about the user during authentication.
        return userRepo.findByUsername(username);
    }
    public boolean addUser(User user){
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB!=null){
            return false;                                                               //user wasn't added
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));        //создает Set с одним единственным значением
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);
        
        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n"+
                            "Welcome to Sweater. Please, confirm your registration by visiting this link: " + "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(),"ActivationCode", message);
        }
        return true;                                            //user was added
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }
}
