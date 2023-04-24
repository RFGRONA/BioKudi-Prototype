package app.biokudi.controller;

import app.biokudi.model.Conexion;
import app.biokudi.model.Lugares;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private Conexion conexion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Resource(name = "jdbc/biokudi")
    private DataSource poolConexiones;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            conexion = new Conexion(poolConexiones);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String instruccion = request.getParameter("instruccion");
        if (instruccion.equals("insertar")) {
            insertar(request, response);
            response.sendRedirect("ListarServlet");
        } else if (instruccion.equals("editar")) {
            editar(request, response);
            response.sendRedirect("ListarServlet");
        } else if (instruccion.equals("actualizar")) {
            actualizar(request, response);
            response.sendRedirect("ListarServlet");
        } else if (instruccion.equals("eliminar")) {
            eliminar(request, response);
            response.sendRedirect("ListarServlet");
        } else {
            listar(request, response);
        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lugares> lugares;
        try {
            lugares = conexion.getLugares();
            System.out.println(lugares);
            request.setAttribute("listaLugares", lugares);
            request.getRequestDispatcher("lista.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Servlet: listar");
        }
    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String coordenadas = request.getParameter("coordenadas");
            String descripcion = request.getParameter("descripcion");
            String actividad = request.getParameter("actividad");
            String informacion = request.getParameter("informacion");

            Lugares lugar = new Lugares(nombre, direccion, coordenadas, actividad, descripcion, informacion);
            conexion.agregarLugar(lugar);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: insertar");
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idLugar = Integer.parseInt(request.getParameter("idLugar"));
            Lugares editarLugar = conexion.getLugar(idLugar);
            request.setAttribute("editarLugar", editarLugar);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: editar");
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idLugar = Integer.parseInt(request.getParameter("idLugar"));
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String coordenadas = request.getParameter("coordenadas");
            String descripcion = request.getParameter("descripcion");
            String actividad = request.getParameter("actividad");
            String informacion = request.getParameter("informacion");

            Lugares lugarActualizado = new Lugares(idLugar, nombre, direccion, coordenadas, actividad, descripcion, informacion);
            conexion.actualizar(lugarActualizado);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: actualizar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idLugar = Integer.parseInt(request.getParameter("idLugar"));
            conexion.borrarLugar(idLugar);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: eliminar");
        }
    }

}
