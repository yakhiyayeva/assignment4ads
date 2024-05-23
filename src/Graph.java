import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class Graph {
    private final int V; // Number of vertices
    private final List<Integer>[] adjList; // Adjacency list

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    public List<Integer> dfs(int start) {
        boolean[] visited = new boolean[V];
        List<Integer> dfsOrder = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                dfsOrder.add(node);
                for (int i = adjList[node].size() - 1; i >= 0; i--) {
                    int neighbor = adjList[node].get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return dfsOrder;
    }

    public List<Integer> bfs(int start) {
        boolean[] visited = new boolean[V];
        List<Integer> bfsOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (!visited[node]) {
                visited[node] = true;
                bfsOrder.add(node);
                for (int neighbor : adjList[node]) {
                    if (!visited[neighbor]) {
                        queue.add(neighbor);
                    }
                }
            }
        }
        return bfsOrder;
    }
}

