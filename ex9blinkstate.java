class ex9blinkstate {
    static final int INF = 9999, N = 4;
    
    public static void main(String[] args) {
        int[][] graph = {{0,1,3,INF},{1,0,1,4},{3,1,0,2},{INF,4,2,0}};
        
        for (int node = 0; node < N; node++) {
            dijkstra(graph, node);
        }
    }
    
    static void dijkstra(int[][] graph, int source) {
        int[] dist = new int[N];
        int[] prev = new int[N];
        boolean[] visited = new boolean[N];
        
        // Initialize
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
            prev[i] = -1;
        }
        dist[source] = 0;
        
        // Find shortest paths
        for (int count = 0; count < N - 1; count++) {
            int u = -1;
            int minDist = INF;
            
            // Find unvisited node with minimum distance
            for (int i = 0; i < N; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            
            if (u == -1) break;
            visited[u] = true;
            
            // Update distances to neighbors
            for (int v = 0; v < N; v++) {
                if (!visited[v] && graph[u][v] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }
        
        // Print routing table
        System.out.println("Node " + source + " routing table:");
        System.out.println("Dest\tCost\tNext");
        for (int i = 0; i < N; i++) {
            String cost = (dist[i] == INF) ? "INF" : String.valueOf(dist[i]);
            String next = (prev[i] == -1) ? "-" : String.valueOf(getNextHop(source, i, prev));
            System.out.println(i + "\t" + cost + "\t" + next);
        }
        System.out.println();
    }
    
    static int getNextHop(int source, int dest, int[] prev) {
        if (source == dest || prev[dest] == -1) return -1;
        
        int current = dest;
        while (prev[current] != source && prev[current] != -1) {
            current = prev[current];
        }
        return current;
    }
}
