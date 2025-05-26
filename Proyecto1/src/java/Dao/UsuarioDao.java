/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import configuracion.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDao
{
    Conexion conexion=Conexion.Obtener_Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public List listar()
    {
        List<Usuario> usuarios = new ArrayList();
        String sql="select * from usuarios";
        
         
        try
        {
            con = conexion.Iniciar_Conexion();
            
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery(); 
            
            while(rs.next())
            {
                Usuario u= new Usuario(rs.getString(2),rs.getString(3),rs.getInt(4));
                usuarios.add(u);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error en Usuarios_DAO-Listar:\n"+e);
        }
        return usuarios;
    }
    public Usuario validar(String username,String password)
    {
         Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE username=? AND password=?";
        try 
        {
            con = conexion.Iniciar_Conexion();

            ps= con.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2, password); 
            ResultSet rs2 = ps.executeQuery();
            System.out.println(ps);
            if (rs2.next()) {
                u = new Usuario(rs2.getString("username"),rs2.getString("password"),rs2.getInt("prioridad"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    public int agregar(Usuario user)
    {   
        int idGenerado = -1;
        String sql="insert into usuarios"
                + "(username,password,prioridad)"
                + "values "
                + "(?,?,?)";
        try
        {
            con=conexion.Iniciar_Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getPrioridad());
            ps.executeUpdate();
         rs = ps.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1); // Aquí obtienes el id generado
        }
        } catch (SQLException e) {
            System.out.println("Error en Usuarios_DAO-Agregar:\n" + e);
        } finally {
            // Aquí podrías cerrar ps, rs y con si los estás manejando manualmente
        }

        return idGenerado;
    }
}