package uz.pdp.lesson7.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.lesson7.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private final long time = 36_000_000;
    private final String secret = "secretKey";

    public String generateToken(String username){

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
//                .claim("roles", lavozim.toString())
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token){
        String username = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return username;
    }

}
