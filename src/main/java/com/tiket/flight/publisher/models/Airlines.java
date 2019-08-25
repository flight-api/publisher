package com.tiket.flight.publisher.models;

import java.util.ArrayList;
import java.util.List;

public class Airlines {
    private List<Airline> airlineList;

    public List<Airline> getAirlineList() {
        if (airlineList == null) {
            airlineList = new ArrayList<>();
        }
        return airlineList;
    }

    public void setAirlineList(List<Airline> airlineList) {
        this.airlineList = airlineList;
    }
}