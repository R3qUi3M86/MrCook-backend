package com.codecool.mrcook.security;

import com.codecool.mrcook.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.Long.parseLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final String tokenSecret = "926D96C90030DD58429D2751AC1BDBBC";
    private final long tokenExpirationMsec = 1000L * 60 * 60 * 24 * 30;

    public String createToken(Authentication authentication){
        User userPrincipal = (User) authentication.getPrincipal();

        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + tokenExpirationMsec);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()+"")
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token);

            return true;
        } catch (SignatureException e){
            log.error("Invalid JWT signature!");
        } catch (MalformedJwtException e){
            log.error("Malformed JWT!");
        } catch (ExpiredJwtException e){
            log.error("JWT has expired!");
        } catch (UnsupportedJwtException e){
            log.error("Unsupported JWT");
        } catch (IllegalArgumentException e){
            log.error("JWT is null or empty");
        }

        return false;
    }
}
