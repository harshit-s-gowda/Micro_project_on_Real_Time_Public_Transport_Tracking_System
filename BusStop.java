public class BusStop {
    private final String id;
    private final String name;
    private final double latitude;
    private final double longitude;
    public BusStop(String id, String name, double latitude, double longitude) {
        this.id=id; this.name=name; this.latitude=latitude; this.longitude=longitude;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String toString() { return String.format("[%s] %s", id, name); }
    public boolean equals(Object o) { if (!(o instanceof BusStop)) return false; return id.equals(((BusStop)o).id); }
    public int hashCode() { return id.hashCode(); }
}