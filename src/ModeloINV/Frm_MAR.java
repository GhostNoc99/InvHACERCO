package ModeloINV;

import ControladorINV.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Frm_MAR {

    ConexionBD conexion = new ConexionBD();
    Connection cx;
    PreparedStatement ps, as, df, gh;
    ResultSet rs;

    public List Listar() {
        String sql = "Select * from materiales";
        List<Frm_Mat> lista = new ArrayList<>();
        try {
            cx = conexion.conectar();
            ps = cx.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Frm_Mat fm = new Frm_Mat();
                fm.setId(rs.getInt(1));
                fm.setArticulo(rs.getString(2));
                fm.setMarca(rs.getString(3));
                fm.setCant(rs.getInt(4));
                fm.setAnot(rs.getString(5));
                fm.setCrea(rs.getString(6));
                lista.add(fm);

            }

        } catch (SQLException e) {
            System.out.println("Error listar:" + e);
        }
        return lista;

    }

    public void agregar(Frm_Mat pro) {
        String sql = "insert into materiales (ARTICULO, MARCA, CANTIDAD, ANOTACIONES) value (?,?,?,?);";

        try {
            cx = conexion.conectar();
            ps = cx.prepareStatement(sql);
            ps.setString(1, pro.getArticulo());
            ps.setString(2, pro.getMarca());
            ps.setInt(3, pro.getCant());
            ps.setString(4, pro.getAnot());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en agregar: " + e);

        }

    }

    public void act(Frm_Mat pro) {

        String sql = "update materiales set articulo = ?, marca = ?, cantidad = ?, anotaciones = ?, DATE_CREATEDDA = current_timestamp() where id_mat = ?;";
        try {
            cx = conexion.conectar();
            ps = cx.prepareStatement(sql);
            ps.setString(1, pro.getArticulo());
            ps.setString(2, pro.getMarca());
            ps.setInt(3, pro.getCant());
            ps.setString(4, pro.getAnot());
            ps.setInt(5, pro.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en act: " + e);
        }

    }

    /*public void delete(int id) {
        String sql = "delete from materiales where id_mat =" + id;
        try {
             cx = conexion.conectar();
             ps = cx.prepareStatement(sql);
             ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en delete: " + e);
        }
    }*/
    public void delete(int id) {
        String sql = "delete from materiales where id_mat =" + id;
        String sql1 = "set @num := 0;";
        String sql2 = "UPDATE materiales SET id_mat = @num := (@num+1);";
        String sql3 = "ALTER TABLE materiales AUTO_INCREMENT = 1;";

        try {
            cx = conexion.conectar();
            ps = cx.prepareStatement(sql);
            as = cx.prepareStatement(sql1);
            df = cx.prepareStatement(sql2);
            gh = cx.prepareStatement(sql3);

            ps.executeUpdate();
            as.executeUpdate();
            df.executeUpdate();
            gh.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en del: " + e);
        }

    }
    
    public List search(String fil, String valor){
    
         String sql = "select * from materiales where " + fil + " like '%" + valor + "%';";
         System.out.println(sql);
         List<Frm_Mat> lista = new ArrayList<>();
        try {
            cx = conexion.conectar();
            ps = cx.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) {
                Frm_Mat fm = new Frm_Mat();
                fm.setId(rs.getInt(1));
                fm.setArticulo(rs.getString(2));
                fm.setMarca(rs.getString(3));
                fm.setCant(rs.getInt(4));
                fm.setAnot(rs.getString(5));
                fm.setCrea(rs.getString(6));
                lista.add(fm);    
        
        }} catch (SQLException e) {
            System.out.println("Error en search: " + e);
        }
        return lista;
    
    }}   
    

   

