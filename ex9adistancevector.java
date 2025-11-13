class ex9adistancevector {
    static final int INF = 9999, N = 4;
    
    public static void main(String[] args) {
        // Network costs
        int[][] cost = {{0,1,3,INF},{1,0,1,4},{3,1,0,2},{INF,4,2,0}};
        int[][] dist = new int[N][N];
        int[][] next = new int[N][N];
        
        // Initialize
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = cost[i][j];
                next[i][j] = (cost[i][j] != INF && i != j) ? j : -1;
            }
        }
        
        // Find shortest paths
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        
        // Print results
        for (int i = 0; i < N; i++) {
            System.out.println("Node " + i + " routing table:");
            System.out.println("Dest\tCost\tNext");
            for (int j = 0; j < N; j++) {
                String c = (dist[i][j] == INF) ? "INF" : String.valueOf(dist[i][j]);
                String n = (next[i][j] == -1) ? "-" : String.valueOf(next[i][j]);
                System.out.println(j + "\t" + c + "\t" + n);
            }
            System.out.println();
        }
    }
}
