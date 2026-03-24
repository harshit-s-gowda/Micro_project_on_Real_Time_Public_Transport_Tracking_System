import java.util.List;
public class RouteResult {
    public enum Algorithm { DIJKSTRA, BELLMAN_FORD }
    private final List<BusStop> path;
    private final double totalWeight;
    private final Algorithm algorithm;
    private final long executionTimeNanos;
    private final int nodesVisited;
    private final boolean pathExists;
    public RouteResult(List<BusStop> path, double totalWeight, Algorithm algorithm, long executionTimeNanos, int nodesVisited, boolean pathExists) {
        this.path=path; this.totalWeight=totalWeight; this.algorithm=algorithm; this.executionTimeNanos=executionTimeNanos; this.nodesVisited=nodesVisited; this.pathExists=pathExists;
    }
    public List<BusStop> getPath() { return path; }
    public double getTotalWeight() { return totalWeight; }
    public Algorithm getAlgorithm() { return algorithm; }
    public int getNodesVisited() { return nodesVisited; }
    public boolean isPathExists() { return pathExists; }
    public String getFormattedTime() {
        if (executionTimeNanos < 1000) return executionTimeNanos + " ns";
        if (executionTimeNanos < 1000000) return String.format("%.2f us", executionTimeNanos/1000.0);
        return String.format("%.2f ms", executionTimeNanos/1000000.0);
    }
    public String toString() {
        if (!pathExists) return String.format("[%s] No path found. Time: %s", algorithm, getFormattedTime());
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Algorithm     : %s%n", algorithm));
        sb.append(String.format("Total Weight  : %.2f minutes%n", totalWeight));
        sb.append(String.format("Stops in path : %d%n", path.size()));
        sb.append(String.format("Nodes visited : %d%n", nodesVisited));
        sb.append(String.format("Exec time     : %s%n", getFormattedTime()));
        sb.append("Path          : ");
        for (int i=0; i<path.size(); i++) { sb.append(path.get(i).getName()); if (i<path.size()-1) sb.append(" -> "); }
        return sb.toString();
    }
}