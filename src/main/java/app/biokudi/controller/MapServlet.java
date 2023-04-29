package app.biokudi.controller;

import app.biokudi.model.EcoPlaces;
import app.biokudi.model.EcoPlacesConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "MapServlet", urlPatterns = {"/MapServlet"})
public class MapServlet extends HttpServlet {

    private EcoPlacesConnection connectEcoPlaces;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // Calls the connection pool set in context.xml
    @Resource(name = "jdbc/biokudi")
    private DataSource dataPool;

    //Initialize the connection through the connection pool
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connectEcoPlaces = new EcoPlacesConnection(dataPool);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Send to the view an ArrayList with all the information from the database
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<EcoPlaces> places;
        try {
            places = connectEcoPlaces.getListMarkers();
            System.out.println(places);
            request.setAttribute("listPlaces", places);
            request.getRequestDispatcher("ecomap.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error MapServlet: doGet");
        }
    }

    // Returns to the view the html code with all the information of the selected place
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idPlace = Integer.parseInt(request.getParameter("idPlace"));
            EcoPlaces place = connectEcoPlaces.getPlace(idPlace);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2>Información del lugar:</h2>");
                out.println("<p><strong>Nombre: </strong>" + place.getName() + "</p>");
                out.println("<p><strong>Actividad: </strong>" + place.getActivity() + "</p>");
                out.println("<p><strong>Dirección: </strong>" + place.getAddress() + "</p>");
                out.println("<p><strong>Descripción: </strong><br>" + place.getDescription() + "</p>");
                out.println("<p><strong>Información: </strong><br>" + place.getInformation() + "</p>");
                out.println("<a href=\"index.html\" id=\"goHomeBtn\">Volver al inicio</a>");
                out.println("<script> document.getElementById(\"goHomeBtn\").addEventListener(\"click\", function () { parent.location.href = \"index.html\"; }); </script>");
            }
        } catch (SQLException ex) {
            System.out.println("Error: POST MapServlet");
            ex.printStackTrace();
        }
    }

}