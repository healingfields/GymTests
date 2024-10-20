package ma.showmaker.gymTests.controllers;

import ma.showmaker.gymTests.models.User;
import ma.showmaker.gymTests.repositories.UserRepository;
import ma.showmaker.gymTests.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
       Authentication authentication =
               UsernamePasswordAuthenticationToken.unauthenticated(user.getUserName(), user.getPassword());
       this.authenticationManager.authenticate(authentication);
        String token = jwtService.generateToken(user.getUserName());
        return ResponseEntity.ok(token);
    }
}
