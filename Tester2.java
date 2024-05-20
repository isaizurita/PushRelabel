public class Tester2 {
  
    public static void main(String[] args) 
      {
        PushRelabel pr = new PushRelabel(8); // Número de nodos
        pr.addEdge(0, 1, 7);
        pr.addEdge(0, 3, 25);
        pr.addEdge(0, 4, 5);
        pr.addEdge(1, 2, 10);
        pr.addEdge(2, 7, 18);
        pr.addEdge(3, 2, 20);
        pr.addEdge(3, 5, 9);
        pr.addEdge(4, 5, 4);
        pr.addEdge(4, 6, 15);
        pr.addEdge(5, 7, 40);
        pr.addEdge(6, 7, 9);
        // Flujo máximo debe ser 37.

        pr.getMaxFlow(); // Ejecutar el algoritmo
        pr.printMaxFlow(); // Imprimir el flujo máximo encontrado
      }
  
  }