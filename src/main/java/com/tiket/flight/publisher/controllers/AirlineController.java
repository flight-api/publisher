package com.tiket.flight.publisher.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiket.flight.publisher.models.Airline;
import com.tiket.flight.publisher.producers.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/airlines")
public class AirlineController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineController.class);
    @Autowired
    private Sender sender;

//    @Autowired
//    private AirlineDao airlineDao;

//    @GetMapping(path = "/", produces = "application/json")
//    public Airlines getAirlines() {
//        return airlineDao.getAllAirlines();
//    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addAirline(@RequestBody Airline airline) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        String newAirline = gson.toJson(airline);
        LOGGER.debug("Request: " + newAirline);
        sender.send("airline-create", newAirline);
        return new ResponseEntity<>("New Airline submitted", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateAirline(@RequestBody Airline airline, @PathVariable("id") Integer id) {
        if(id==null || id<0){
            return new ResponseEntity<>("Need ID of airline data", HttpStatus.FAILED_DEPENDENCY);
        }
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        String newAirline = gson.toJson(airline);
        LOGGER.debug("Request: " + newAirline);
        sender.send("airline-update", newAirline);
        return new ResponseEntity<>("Update Airline submitted", HttpStatus.OK);
    }

}