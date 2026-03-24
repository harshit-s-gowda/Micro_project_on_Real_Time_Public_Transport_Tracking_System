import java.util.*;
public class ConsoleUI {
    private final TransportGraph graph=GraphBuilder.buildBengaluruNetwork();
    private final DijkstraAlgorithm dijkstra=new DijkstraAlgorithm();
    private final BellmanFordAlgorithm bellman=new BellmanFordAlgorithm();
    private final Scanner sc=new Scanner(System.in);
    public void run() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  PUBLIC TRANSPORT FINDER  (SIH25013)    ║");
        System.out.println("║  Bengaluru Bus Network | DAA Project     ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.printf("%nNetwork: %d stops, %d edges%n",graph.stopCount(),graph.edgeCount());
        while (true) {
            System.out.println("%n1.Dijkstra  2.Bellman-Ford  3.Compare  4.Stops  5.Graph  6.Complexity  0.Exit");
            System.out.print("Choice: ");
            String c=sc.nextLine().trim();
            if (c.equals("1")) runAlgo("D");
            else if (c.equals("2")) runAlgo("B");
            else if (c.equals("3")) compare();
            else if (c.equals("4")) listStops();
            else if (c.equals("5")) System.out.println(graph);
            else if (c.equals("6")) complexity();
            else if (c.equals("0")) { System.out.println("Goodbye!"); return; }
        }
    }
    void runAlgo(String t) {
        listStops();
        String src=promptStop("SOURCE"); String dst=promptStop("DESTINATION");
        RouteResult r=t.equals("D")?dijkstra.findShortestPath(graph,src,dst):bellman.findShortestPath(graph,src,dst);
        System.out.println(r);
    }
    void compare() {
        listStops();
        String src=promptStop("SOURCE"); String dst=promptStop("DESTINATION");
        RouteResult dr=dijkstra.findShortestPath(graph,src,dst);
        RouteResult br=bellman.findShortestPath(graph,src,dst);
        System.out.println("--- DIJKSTRA ---"); System.out.println(dr);
        System.out.println("--- BELLMAN-FORD ---"); System.out.println(br);
        System.out.println("Dijkstra: O((V+E)logV)  BellmanFord: O(VxE)");
    }
    void complexity() {
        int V=graph.stopCount(),E=graph.edgeCount();
        System.out.printf("V=%d E=%d%nDijkstra O((V+E)logV)~%d ops%nBellmanFord O(VxE)~%d ops%n",V,E,(long)((V+E)*(Math.log(V)/Math.log(2))),(long)V*E);
    }
    void listStops() { for (BusStop s:graph.getAllStops()) System.out.printf("%-5s %s%n",s.getId(),s.getName()); }
    String promptStop(String l) {
        while(true) { System.out.print("Enter "+l+" stop ID: "); String id=sc.nextLine().trim().toUpperCase(); if(graph.containsStop(id)) return id; System.out.println("Not found."); }
    }
}