package ma.showmaker.gymTests.utilities;

import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtil {

    private static final Logger logger = LogManager.getLogger(JwtUtil.class);

    private SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String generateToken(String username){
        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 1000 * 10 * 10 * 10))
                .signWith(secretKey)
                .compact();
    }

    public void validateToken(String token, String username){
        try{
            Jwts.parser().verifyWith(secretKey)
                    .requireSubject(username)
                    .requireNotBefore(new Date())
                    .build()
                    .parseSignedClaims(token);
        }catch (InvalidClaimException ex){
            logger.log(Level.ERROR, "invalid claim");
        }
    }

}
