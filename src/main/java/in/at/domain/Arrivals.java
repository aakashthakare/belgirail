package in.at.domain;

import java.util.List;

public class Arrivals {

    private Integer number;

    List<Arrival> arrival;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Arrival> getArrival() {
        return arrival;
    }

    public void setArrival(List<Arrival> arrival) {
        this.arrival = arrival;
    }
}
