package org.launchcode.roomranger.service;

import org.launchcode.roomranger.data.UserAccountRepository;
import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.User;
import org.launchcode.roomranger.models.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class RoomRangerUserDetailsService implements UserDetailsService{
    @Autowired
private UserAccountRepository userAccountRepository;


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("Trying to find user:"+username);
    UserAccount user = null;
    try{
         user = userAccountRepository.findByUsername(username);
    }
    catch (Exception e){
        e.printStackTrace();
    }
    if (user == null) {
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // Convert the single role to a SimpleGrantedAuthority object
    System.out.println("User ROle:"+user.getRole());
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(authority));
}
}