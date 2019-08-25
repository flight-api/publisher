package com.tiket.flight.publisher.controllers;

import com.tiket.flight.publisher.libs.jwt.AuthenticationTokenImpl;
import com.tiket.flight.publisher.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/v1")
public class AuthController {

    @Autowired
    RedisService service;

    @PostMapping(path = "/logout", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> logout(AuthenticationTokenImpl auth, HttpServletResponse response) {
        service.setValue(auth.getPrincipal()+":"+auth.getHash(), "");
        return new ResponseEntity<>("logout success", HttpStatus.OK);
    }

}
