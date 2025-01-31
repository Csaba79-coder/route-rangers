package hu.pannonuni.routerangers.service;

import hu.pannonuni.model.Role;
import hu.pannonuni.routerangers.entity.user.User;
import hu.pannonuni.routerangers.util.Blacklist;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "bbe564433ebbb46af5da5513f146021527d51037618b144cdf4198ef2f4eaa01";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject); //subject a username vagy az email
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        // A felhasználó szerepének kinyerése (feltételezve, hogy a szerep a UserDetails-ben szerepel)
        Role role = ((User) userDetails).getRole(); // User típusra kell castolni, hogy elérjük a role-t
        String roleName = role.name(); // Enum értékének a neve, pl. "USER"

        // Hozzáadjuk a szerepet az extraClaims-hez
        extraClaims.put("role", roleName); // roleName mint string kerül a JWT-be

        // JWT token létrehozása
        return Jwts.builder()
                .setClaims(extraClaims) // extraClaims beállítása
                .setSubject(userDetails.getUsername()) // a felhasználó neve (vagy más információ) a subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // létrehozás dátuma
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365 * 10)) // lejárati dátum
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // aláírás
                .compact(); // a token véglegesítése
    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token) && !Blacklist.isBlacklistContainToken(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
