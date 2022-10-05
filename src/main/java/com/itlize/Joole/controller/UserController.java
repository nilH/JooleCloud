package com.itlize.Joole.controller;
//login register logout

import com.itlize.Joole.entity.User;
import com.itlize.Joole.service.UserService;
import com.itlize.Joole.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username,
                     @RequestParam("password") String password) throws Exception
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        final String jwt=jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user)
    {
        if(userService.findByUsername(user.getName())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User user1=new User();
        user1.setPassword(user.getPassword());
        user1.setName(user.getName());
        user1.setTimeCreated(LocalDateTime.now());
        user1.setRole("User");
        return new ResponseEntity<>(userService.saveUser(user1), HttpStatus.CREATED);
    }
    

}
