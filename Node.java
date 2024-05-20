import java.util.ArrayList;
import java.util.List;

public class Node {
    int height; // Altura dle nodo
    int excessFlow; // Flujo excedente del nodo
    List<Edge> edges; // Lista de aristas que salen de cada nodo

    Node(int height, int excessFlow) 
        {
            this.height = height;
            this.excessFlow = excessFlow;
            edges = new ArrayList<>();
        }

    // Método para agregar una arista desde este nodo a otro
    void addEdge(Node dest, int flow, int capacity) 
        {
            Edge edge = new Edge(this, dest, flow, capacity);
            edges.add(edge);
        }
}

