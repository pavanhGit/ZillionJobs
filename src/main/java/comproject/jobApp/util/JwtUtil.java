//package comproject.jobApp.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//        private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey"; // should be 256-bit for HS256
//        private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    public String extractUsername(String token) {
//        Claims claims = Jwts.parser()
//                .verifyWith((SecretKey) key) // NEW way to set key
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//
//        return claims.getSubject();
//    }
//
//        public boolean isTokenExpired(String token) {
//            Date expiration = Jwts.parser()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getExpiration();
//
//            return expiration.before(new Date());
//        }
//
//        public boolean validateToken(String token, UserDetails userDetails) {
//            String username = extractUsername(token);
//            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//        }
//
//        public String generateToken(String username) {
//            return Jwts.builder()
//                    .setSubject(username)
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                    .signWith(key, SignatureAlgorithm.HS256)
//                    .compact();
//        }
//
//
//}
//
