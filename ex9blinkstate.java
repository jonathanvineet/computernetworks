class ex9blinkstate {
    static final int INF = 9999, N = 4;
    
    public static void main(String[] args) {
        int[][] cost = {{0,1,3,INF},{1,0,1,4},{3,1,0,2},{INF,4,2,0}};
        
        for (int src = 0; src < N; src++) {
            int[] dist = new int[N];
            boolean[] vis = new boolean[N];
            
            // Initialize
            for (int i = 0; i < N; i++) dist[i] = INF;
            dist[src] = 0;
            
            // Find shortest paths
            for (int c = 0; c < N; c++) {
                int u = -1;
                for (int i = 0; i < N; i++) {
                    if (!vis[i] && (u == -1 || dist[i] < dist[u])) u = i;
                }
                if (dist[u] == INF) break;
                vis[u] = true;
                
                for (int v = 0; v < N; v++) {
                    if (cost[u][v] != INF && dist[u] + cost[u][v] < dist[v]) {
                        dist[v] = dist[u] + cost[u][v];
                    }
                }
            }
            
            // Print results  
            System.out.println("Node " + src + " routing table:");
            System.out.println("Dest\tCost\tNext");
            for (int i = 0; i < N; i++) {
                String d = (dist[i] == INF) ? "INF" : String.valueOf(dist[i]);
                String next = "-";
                if (src != i && dist[i] != INF) {
                    // Find next hop
                    for (int j = 0; j < N; j++) {
                        if (cost[src][j] != INF && dist[j] + cost[src][j] == dist[i]) {
                            next = String.valueOf(j);
                            break;
                        }
                    }
                }
                System.out.println(i + "\t" + d + "\t" + next);
            }
            System.out.println();
        }
    }
}
