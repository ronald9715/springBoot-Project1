package com.ronald.security;

import com.ronald.model.User;
import com.ronald.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Clase S2
@Service
public class JwtUserDetailsService implements UserDetailsService {
    //Aqui le decimos a Spring Security de donde va sacar los usuarios
    @Autowired
    private IUserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOneByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        //Los roles que maneja Spring son Granted Authority
        List<GrantedAuthority> roles = new ArrayList<>();
        String role = user.getRole().getName();
        //SimpleGrantedAuthority pide dentro de su constructor el nombre del Rol
        roles.add(new SimpleGrantedAuthority(role));
        return  new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),roles);
    }
}
