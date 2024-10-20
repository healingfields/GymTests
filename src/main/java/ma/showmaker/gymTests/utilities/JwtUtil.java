package ma.showmaker.gymTests.utilities;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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

    public String extractNameFromToken(String token){
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (SecurityException | MalformedJwtException ex){
            logger.log(Level.ERROR, "jwt incorrect");
            throw new AuthenticationCredentialsNotFoundException("jwt incorrect");
        }catch (ExpiredJwtException ex){
            logger.log(Level.ERROR, "jwt expired");
            throw new AuthenticationCredentialsNotFoundException("jwt expired");
        }catch(UnsupportedJwtException ex){
            logger.log(Level.ERROR, "unsupported format");
            throw new AuthenticationCredentialsNotFoundException("unsupported format");
        }
    }

}
