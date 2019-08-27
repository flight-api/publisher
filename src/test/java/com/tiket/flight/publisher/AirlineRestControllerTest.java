package com.tiket.flight.publisher;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.tiket.flight.publisher.consumer.Receiver;
import com.tiket.flight.publisher.controllers.AirlineController;
import com.tiket.flight.publisher.models.Airline;
import com.tiket.flight.publisher.models.Login;
import com.tiket.flight.publisher.producers.Sender;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirlineRestControllerTest {

    @LocalServerPort
    int randomServerPort;

    public static String token;

    public static String baseUrl;

    @Before
    public void login() throws URISyntaxException {
        System.out.println();
        HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type","application/json");

//		HttpEntity<Login> request = new HttpEntity<>(login, headers);
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort;
//        final String baseUrl = "http://localhost:" + randomServerPort + "/v1/login";
        this.baseUrl = baseUrl;
        URI uri = new URI(baseUrl+ "/v1/login");
        Login login = new Login();
        login.setUsername("tester1");
        login.setPassword("tester1");
        ResponseEntity<String> resp = restTemplate.postForEntity(uri, login, String.class);
        token = resp.getHeaders().getFirst("Authorization");
        assertTrue(token.contains("Bearer"));
    }

    @Test
    public void AddAirline() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", token);
        String endpoint = baseUrl + "/v1/airlines/";
        Airline airline = new Airline();
        airline.set_id("1");
        airline.setCode("GARUDA");
        airline.setName("GARUDA YK-CKG");
        airline.setStatus(Airline.AirlineStatus.ACTIVE);
        HttpEntity<Airline> request = new HttpEntity<>(airline, headers);
        URI uri = new URI(endpoint);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.postForEntity(uri, request, String.class);
        String msg = resp.getBody();
        assertTrue(msg.equals("Create Airline submitted"));
    }

    @Test
    public void UpdateAirline() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", token);
        String endpoint = baseUrl + "/v1/airlines/1";
        Airline airline = new Airline();
        airline.setCode("LION");
        airline.setName("GARUDA YK-CKG");
        airline.setStatus(Airline.AirlineStatus.ACTIVE);
        HttpEntity<Airline> request = new HttpEntity<>(airline, headers);
        URI uri = new URI(endpoint);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> resp = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
        String msg = resp.getBody();
        assertTrue(msg.equals("Update Airline submitted"));
    }

    @After
    public void logout() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Authorization", token);
        String endpoint = baseUrl + "/v1/logout";
        HttpEntity<String> request = new HttpEntity<>("", headers);
        URI uri = new URI(endpoint);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.postForEntity(uri, request, String.class);
        String msg = resp.getBody();
        assertTrue(msg.equals("logout success"));
    }


}
