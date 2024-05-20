public class Tester {
  
    public static void main(String[] args) 
      {
        PushRelabel pr = new PushRelabel(7); // Número de nodos
        pr.addEdge(0, 1, 8);
        pr.addEdge(0, 2, 9);
        pr.addEdge(0, 3, 5);
        pr.addEdge(1, 4, 6);
        pr.addEdge(2, 3, 7);
        pr.addEdge(2, 5, 5);
        pr.addEdge(3, 4, 2);
        pr.addEdge(3, 5, 6);
        pr.addEdge(4, 5, 4);
        pr.addEdge(4, 6, 11);
        pr.addEdge(5, 6, 13);
        // Flujo máximo debe ser 19.

        pr.getMaxFlow(); // Ejecutar el algoritmo
        pr.printMaxFlow(); // Imprimir el flujo máximo encontrado
      }
  
  }