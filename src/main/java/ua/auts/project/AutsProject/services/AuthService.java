package ua.auts.project.AutsProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.auts.project.AutsProject.dtos.SignUpDto;
import ua.auts.project.AutsProject.entities.User;
import ua.auts.project.AutsProject.repositories.UserRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByLogin(username);
        return user;
    }

    public UserDetails signUp(SignUpDto data) throws Exception {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new Exception("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }
    
}
