public class Tester3 {
  
    public static void main(String[] args) 
      {
        PushRelabel pr = new PushRelabel(5); // Número de nodos
        pr.addEdge(0, 1, 20);
        pr.addEdge(0, 2, 30);
        pr.addEdge(0, 3, 10);
        pr.addEdge(1, 2, 40);
        pr.addEdge(1, 4, 20);
        pr.addEdge(2, 4, 20);
        pr.addEdge(3, 2, 10);
        pr.addEdge(3, 4, 20);
        // Flujo máximo debe ser 60.

        pr.getMaxFlow(); // Ejecutar el algoritmo
        pr.printMaxFlow(); // Imprimir el flujo máximo encontrado
      }
  
  }