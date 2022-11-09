package in.at.domain;

public abstract class TrainAction {

    private String id;

    private int delay;

    private String station;

    private String vehicle;

    private String platform;

    private Long time;

    private Integer canceled;

    private Integer left;

    private VehicleInfo vehicleinfo;

    private StationInfo stationinfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public VehicleInfo getVehicleinfo() {
        return vehicleinfo;
    }

    public void setVehicleinfo(VehicleInfo vehicleinfo) {
        this.vehicleinfo = vehicleinfo;
    }

    public StationInfo getStationinfo() {
        return stationinfo;
    }

    public void setStationinfo(StationInfo stationinfo) {
        this.stationinfo = stationinfo;
    }
}
