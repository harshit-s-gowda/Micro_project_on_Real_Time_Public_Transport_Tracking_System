import java.util.*;
public class TransportGraph {
    private final Map<String,BusStop> stops = new LinkedHashMap<>();
    private final Map<String,List<Edge>> adjacency = new LinkedHashMap<>();
    public void addStop(BusStop stop) { stops.put(stop.getId(),stop); adjacency.putIfAbsent(stop.getId(),new ArrayList<>()); }
    public void addEdge(String a, String b, double w, String r) {
        BusStop src=stops.get(a); BusStop dst=stops.get(b);
        if (src==null||dst==null) throw new IllegalArgumentException("Stop not found");
        adjacency.get(a).add(new Edge(src,dst,w,r));
    }
    public void addBidirectionalEdge(String a, String b, double w, String r) { addEdge(a,b,w,r); addEdge(b,a,w,r); }
    public BusStop getStop(String id) { return stops.get(id); }
    public Collection<BusStop> getAllStops() { return stops.values(); }
    public List<Edge> getEdges(String id) { return adjacency.getOrDefault(id, Collections.emptyList()); }
    public boolean containsStop(String id) { return stops.containsKey(id); }
    public int stopCount() { return stops.size(); }
    public int edgeCount() { return adjacency.values().stream().mapToInt(List::size).sum(); }
    public List<Edge> getAllEdges() { List<Edge> all=new ArrayList<>(); adjacency.values().forEach(all::addAll); return all; }
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("=== Transport Graph ===%n");
        sb.append(String.format("Stops: %d | Edges: %d%n%n",stopCount(),edgeCount()));
        for (BusStop s:stops.values()) { sb.append(s).append("%n"); for (Edge e:adjacency.get(s.getId())) sb.append("    ").append(e).append("%n"); }
        return sb.toString();
    }
}