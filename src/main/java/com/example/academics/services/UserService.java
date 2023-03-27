package com.example.academics.services;

import com.example.academics.config.MyUserPrincipal;
import com.example.academics.model.Users;
import com.example.academics.model.repo.RoleRepository;
import com.example.academics.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(7);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(users);
    }

    @Transactional
    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void saveUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        roleRepository.saveAll(users.getRoles());
        userRepository.save(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }

}

