package net.no5no3.microservices.english.service;

import net.no5no3.microservices.english.mapper.UserMapper;
import net.no5no3.microservices.english.model.Role;
import net.no5no3.microservices.english.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }//end
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        userBuilder.roles(user.getUserRole().stream().map(userRole -> {
            Role role = userRole.getRole();
            return role.getName();
        }).toArray(String[]::new));
        userBuilder.authorities(new SimpleGrantedAuthority("normal"));
        return userBuilder.build();
    }
}
