package ControladorINV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBD {

    String url = "jdbc:mysql://localhost:3306/invhacerco";
    String user = "root";
    String password = "root";
    Connection cx;

    
    public Connection conectar() {
        try {
            //Class.forName(driver);
            cx = DriverManager.getConnection(url, user, password);
            System.out.println("SE CONECTO A BD ");
        } catch (SQLException ex) {
            System.out.println("NO SE CONECTO A BD ");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);

        }
        return cx;
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();
        conexion.conectar();

    }*/
}
