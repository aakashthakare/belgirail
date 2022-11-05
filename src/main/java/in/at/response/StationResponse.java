package in.at.response;

import in.at.domain.Station;

import java.util.List;

public class StationResponse {

    private List<Station> station;

    public List<Station> getStation() {
        return station;
    }

    public void setStation(List<Station> station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "StationResponse{" +
                "stations=" + station +
                '}';
    }
}
