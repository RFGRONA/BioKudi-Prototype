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
import javax.sql.DataSource;
import java.util.List;

@WebServlet(urlPatterns = {"/MapaServlet"})
public class MapaServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

}
