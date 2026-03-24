import java.util.*;
public class DijkstraAlgorithm {
    public RouteResult findShortestPath(TransportGraph graph, String sourceId, String destId) {
        long startTime=System.nanoTime();
        Map<String,Double> dist=new HashMap<>();
        Map<String,String> prev=new HashMap<>();
        for (BusStop s:graph.getAllStops()) { dist.put(s.getId(),Double.MAX_VALUE); prev.put(s.getId(),null); }
        dist.put(sourceId,0.0);
        PriorityQueue<StopEntry> heap=new PriorityQueue<>(Comparator.comparingDouble(e->e.dist));
        heap.add(new StopEntry(0.0,sourceId));
        Set<String> settled=new HashSet<>();
        int[] visited={0};
        while (!heap.isEmpty()) {
            StopEntry cur=heap.poll();
            String u=cur.stopId;
            if (settled.contains(u)) continue;
            settled.add(u); visited[0]++;
            if (u.equals(destId)) break;
            for (Edge edge:graph.getEdges(u)) {
                String v=edge.getDestination().getId();
                double nd=dist.get(u)+edge.getWeight();
                if (nd<dist.get(v)) { dist.put(v,nd); prev.put(v,u); heap.add(new StopEntry(nd,v)); }
            }
        }
        long elapsed=System.nanoTime()-startTime;
        boolean found=dist.get(destId)<Double.MAX_VALUE;
        List<BusStop> path=new ArrayList<>();
        if (found) { for (String at=destId; at!=null; at=prev.get(at)) path.add(0,graph.getStop(at)); }
        return new RouteResult(path,found?dist.get(destId):0,RouteResult.Algorithm.DIJKSTRA,elapsed,visited[0],found);
    }
    static class StopEntry { double dist; String stopId; StopEntry(double d,String s){dist=d;stopId=s;} }
}