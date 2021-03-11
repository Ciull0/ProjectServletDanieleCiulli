package it.itsvita.byte19.ufc9.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {
	
	private static final String DB_NAME = "webloginsecure";	//così non sbagli a inserire i nomi
    private static final String DB_USER = "webloginapp";
    private static final String DB_HOST = "localhost";
    private static final String DB_PASS = "app";
    
    private static final String TBL_USERS = "users";
    
    private static final String FLD_USERNAME = "username";
    private static final String FLD_PASSWORD = "password";

    
    private Connection establishConnection() throws ClassNotFoundException, SQLException{
        Connection connection = null;
        
        try 
        {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");

            // Setup the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://"+ DB_HOST + ":3306/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS);
        }
        catch (ClassNotFoundException | SQLException exception) 
        {
            throw exception;
        } 
        
        return connection;
    }

    
    private void close(ResultSet resultSet, Statement statement, Connection connection) {
        try 
        {
            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (connection != null) 
                connection.close();
        } 
        catch (SQLException exception) 
        {
            System.err.println(exception);
        }
    }


    
    public boolean isNameDefined(String name)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        boolean found = false;
        
        try 
        {
            connection = establishConnection();
            statement = connection.createStatement();
  
            String sql = "SELECT " + FLD_USERNAME + " FROM " + DB_NAME + "." + TBL_USERS + " WHERE " + FLD_USERNAME + " = '" + name + "'";        
            resultSet = statement.executeQuery(sql);
            
            if(resultSet != null)
                found = resultSet.next();
        } 
        catch (ClassNotFoundException | SQLException exception) 
        {
            System.err.println(exception);
        } 
        finally 
        {
            close(resultSet, statement, connection);
        }
         
        return found;
    }
    
    

    
    public boolean isPasswordVerified(String name, String password)
    {
     Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        boolean verified = false;
        
     // Password cannot be empty or null
     if(password == null || password.isEmpty())
     {
      return verified;
     }
        
        try 
        {
            connection = establishConnection();
            statement = connection.createStatement();
  
            String sql = "SELECT " + FLD_PASSWORD + " FROM " + DB_NAME + "." + TBL_USERS + " WHERE " + FLD_USERNAME + " = '" + name + "' AND " + FLD_PASSWORD + " = '" + password + "'";        
            resultSet = statement.executeQuery(sql);
            
            if(resultSet != null)
             verified = resultSet.next();

        } 
        catch (ClassNotFoundException | SQLException exception) 
        {
            System.err.println(exception);
        } 
        finally 
        {
            close(resultSet, statement, connection);
        }
         
        return verified;
    }
    
    
    
    public boolean addName(String name, String password)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        // Name and password cannot be empty or null
        if(name == null || password == null || name.isEmpty() || password.isEmpty()) {
         return false;
        }
        
        try 
        {
            connection = establishConnection(); 
            statement = connection.createStatement();

            String sql = "INSERT INTO " + DB_NAME + "." + TBL_USERS + " VALUES ('" + name + "','" + password + "', null)";         
            statement.executeUpdate(sql);
        } 
        catch (ClassNotFoundException | SQLException exception) 
        {
            System.err.println(exception);
        } 
        finally 
        {
            close(resultSet, statement, connection);
        }
        
        return true;
    }

    

    
}
