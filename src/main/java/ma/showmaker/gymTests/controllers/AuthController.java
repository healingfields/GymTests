package ma.showmaker.gymTests.controllers;

import ma.showmaker.gymTests.models.User;
import ma.showmaker.gymTests.repositories.UserRepository;
import ma.showmaker.gymTests.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(name = "/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtService;

    //Why not autowire it
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        User doesUserExist = userRepository.findByUserName(user.getUserName());
        if(Objects.nonNull(doesUserExist) && bCryptPasswordEncoder.matches(user.getPassword(), doesUserExist.getPassword())){
            return jwtService.generateToken(user.getUserName());
        }
        throw new RuntimeException("invalid credentials");
    }
}
