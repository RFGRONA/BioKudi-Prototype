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
                out.println("<table><tr><th><span><h1>" + place.getName() + "</h1></span></th></tr>");
                out.println("<tr><td><span class=\"title\">Actividad</span><br><p>" + place.getActivity() + "</p></td></tr>");
                out.println("<tr><td><span class=\"title\">Dirección</span><br><p>" + place.getAddress() + "</p></td></tr>");
                out.println("<tr><td><span class=\"title\">Descripción</span><br><p>" + place.getDescription() + "</p></td></tr>");
                out.println("<tr><td><span class=\"title\">Información</span><br><p>" + place.getInformation() + "</p></td></tr>");
                out.println("<tr><td><a href=\"index.html\" id=\"goHomeBtn\">Volver al inicio</a></td></tr></table>");
                out.println("<script> document.getElementById(\"goHomeBtn\").addEventListener(\"click\", function () { parent.location.href = \"index.html\"; }); </script>");
            }
        } catch (SQLException ex) {
            System.out.println("Error: POST MapServlet");
            ex.printStackTrace();
        }
    }

}