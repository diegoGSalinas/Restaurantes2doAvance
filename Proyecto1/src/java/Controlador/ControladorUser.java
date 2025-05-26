/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Usuario;
import Dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author dkred
 */
public class ControladorUser extends HttpServlet{
    private final UsuarioDao udao = new UsuarioDao();
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica del controlador
       
        List<Usuario> usuarios = udao.listar(); 
        //response.getWriter().write("Mensaje desde el controlador");
                // Pasar la lista como atributo al JSP
                System.out.println("Estacionamientos enviados al JSP: " + usuarios.size());
               // request.setAttribute("estacionamientos", usuarios);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Procesar petición POST
        
        String accion = request.getParameter("accion");
        if ("Login".equals(accion)) {
            String username = request.getParameter("username");
            String clave = request.getParameter("password");

            UsuarioDao dao = new UsuarioDao();
            Usuario u = dao.validar(username, clave);

             if (u != null) {
                 request.getSession().setAttribute("usuario", u);
                response.sendRedirect("controladorPrincipal"); // página principal
            } else {
                request.setAttribute("error", "Credenciales inválidas");
                request.getRequestDispatcher("JSP/login.jsp").forward(request, response);
            }
        }
        else if("Registrar".equals(accion)){
            String username = request.getParameter("username");
            String clave = request.getParameter("password");
            String nombre = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String correo = request.getParameter("correo");
            
            int prioridad = 2;
            UsuarioDao dao = new UsuarioDao();
            Usuario u = new Usuario(username,clave,prioridad);
            dao.agregar(u);
            request.getSession().setAttribute("usuario", u);
            response.sendRedirect("controladorPrincipal"); // página principal
        }
    }
}
