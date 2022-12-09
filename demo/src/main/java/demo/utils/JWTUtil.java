package demo.utils;
import java.time.Instant;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import demo.model.User;


public class JWTUtil {

    public static final long TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "eacc04ab-aff6-4238-86dd-0eb21e7c0d0b";

    public static String successfulAuthentications(User user) {
        var jwtPayload = Map.of(
                "username", user.getName(),
                "email", user.getEmail()
        );

        String token = JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(Instant.now().plusMillis(TOKEN_EXPIRACAO))
                .withPayload(jwtPayload)
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        return token;
    }
}
