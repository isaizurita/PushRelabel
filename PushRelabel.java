import java.util.List;
import java.util.ArrayList;

public class PushRelabel {
    private List<Node> nodes; // Lista de nodos en el grafo

    // Constructor que inicializa el gráfico con el número dado de nodos 
    public PushRelabel(int nodeCount) 
        {
            nodes = new ArrayList<>(nodeCount);
            for (int i = 0; i < nodeCount; i++) 
                {
                    nodes.add(new Node(0, 0)); // Se agrega un nodo con flujo excedente y altura inicial a cero
                }
        }

    // Método para agregar una arista entre dos nodos con una capacidad dada
    public void addEdge(int source, int dest, int capacity) 
        {
            if (source < 0 || dest < 0 || source >= nodes.size() || dest >= nodes.size()) 
                {
                    throw new IllegalArgumentException("Invalid node index"); // Validación de los índices
                }
            nodes.get(source).addEdge(nodes.get(dest), 0, capacity); //Agrega la arista al nodo origen
        }

    // Método para obtener el flujo máximo en el grafo
    public int getMaxFlow() 
        {
            preflow(nodes.get(0)); // Se inicializa el preflujo desde el nodo fuente
            Node activeNode = getActiveNode();
            while (activeNode != null) // Mientras haya nodos activos
                {
                    if (!push(activeNode)) // Se intenta empujar el flujo
                        {
                            relabel(activeNode); // Si no se puede empujar, reajusta la altura del nodo
                        }
                    activeNode = getActiveNode(); // Se obteiene el siguiente nodo activo
                }
            return nodes.get(nodes.size() - 1).excessFlow; // Se devuelve el flujo excedente del nodo sumidero
        }

    // Método que inicializa el preflujo desde el nodo fuente
    private void preflow(Node s) 
        {
            s.height = nodes.size(); // Ajusta la altura del nodo fuente al número de nodos
            for (Edge e : s.edges) 
                {
                    e.flow = e.capacity; // Asigna flujo máximo a las aristas desde el nodo fuente
                    e.dest.excessFlow += e.flow; // Actualiza el flujo excedente del nodo destino
                    e.dest.addEdge(s, -e.flow, 0); // Agrega la arista inversa con flujo negativo
                }
        }

    // Método que ntenta empujar flujo desde el nodo dado
    private boolean push(Node n) 
        {
            for (Edge e : n.edges) 
                {
                    if (n.height > e.dest.height && e.flow != e.capacity) // Si la altura del nodo es mayor y la arista no está saturada
                        {
                            int flow = Math.min(e.capacity - e.flow, n.excessFlow); // Calcula el flujo a empujar
                            n.excessFlow -= flow; // Reduce el flujo excedente del nodo
                            e.dest.excessFlow += flow; // Aumenta el flujo excedente del nodo destino
                            e.flow += flow; // Actualiza el flujo de la arista
                            updateReverseEdge(e, flow); // Actualiza la arista inversa
                            printGraphStateGraphically(); // Imprimir estado del grafo después de cada empuje
                            return true; // Indica que se empujó flujo
                        }
                }
            return false; // No se pudo empujar flujo
        }

    // Método que imprime el estado gráfico del grafo
    public void printGraphStateGraphically() 
        {
            System.out.println("Representación gráfica del estado del grafo:\n");
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                System.out.println("Nodo " + i + ":");
                System.out.println("  Altura: " + node.height);
                System.out.println("  Flujo excedente: " + node.excessFlow);
                System.out.println("  Aristas:");
                for (Edge edge : node.edges) 
                    {
                        Node dest = edge.dest;
                        System.out.println("    " + i + " --" + edge.flow + "/" + edge.capacity + "--> " + nodes.indexOf(dest));
                    }
                System.out.println();
            }
        }
    
    // Método que reajusta la altura del nodo dado
    private void relabel(Node n) 
        {
            int minAdjHeight = Integer.MAX_VALUE;
            for (Edge e : n.edges) 
                {
                    if (e.flow != e.capacity && e.dest.height < minAdjHeight) // Encuentra la menor altura entre los nodos adyacentes
                        {
                            minAdjHeight = e.dest.height;
                            n.height = minAdjHeight + 1; // Ajusta la altura del nodo
                        }
                }
            printGraphStateGraphically(); // Imprimir estado del grafo después de cada etiquetado
        }

    // Método que obtiene un nodo activo (con flujo excedente)
    private Node getActiveNode() 
        {
            for (int i = 1; i < nodes.size() - 1; i++) // No incluye la fuent y el sumidero
                {
                    if (nodes.get(i).excessFlow > 0) 
                        {
                            return nodes.get(i);
                        }
                }
            return null; // No hay nodos activos
        }

    // Método que actualiza la arista inversa después de un empuje de flujo
    private void updateReverseEdge(Edge edge, int flow) 
        {
            for (Edge e : edge.dest.edges) 
                {
                    if (e.dest.equals(edge.source)) 
                        {
                            e.flow -= flow; // Actualiza el flujo de la arista inverss
                            return;
                        }
                }
            edge.dest.addEdge(edge.source, -flow, 0); // Si no existe, agrega una nueva arista inversa
        }

    // Inicialización de el grafo con nodos, aristas y capacidades dadas
    public void initializeGraph(int numVertices, int source, int sink, int[][] edges, int[] capacities) 
        {
            this.nodes = new ArrayList<>(numVertices);
            for (int i = 0; i < numVertices; i++) 
                {
                    this.nodes.add(new Node(0, 0)); // Inicializa los nodos
                }
            for (int i = 0; i < edges.length; i++) 
                {
                    int[] edge = edges[i];
                    int from = edge[0];
                    int to = edge[1];
                    int capacity = capacities[i];
                    this.addEdge(from, to, capacity); // Agrega las aristas con sus capacidades

                }
        }

    // Método que imprime el estado actual del grafo
    public void printGraphState() 
        {
            System.out.println("Estado del grafo:");
            for (int i = 0; i < nodes.size(); i++) 
                {
                    Node node = nodes.get(i);
                    System.out.println("Nodo " + i + ":");
                    System.out.println("  Altura: " + node.height);
                    System.out.println("  Flujo excedente: " + node.excessFlow);
                    System.out.println("  Aristas:");
                    for (Edge edge : node.edges) 
                        {
                            System.out.println("    Destino: " + nodes.indexOf(edge.dest));
                            System.out.println("    Flujo: " + edge.flow);
                            System.out.println("    Capacidad: " + edge.capacity);
                        }
                }
        }

    // Método que imprime el flujo máximo total del grafo
    public void printMaxFlow() 
        {
            System.out.println("FLUJO MÁXIMO TOTAL: " + getMaxFlow() + "\n");
        }

}
    