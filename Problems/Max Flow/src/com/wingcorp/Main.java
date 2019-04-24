import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        MaxFlowDirected.Compute(parseIn(System.in));
    }

    // Parsing code
    public static FlowGraph parseIn(InputStream input){
        Scanner scanner = new Scanner(input);

        // Get the first line separately
        String[] params = scanner.nextLine().split(" ");
        int N = Integer.parseInt(params[0]); // Number of vertices
        int M = Integer.parseInt(params[1]); // Number of edges
        int S = Integer.parseInt(params[2]); // Start
        int T = Integer.parseInt(params[3]); // Terminus

        FlowGraph G = new FlowGraph(N, S, T);

        // Parse the rest of the input
        while(scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(" ");
            int U = Integer.parseInt(parts[0]);
            int V = Integer.parseInt(parts[1]);
            int C = Integer.parseInt(parts[2]);

            G.addEdge(U,V,C);
        }
        return G;
    }
}

class FlowGraph {
    final int V;
    private int E;
    ArrayList<Edge>[] adj;
    ArrayList<Edge> edges = new ArrayList<>();

    final int S;
    final int T;

    public FlowGraph(int v, int s, int t) {
        V = v;
        E = 0;
        S = s;
        T = t;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v, int c) {
        Edge eu = new Edge(u, v, c);
        adj[u].add(eu);
        adj[v].add(eu);
        edges.add(eu);
        E++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges \n");
        for (int v = 0; v < V; v++) {
            s.append(String.format("Adjacency list for %d: \n", v));
            for (Edge e : adj[v]) {
                s.append("\t" + e.toString() + "\n");
            }
            s.append("\n");
        }
        return s.toString();
    }
}

class Edge {
    final int U;
    final int V;
    final int Capacity;

    private int Residual;
    private int Flow;

    public Edge(int u, int v, int c) {
        U = u;
        V = v;
        Capacity = c;

        Flow = 0;
        Residual = c;
    }

    public int ResidualTo(int edge) {
        if(edge == V) return Residual;
        return Flow;
    }

    public int AddFlowTo(int edge, double flow) {
        if (edge == U) {
            Flow -= flow;
            Residual += flow;
        } else {
            Residual -= flow;
            Flow += flow;
        }

        return Flow;
    }

    public int Flow() {
        return Flow;
    }

    public int other(int node) {
        if (node == U) return V;
        return U;
    }

    public String toString() {
        return U + " " + V + " " + Flow;
    }
}

class MaxFlowDirected {
    public static void Compute(FlowGraph G) {
        int s = G.S;
        int t = G.T;
        int value = 0;
        Edge[] edgeTo = new Edge[G.V];

        while (hasAugmentingPath(G, edgeTo, s, t)) {
            // compute bottleneck capacity
            double bottle = bottleneck(edgeTo, s, t);

            // augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].AddFlowTo(v, bottle);
            }

            // Increase the value by the bottleneck
            edgeTo = new Edge[G.V];
            value += bottle;
        }

        StringBuilder sb = new StringBuilder();
        int edgesUsed = 0;

        for (Edge e : G.edges) {
            if(e.Flow() < 1) continue;
            if(edgesUsed > 0) sb.append("\n");
            edgesUsed++;
            sb.append(e.toString());
        }

        System.out.println(G.V + " " + value + " " + edgesUsed);
        System.out.println(sb.toString());
    }

    // Checks if there is a path to be augmented from s to t in the graph using breadth-first-search
    private static boolean hasAugmentingPath(FlowGraph G, Edge[] edgeTo, int s, int t) {
        boolean[] marked = new boolean[G.V];

        // breadth-first search
        ArrayDeque<Integer> queue = new ArrayDeque<>(G.V/3 + G.V/16);
        queue.offer(s);
        marked[s] = true;

        // While the queue is not empty and terminus has not been marked by the search
        while (!queue.isEmpty() && !marked[t]) {
            // Grab an element
            int v = queue.poll();

            // For each edge adjacent to it
            for (Edge e : G.adj[v]) {
                // Sanity check to see if t has already been marked
                if (marked[t]) {
                    return true;
                }

                // Get the vertex on the other side of this edge that is not the current one
                int w = e.other(v);

                // If residual capacity from v to w is greater than 0
                if (e.ResidualTo(w) > 0) {

                    // And has not been marked
                    if (!marked[w]) {
                        // Mark it and enqueue the next vertex
                        edgeTo[w] = e;
                        marked[w] = true;
                        queue.offerLast(w);

                        if(marked[t]) {
                            return true;
                        }
                    }
                }
            }
        }
        return marked[t];
    }

    // Computes the bottleneck of the path between s and t
    private static double bottleneck(Edge[] edgeTo, int s, int t) {
        double bottle = Double.POSITIVE_INFINITY;
        for (int v = t; v != s; v = edgeTo[v].other(v)) {
            bottle = Math.min(bottle, edgeTo[v].ResidualTo(v));
        }
        return bottle;
    }
}