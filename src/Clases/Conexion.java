package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    
    private Connection conn;

    public Conexion() {
        verificar();
        conectarse();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public boolean verificar(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        }
        catch(ClassNotFoundException ex)
        {
            return false;            
        }
    }
    
    public boolean conectarse(){
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mifare","root","");
            return true;
        }
        catch(SQLException ex)
        {
            return false;
        }
    }
    
    public PreparedStatement crearSentencia(String consulta){
        try
        {
            return conn.prepareStatement(consulta);
        }
        catch(SQLException ex)
        {
            return null;
        }
    }
    
    
}















