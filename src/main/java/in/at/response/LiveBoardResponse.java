package in.at.response;

import in.at.domain.Arrivals;
import in.at.domain.Departures;

public class LiveBoardResponse {

    private Departures departures;

    private Arrivals arrivals;

    public Departures getDepartures() {
        return departures;
    }

    public void setDepartures(Departures departures) {
        this.departures = departures;
    }

    public Arrivals getArrivals() {
        return arrivals;
    }

    public void setArrivals(Arrivals arrivals) {
        this.arrivals = arrivals;
    }
}
