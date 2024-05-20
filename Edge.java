public class Edge {
    Node source; // Nodo fuente
    Node dest; // Nodo destineo
    int flow; // Variable para almacenar el flujo actual de la arista
    int capacity; // Capacidad máxima

    Edge(Node source, Node dest, int flow, int capacity) 
        {
            this.source = source;
            this.dest = dest;
            this.flow = flow;
            this.capacity = capacity;
        }
}