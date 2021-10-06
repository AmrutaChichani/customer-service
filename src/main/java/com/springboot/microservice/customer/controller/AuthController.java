package com.springboot.microservice.customer.controller;

import com.springboot.microservice.customer.config.JwtUtils;
import com.springboot.microservice.customer.dao.RolesDAO;
import com.springboot.microservice.customer.dao.UserDAO;
import com.springboot.microservice.customer.dto.LoginRequest;
import com.springboot.microservice.customer.dto.SignupRequest;
import com.springboot.microservice.customer.entity.Role;
import com.springboot.microservice.customer.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RolesDAO rolesDAO;

    @PostMapping("/signIn")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken
                        (loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= jwtUtils.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok().body(jwt);

    }
    @PostMapping("/singUp")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        if(Boolean.TRUE.equals(userDAO.existsByUserName(signupRequest.getUsername()))){
            return ResponseEntity.badRequest().body("Username already exists. Please try with another name");
        }

            User user=new User();
            user.setUserName(signupRequest.getUsername());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            List<String> roleList = new ArrayList<>(signupRequest.getRoles());
            if(roleList.isEmpty()){

                roleList.add("ROLE_USER");
            }
            List<Role> listRoles=new ArrayList<>();
            for(String role:roleList){
                Optional<Role> theRole=rolesDAO.findByRoles(role);
                if(!theRole.isPresent()){
                    log.info("Invalid role");
                    return ResponseEntity.badRequest().body("Invalid role mentioned");
                }
                listRoles.add(theRole.get());
            }
            user.setRoles(listRoles);
            userDAO.save(user);
            return ResponseEntity.ok().body("User registered successfully.");
    }
}
