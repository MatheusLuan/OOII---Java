
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection connection = null;  
  
    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/dbcliente?user=root&password=12345";

        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(url);

        return connection;
    }
}