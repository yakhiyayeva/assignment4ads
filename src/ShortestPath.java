import java.util.*;

public class ShortestPath {
    private static final int MAX_VERTICES = 100; // Maximum number of vertices
    private List<Edge>[] graph;
    private String[] vertices;
    private int vertexCount;

    public ShortestPath() {
        graph = new ArrayList[MAX_VERTICES];
        vertices = new String[MAX_VERTICES];
        for (int i = 0; i < MAX_VERTICES; i++) {
            graph[i] = new ArrayList<>();
        }
        vertexCount = 0;
    }

    private int getVertexIndex(String vertex) {
        for (int i = 0; i < vertexCount; i++) {
            if (vertices[i].equals(vertex)) {
                return i;
            }
        }
        vertices[vertexCount] = vertex;
        return vertexCount++;
    }

    public void addRoad(String src, String dest, int dist) {
        int srcIndex = getVertexIndex(src);
        int destIndex = getVertexIndex(dest);
        graph[srcIndex].add(new Edge(destIndex, dist));
        // Assuming bidirectional roads
        graph[destIndex].add(new Edge(srcIndex, dist));
    }

    public int[] dijkstra(String source) {
        int sourceIndex = getVertexIndex(source);
        int[] distances = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[sourceIndex] = 0;
        pq.add(new Edge(sourceIndex, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentDist = current.distance;
            int currentIndex = current.destination;

            if (visited[currentIndex]) {
                continue;
            }

            visited[currentIndex] = true;

            for (Edge neighbor : graph[currentIndex]) {
                int newDist = currentDist + neighbor.distance;
                if (newDist < distances[neighbor.destination]) {
                    distances[neighbor.destination] = newDist;
                    pq.add(new Edge(neighbor.destination, newDist));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();
        sp.addRoad("Edinburgh", "Perth", 100);
        sp.addRoad("Perth", "Dundee", 60);
        sp.addRoad("Stirling", "Perth", 40);
        sp.addRoad("Edinburgh", "Stirling", 50);
        sp.addRoad("Glasgow", "Stirling", 50);
        sp.addRoad("Edinburgh", "Glasgow", 70);

        int[] distances = sp.dijkstra("Edinburgh");
        int dundeeIndex = sp.getVertexIndex("Dundee");
        System.out.println("Shortest distance from Edinburgh to Dundee: " + distances[dundeeIndex]);
    }
}

