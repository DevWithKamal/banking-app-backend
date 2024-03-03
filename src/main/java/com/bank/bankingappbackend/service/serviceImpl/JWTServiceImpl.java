package com.bank.bankingappbackend.service.serviceImpl;

import com.bank.bankingappbackend.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Override

    public      String generateToken(UserDetails userDetails)
    {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24)).
                signWith(getAssigneeKey(), SignatureAlgorithm.HS256).compact();
    }
    // To Extract the userName from token we need to extract claim from the  token
    private <T>  T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final  Claims claims=extractAllClaim(token);

        return  claimsResolver.apply(claims);
    }

    private Key getAssigneeKey()
    {
        byte[] key= Decoders.BASE64.decode("413F4428472B4B62506553685660597033733676397924422645294840406351");

        return Keys.hmacShaKeyFor(key);
    }
    // this will fetch email stored in the particular token using extract  claim
    public  String extractUserName(String token)
    {
        return  extractClaim(token,Claims::getSubject);
    }
    private  Claims extractAllClaim(String token)
    {

        return Jwts.parser().setSigningKey(getAssigneeKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token,UserDetails userDetails)
    {
        final  String userName=extractUserName(token);

        return  (userName.equals(userDetails.getUsername()) &&  !isTokenExpired(token));
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000)).
                signWith(getAssigneeKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {

        return  extractClaim(token,Claims::getExpiration).before(new Date());
    }
}
