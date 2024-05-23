import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Create graph and add edges
        Graph g = new Graph(7); // 7 vertices: A, B, C, D, E, F, G
        int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6;

        g.addEdge(A, C);
        g.addEdge(A, B);
        g.addEdge(A, D);
        g.addEdge(B, A);
        g.addEdge(B, C);
        g.addEdge(B, E);
        g.addEdge(B, G);
        g.addEdge(C, A);
        g.addEdge(C, B);
        g.addEdge(C, D);
        g.addEdge(D, C);
        g.addEdge(D, A);
        g.addEdge(E, G);
        g.addEdge(E, F);
        g.addEdge(E, B);
        g.addEdge(F, G);
        g.addEdge(F, E);
        g.addEdge(G, F);
        g.addEdge(G, B);

        // Perform DFS and BFS
        List<Integer> dfsOutput = g.dfs(A);
        List<Integer> bfsOutput = g.bfs(A);

        // Convert integer outputs to characters for display
        System.out.print("DFS Output: ");
        for (int node : dfsOutput) {
            System.out.print((char) (node + 'A') + " ");
        }
        System.out.println();

        System.out.print("BFS Output: ");
        for (int node : bfsOutput) {
            System.out.print((char) (node + 'A') + " ");
        }
    }
}