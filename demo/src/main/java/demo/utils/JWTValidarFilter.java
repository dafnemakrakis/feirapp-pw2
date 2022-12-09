package demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTValidarFilter {

    public static boolean getAuthenticationToken(String token) {
        String usuario = JWT.require(Algorithm.HMAC512("eacc04ab-aff6-4238-86dd-0eb21e7c0d0b"))
                .build()
                .verify(token)
                .getSubject();

        return usuario != null;
    }

}