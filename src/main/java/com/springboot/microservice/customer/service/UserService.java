package com.springboot.microservice.customer.service;

import com.springboot.microservice.customer.dao.UserDAO;
import com.springboot.microservice.customer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDAO.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found :"+username));

        return UserDetailsImpl.build(user);
    }
}
