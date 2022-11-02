package com.hello.domain;

import java.util.List;

public class Departures {

    private Integer number;

    List<Departure> departure;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Departure> getDeparture() {
        return departure;
    }

    public void setDeparture(List<Departure> departure) {
        this.departure = departure;
    }
}
