import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseReader {

    // Método para conectar con la base de datos
    public static Connection connectToDatabase(String url, String user, String password) throws SQLException 
        {
            return DriverManager.getConnection(url, user, password);
        }

    // Método para leer datos de la base de datos y inicializar el grafo
    public static void initializeGraphFromDatabase(PushRelabel pr, Connection connection) throws SQLException 
        {
            // Consulta SQL para obtener los bordes y capacidades de las aristas
            String query = "SELECT source, destination, capacity FROM edges";
            
            try (PreparedStatement statement = connection.prepareStatement(query)) 
                {
                    // Ejecutar la consulta
                    ResultSet resultSet = statement.executeQuery();
                    
                    // Procesar los resultados y agregar los bordes al grafo
                    while (resultSet.next()) 
                        {
                            int source = resultSet.getInt("source");
                            int destination = resultSet.getInt("destination");
                            int capacity = resultSet.getInt("capacity");
                            pr.addEdge(source, destination, capacity);
                        }
                }
        }
}