package ma.showmaker.gymTests.providers;

import ma.showmaker.gymTests.models.User;
import ma.showmaker.gymTests.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUserName(userName);
        if(Objects.nonNull(user) &&  passwordEncoder.matches(password, user.getPassword())){
            return new UsernamePasswordAuthenticationToken(userName, password, null);
        }else{
            throw new RuntimeException("Invalid Credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
