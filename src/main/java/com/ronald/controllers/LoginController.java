package com.ronald.controllers;

import com.ronald.security.JwtRequest;
import com.ronald.security.JwtResponse;
import com.ronald.security.JwtTokenUtil;
import com.ronald.security.JwtUserDetailsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws Exception{
       authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
       final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
       final String token = jwtTokenUtil.generateToken(userDetails);
       return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate (String username, String password) throws Exception{
        try{
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED");
        }catch (BadCredentialsException e){
            throw  new Exception("INVALID_CREDENTIALS");
        }
    }
}
