package Electricity;

import java.sql.*;  

public class Conn{
    Connection connection;
    Statement statement;



    public Conn(){
        try{  
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///electricity_billing_system","root","");
            statement = connection.createStatement();
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}  