package app.controller;

import app.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("getUser")
    @JsonFormat
    public String login(@RequestHeader("Authorization") String authHeader) {

        var rawToken = authHeader.substring(7);

        System.out.println("::: Got token " + rawToken);

        var decodedToken = JWT.require(Algorithm.HMAC512("eacc04ab-aff6-4238-86dd-0eb21e7c0d0b"))
                .build()
                .verify(rawToken);

        var decodedPayload = new String(
                Base64.getDecoder().decode(decodedToken.getPayload())
        );

        return decodedPayload;
    }
}
