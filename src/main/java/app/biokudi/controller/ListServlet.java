package app.biokudi.controller;

import app.biokudi.model.EcoPlacesConnection;
import app.biokudi.model.EcoPlaces;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "ListServlet", urlPatterns = {"/ListServlet"})
public class ListServlet extends HttpServlet {
    
    // This class uses the Post-Redirect-Get pattern to avoid cloning data when the page is reloaded
    
    private EcoPlacesConnection connectEcoPlaces;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    // Calls the connection pool set in context.xml
    @Resource(name = "jdbc/biokudi")
    private DataSource dataPool;
    
    // Initialize the connection through the connection pool
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connectEcoPlaces = new EcoPlacesConnection(dataPool);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Receives an ArrayList with all the information from the database and sends it to list.jsp
        List<EcoPlaces> places;
        try {
            places = connectEcoPlaces.getListPlaces();
            System.out.println(places);
            request.setAttribute("listPlaces", places);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Servlet: doGet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}