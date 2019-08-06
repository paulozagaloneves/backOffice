package com.itsector.backoffice.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JWTAuth {

    private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(1);
    private static final String SECRET = "itsectorbackoffice";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";
    private static final String CLAIMS_PRIVILEGES = "privileges";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

    static void addAuthentication(HttpServletResponse response, Authentication auth) {
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setSubject(auth.getName())
                .setExpiration(expiration)
                .setSubject(auth.getName());

        claims.put(CLAIMS_PRIVILEGES, auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        String JWT = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(claims)
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS,HEADER_STRING);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String user = claims.getSubject();

            List<GrantedAuthority> objects = new ArrayList<>();
            if (claims.containsKey(CLAIMS_PRIVILEGES)) {
                List<String> privileges = (List<String>) claims.get(CLAIMS_PRIVILEGES);
                for (String privilege : privileges) {
                    objects.add(() -> privilege);
                }
            }
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, objects);
            }
        }
        return null;
    }
}
