package org.launchcode.roomranger.service;

import org.launchcode.roomranger.data.UserRepository;
import org.launchcode.roomranger.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class RoomRangerUserDetailsService implements UserDetailsService{
    @Autowired
private UserRepository userRepository;


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("Trying to find user:"+username);
    User user = null;
    try{
         user = userRepository.findByUsername(username);
    }
    catch (Exception e){
        e.printStackTrace();
    }
    if (user == null) {
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // Convert the single role to a SimpleGrantedAuthority object
    System.out.println("User Role:"+user.getRole());
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(authority));
}
}