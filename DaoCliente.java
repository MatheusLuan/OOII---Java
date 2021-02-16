/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import model.Conexao;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;




public class DaoCliente {
  private Connection connection = null;
  private Statement statement = null;

  private void connect() {
    try {
      connection = Conexao.createConnection();
    } catch (ClassNotFoundException e) {
      System.out.println("\nClass Exception Error: " + e.getMessage());
    } catch (SQLException e) {
      System.out.println("\nSQLException Error: " + e.getMessage());
    }
  }

  private void disconnect() {
    try {
      connection.close();
    } catch (SQLException e) {
      System.out.println("\nSQLException Error: " + e.getMessage());
    }
  }

  public boolean store(Cliente Cliente) {
    boolean result = false;

    try {
      connect();

      String sql = "INSERT INTO tbcliente VALUES (NULL, ?, ?, ?, ?)";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, Cliente.getNome());
      preparedStatement.setString(2, Cliente.getEmail());
      preparedStatement.setString(3, Cliente.getTelefone());
      preparedStatement.setString(4, Cliente.getDocumento());

      preparedStatement.execute();
      preparedStatement.close();

      result = true;
    } catch (SQLException e) {
      System.out.println("SQLException when inserting into Cliente: " + e.getMessage());
    } finally {
      disconnect();
    }

    return result;
  }

  public boolean update(Cliente Cliente) {
    boolean result = false;

    try {
      connect();

      String sql = "UPDATE tbcliente SET nome = ?, email = ?, telefone = ?, documento = ? WHERE id = ?";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, Cliente.getNome());
      preparedStatement.setString(2, Cliente.getEmail());
      preparedStatement.setString(3, Cliente.getTelefone());
      preparedStatement.setString(4, Cliente.getDocumento());
      preparedStatement.setInt(5, Cliente.getId());

      preparedStatement.execute();
      preparedStatement.close();

      result = true;
    } catch (SQLException e) {
      System.out.println("SQLException when updating tbcliente: " + e.getMessage());
    } finally {
      disconnect();
    }

    return result;
  }

  public ArrayList<Cliente> index() {
    ArrayList<Cliente> results = new ArrayList<Cliente>();

    try {
      connect();

      String sql = "SELECT * FROM tbcliente";

      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        Cliente Cliente = new Cliente();

        Cliente.setId(resultSet.getInt("id"));
        Cliente.setNome(resultSet.getString("nome"));
        Cliente.setEmail(resultSet.getString("email"));
        Cliente.setTelefone(resultSet.getString("telefone"));
        Cliente.setDocumento(resultSet.getString("documento"));

        results.add(Cliente);
      }

      statement.close();
      resultSet.close();
    } catch (SQLException e) {
      System.out.println("SQLException when selecting tbcliente: " + e.getMessage());
    } finally {
      disconnect();
    }

    return results;
  }

  public ArrayList<Cliente> show(Cliente Cliente) {
    ArrayList<Cliente> result = new ArrayList<Cliente>();

    try {
      connect();

      String sql = "SELECT * FROM tbcliente WHERE id = " + Cliente.getId();

      statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        Cliente storedCliente = new Cliente();

        storedCliente.setId(resultSet.getInt("id"));
        storedCliente.setNome(resultSet.getString("nome"));
        storedCliente.setEmail(resultSet.getString("email"));
        storedCliente.setTelefone(resultSet.getString("telefone"));
        storedCliente.setDocumento(resultSet.getString("documento"));

        result.add(storedCliente);
      }

      statement.close();
      resultSet.close();
    } catch (SQLException e) {
      System.out.println("SQLException when selecting tbcliente: " + e.getMessage());
    } finally {
      disconnect();
    }

    return result;
  }
  
    public ArrayList<Cliente> show(String campo, String filtro) {
    ArrayList<Cliente> results = new ArrayList<Cliente>();
    
      if(!campo.equals("nome") && !campo.equals("email")){
          return results;
      }  

      try {
        connect();

       
       String sql = "SELECT * FROM tbcliente where "+campo +" like '%"+filtro+"%'";       
             
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
          Cliente Cliente = new Cliente();

          Cliente.setId(resultSet.getInt("id"));
          Cliente.setNome(resultSet.getString("nome"));
          Cliente.setEmail(resultSet.getString("email"));
          Cliente.setTelefone(resultSet.getString("telefone"));
          Cliente.setDocumento(resultSet.getString("documento"));

          results.add(Cliente);
        }

        statement.close();
        resultSet.close();
      } catch (SQLException e) {
        System.out.println("SQLException when selecting tbcliente: " + e.getMessage());
      } finally {
        disconnect();
      }

    return results;
  }

  public boolean delete(int id) {
    boolean result = false;

    try {
      connect();

      String sql = "DELETE FROM tbcliente WHERE id = ?";

      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, id);

      preparedStatement.execute();
      preparedStatement.close();

      result = true;
    } catch (SQLException e) {
      System.out.println("SQLException when deleting Cliente: " + e.getMessage());
    } finally {
      disconnect();
    }

    return result;
  }
}
