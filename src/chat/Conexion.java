/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.Action.DEFAULT;

/**
 *
 * @author pedro
 */
public class Conexion {
    
    
    public static Connection conexion() throws SQLException
    {
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
           e.printStackTrace();
        } 
        
        Connection con = DriverManager.getConnection ("jdbc:mysql://localhost/chat","root", "root");
        return con;
    }
    
    public void enviarMensaje(String mensaje, String alias) throws SQLException
    {
        Connection con = conexion();
        String query = " insert into mensajes (id, mensaje, alias)"
        + " values (DEFAULT, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);  
        preparedStmt.setString (1, mensaje);
        preparedStmt.setString (2, alias);
        preparedStmt.execute();
        con.close();
        
        
    }
    
    public String recibirDatos() throws SQLException
    {
        String mensajes=null;
        Connection con = conexion();
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery ("select mensaje from mensajes");
        
        while (rs.next())
        {
            mensajes += rs.getString(1)+"\n";
        }
        return mensajes;
        
    }
    
}
