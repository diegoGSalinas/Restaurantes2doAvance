/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;
import Dao.ProductoDao;
import Modelo.Carrito;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author dkred
 */

public class ControladorCarrito extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);

            ProductoDao productoDAO = new ProductoDao();
            Producto producto = productoDAO.obtenerProductoPorId(id);

            if (producto != null) {
                Carrito item = new Carrito(producto.getNombreProducto(), 1, producto.getPrecioProducto());

                HttpSession session = request.getSession();
                ArrayList<Carrito> carrito = (ArrayList<Carrito>) session.getAttribute("carrito");
                if (carrito == null) {
                    carrito = new ArrayList<>();
                }

                // Verificar si ya existe el producto en el carrito
                boolean existe = false;
                for (Carrito c : carrito) {
                    if (c.getProducto().equals(producto.getNombreProducto())) {
                        c.setCantidad(c.getCantidad() + 1);
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    carrito.add(item);
                }

                session.setAttribute("carrito", carrito);
            }
        }
        response.sendRedirect("controladorProducto");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String accion = request.getParameter("accion");

        if ("actualizar".equals(accion)) {
            // Actualizar cantidades
            HttpSession session = request.getSession();
            ArrayList<Carrito> carrito = (ArrayList<Carrito>) session.getAttribute("carrito");
            int size = Integer.parseInt(request.getParameter("size"));

            for (int i = 0; i < size; i++) {
                String nombre = request.getParameter("producto_" + i);
                int cantidad = Integer.parseInt(request.getParameter("cantidad_" + i));

                for (Carrito item : carrito) {
                    if (item.getProducto().equals(nombre)) {
                        item.setCantidad(cantidad);
                        break;
                    }
                }
            }
            session.setAttribute("carrito", carrito);
            response.sendRedirect("JSP/verCarrito.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
