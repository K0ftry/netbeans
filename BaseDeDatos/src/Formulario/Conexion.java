/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author KoF
 */
public class Conexion {
   
    static String bd="registros";
    static String login="root";
    static String password="";
    static String url="jdbc:mysql://localhost:3306/"+bd;
    
    public Connection conectar(){
     
        Connection con=null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,login,password);
            System.out.println("En línea");
        }catch(Exception ex){
           System.out.println("Error: "+ex.getMessage()); 
        }
        return con;
    }
    
    public void guardar(String rut, String nombre, int edad, String ruta){
        
        Connection con=conectar();
        String insert="insert into empleados(rut,nombre,edad,imagen) values(?,?,?,?)";
        FileInputStream fi=null;
        PreparedStatement ps=null;
        
        try{
            File file =new File(ruta);
            fi=new FileInputStream(file);
            ps=con.prepareStatement(insert);
            ps.setString(1, rut);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
            ps.setBinaryStream(4, fi);
            ps.executeUpdate();
        }catch(Exception ex){
           System.out.println("Error al agregarempleado: "+ex.getMessage()); 
        }
    }
}
