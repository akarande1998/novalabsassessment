package com.novalabs.task.service.serviceImpl;

import com.novalabs.task.model.User;
import com.novalabs.task.model.request.NewUserRequest;
import com.novalabs.task.repository.UserRepository;
import com.novalabs.task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService,IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthority(user)
        );
    }
    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    //create new user
    @Override
    public Object createNewUser(NewUserRequest newUserRequest) {
        User user=new User();
        user.setEmail(newUserRequest.getEmail());
        user.setEmail(cryptPasswordEncoder.encode(newUserRequest.getPassword()));
        userRepository.save(user);
        return "Saved successfully";
    }

    private Collection<? extends GrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "Admin"));
        return authorities;
    }

}
