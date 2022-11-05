package in.at.domain;

public class Station {

    private String id;

    private String standardname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStandardname() {
        return standardname;
    }

    public void setStandardname(String standardname) {
        this.standardname = standardname;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", standardName='" + standardname + '\'' +
                '}';
    }
}
