package demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import demo.model.User;
import demo.utils.JWTUtil;
import demo.utils.JWTValidarFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class LoginController {

    //getPrincipal return name, email, photo -- Exemplo do json está na raiz do projeto
    @GetMapping
    @JsonFormat
    public ResponseEntity<Map> login(OAuth2AuthenticationToken oAuth2AuthenticationToken) {

        var template = new RestTemplate();

        String name = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name").toString();
        String email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email").toString();

        var user = new User(name, email);
        var token = JWTUtil.successfulAuthentications(user);

        template.setInterceptors(List.of(
                (request, body, execution) -> {
                    request.getHeaders().add("Authorization", "Bearer " + token);
                    return execution.execute(request, body);
                }
        ));

        System.out.println("::::: Generated Token: " + token);

        var decodedToken = template.getForEntity("http://localhost:8071/getUser", Map.class);

        System.out.println("::::: Calling Decoding service: " + decodedToken);

        return decodedToken;
    }
}
