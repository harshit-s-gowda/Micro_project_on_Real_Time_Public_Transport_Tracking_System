public class Edge {
    private final BusStop source;
    private final BusStop destination;
    private final double weight;
    private final String routeNumber;
    public Edge(BusStop source, BusStop destination, double weight, String routeNumber) {
        this.source=source; this.destination=destination; this.weight=weight; this.routeNumber=routeNumber;
    }
    public BusStop getSource() { return source; }
    public BusStop getDestination() { return destination; }
    public double getWeight() { return weight; }
    public String getRouteNumber() { return routeNumber; }
    public String toString() { return String.format("%s --> %s (%.1f min, %s)", source.getName(), destination.getName(), weight, routeNumber); }
}