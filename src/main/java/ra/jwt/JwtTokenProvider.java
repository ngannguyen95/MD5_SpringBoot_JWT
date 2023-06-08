package ra.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.security.CustomUserDetail;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    // khai báo mã bảo mật tổ chức

    private String JWT_SECRET ="ngan2k6";

    private long JWT_EXPIRATION=86400000;

    // tạo Jwt từ thông tin User
    public String generateToken(CustomUserDetail customUserDetail) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);
        // tao chuỗi Jwt từ user
        // dùng Jwts để builder cá thôn tin
        return Jwts.builder().setSubject(customUserDetail.getUsername())
                // ngày bắt đầu
                .setIssuedAt(now)
                // ngày hết hạn
                .setExpiration(expiration)
                // set giải thuật để mã hóa
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }

    // từ Jwt mã hóa thành thông tin User
    public String getUserNameFromJwt(String token) {
        // set key
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        // trả lại username
        return claims.getSubject();
    }

    // validate còn hạn , còn hiệu lực hay không
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired Jwt Token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported  Jwt Token");
        } catch (MalformedJwtException e) {
            log.error("Invalid Jwt Token");
        } catch (SignatureException e) {
            log.error("Signature Exception");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims String is empty");
        }
        return false;
    }


}
