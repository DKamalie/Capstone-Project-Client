package com.example.application.api;

import com.example.application.domain.AuthenticationRequest;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthenticateUserApi {
    static RestTemplate restTemplate = new RestTemplate();

    private static String url = "http://localhost:8080/api/v1/auth/authenticate";
    private  AuthenticationRequest authenticationRequest = new AuthenticationRequest();

    public String getToken(String email, String password) {

        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(new AuthenticationRequest (
                email,
                password
        ));

        String token = String.valueOf(restTemplate.postForObject(url, request, String.class));
        System.out.println(token);
        VaadinSession.getCurrent().setAttribute("token", token);
        //System.out.println(VaadinSession.getCurrent().getAttribute(token));
        return token;
    }

}
