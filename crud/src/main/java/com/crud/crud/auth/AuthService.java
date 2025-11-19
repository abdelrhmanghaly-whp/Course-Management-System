package com.crud.crud.auth;


import com.crud.crud.model.User;
import com.crud.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    // Register
    public AuthResponse register(AuthRequest req){
        if(userRepository.findByUsername(req.getUsername()).isPresent()){ // if already exists
            throw new RuntimeException("Username already exists");
        }

        // create and save a new user
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        userRepository.save(user);

        String token = jwtService.generateToken(req.getUsername());
        return new AuthResponse(token, user.getUsername());
    }
    

    // Login
    public AuthResponse login(AuthRequest req){
        User user = userRepository.findByUsername(req.getUsername())
        .orElseThrow(()-> new RuntimeException("Invalid username or password"));
        
        if(!passwordEncoder.matches(req.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid username or password");
        }
        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token, user.getUsername());
    }

    
}
