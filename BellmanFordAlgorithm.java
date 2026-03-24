import java.util.*;
public class BellmanFordAlgorithm {
    public RouteResult findShortestPath(TransportGraph graph, String sourceId, String destId) {
        long startTime=System.nanoTime();
        int V=graph.stopCount();
        List<Edge> edges=graph.getAllEdges();
        Map<String,Double> dist=new HashMap<>();
        Map<String,String> prev=new HashMap<>();
        int relaxed=0;
        for (BusStop s:graph.getAllStops()) { dist.put(s.getId(),Double.MAX_VALUE); prev.put(s.getId(),null); }
        dist.put(sourceId,0.0);
        for (int pass=1; pass<=V-1; pass++) {
            boolean updated=false;
            for (Edge edge:edges) {
                String u=edge.getSource().getId(); String v=edge.getDestination().getId(); double w=edge.getWeight();
                if (dist.get(u)!=Double.MAX_VALUE && dist.get(u)+w<dist.get(v)) { dist.put(v,dist.get(u)+w); prev.put(v,u); relaxed++; updated=true; }
            }
            if (!updated) break;
        }
        boolean negCycle=false;
        for (Edge edge:edges) { String u=edge.getSource().getId(); String v=edge.getDestination().getId(); if (dist.get(u)!=Double.MAX_VALUE && dist.get(u)+edge.getWeight()<dist.get(v)) { negCycle=true; break; } }
        long elapsed=System.nanoTime()-startTime;
        if (negCycle) return new RouteResult(Collections.emptyList(),0,RouteResult.Algorithm.BELLMAN_FORD,elapsed,relaxed,false);
        boolean found=dist.get(destId)<Double.MAX_VALUE;
        List<BusStop> path=new ArrayList<>();
        if (found) { for (String at=destId; at!=null; at=prev.get(at)) path.add(0,graph.getStop(at)); }
        return new RouteResult(path,found?dist.get(destId):0,RouteResult.Algorithm.BELLMAN_FORD,elapsed,relaxed,found);
    }
}