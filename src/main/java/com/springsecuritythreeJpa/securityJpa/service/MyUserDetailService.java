package com.springsecuritythreeJpa.securityJpa.service;

import com.springsecuritythreeJpa.securityJpa.dto.MyUserDetails;
import com.springsecuritythreeJpa.securityJpa.model.User;
import com.springsecuritythreeJpa.securityJpa.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byUserName = userRepository.findByUserName(s);

        byUserName.orElseThrow(()->new UsernameNotFoundException("NoT Found this UserName ::"+ byUserName));

        //MyUserDetails myUserDetails = byUserName.map(MyUserDetails::new).get();
        MyUserDetails myUserDetails = byUserName.map(user -> new MyUserDetails(user)).get();

        return myUserDetails;
    }
}
