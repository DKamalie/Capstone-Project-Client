package com.example.application.api;

import com.example.application.domain.AuthenticationRequest;

import com.vaadin.flow.server.VaadinSession;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class AuthenticateUserApi {
    static RestTemplate restTemplate = new RestTemplate();
    private String token;

    private static String url = "http://localhost:8083/api/v1/auth/";
    private  AuthenticationRequest authenticationRequest = new AuthenticationRequest();

    public String getToken(String email, String password) {

        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(new AuthenticationRequest (
                email,
                password
        ));

         token = String.valueOf(restTemplate.postForObject(url + "authenticate", request, String.class));
        System.out.println(token);
        VaadinSession.getCurrent().setAttribute("token", token);
        return token;
    }

    public String greet() throws IOException {
//        HttpClient client = HttpClients.custom().build();
//        HttpUriRequest request = RequestBuilder.get().setUri(url)
//                .setHeader(HttpHeaders.AUTHORIZATION, "application/json").build();
//        client.execute(request);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet get = new HttpGet(url + "greetings");
        get.setHeader("Authorization", "Bearer " + token);


        HttpResponse response = httpclient.execute(get);
        System.out.printf(Arrays.toString(response.getHeaders("Authorization")));
        System.out.println(response.getStatusLine());
        System.out.println("get:" + get);
        System.out.println(response.getEntity());

        EntityUtils.toString(response.getEntity());

        return token;
    }

    public void callListOrganizationAPI(){
        System.out.println(token);

            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization","Bearer " + token);
            headers.setBearerAuth(token);
            HttpEntity <String> request = new HttpEntity<String>(String.valueOf(headers));
            HttpEntity <String> request2 = new HttpEntity<String>("");


            ResponseEntity<String> result =  restTemplate.exchange(url + "greetings", HttpMethod.GET, request, String.class);
            System.out.println(result);
            String json = result.getBody();

            System.out.println(json);

        }


    }

