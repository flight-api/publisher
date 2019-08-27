package com.tiket.flight.publisher.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Airline.class)
public class Airline {

    public enum AirlineStatus {
        ACTIVE,
        INACTIVE
    }
    String _id;
    String code;
    String name;
    AirlineStatus status;

    public Airline(){

    }

    public Airline(String code, String name, AirlineStatus status) {
        this.code = code;
        this.name = name;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AirlineStatus getStatus() {
        return status;
    }

    public void setStatus(AirlineStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
