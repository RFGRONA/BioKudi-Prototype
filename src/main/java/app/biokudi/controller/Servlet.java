package app.biokudi.controller;

import app.biokudi.model.EcoPlacesConnection;
import app.biokudi.model.EcoPlaces;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;
import javax.sql.DataSource;

@WebServlet(name = "Servlet", urlPatterns = { "/Servlet" })
public class Servlet extends HttpServlet {

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
        String instruction = request.getParameter("instruction");
        if (instruction.equals("insert")) {
            insert(request, response);
            response.sendRedirect("ListServlet");
        } else if (instruction.equals("edit")) {
            edit(request, response);
            response.sendRedirect("ListServlet");
        } else if (instruction.equals("update")) {
            update(request, response);
            response.sendRedirect("ListServlet");
        } else if (instruction.equals("delete")) {
            delete(request, response);
            response.sendRedirect("ListServlet");
        } else if (instruction.equals("activity")) {
            response.sendRedirect("ActServlet");
        } else {
            doGet(request, response);
        }
    }

    protected void insert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String coordinate = request.getParameter("coordinate");
            String description = request.getParameter("description");
            String activity = request.getParameter("activity");
            String information = request.getParameter("information");

            EcoPlaces place = new EcoPlaces(name, address, coordinate, activity, description, information);
            connectEcoPlaces.addPlace(place);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: insert");
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPlace = Integer.parseInt(request.getParameter("idPlace"));
            EcoPlaces editPlace = connectEcoPlaces.getPlace(idPlace);
            request.setAttribute("editPlace", editPlace);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: edit");
        }
    }

    protected void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idPlace = Integer.parseInt(request.getParameter("idPlace"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String coordinate = request.getParameter("coordinate");
            String description = request.getParameter("description");
            String activity = request.getParameter("activity");
            String information = request.getParameter("information");

            EcoPlaces updatedPlace = new EcoPlaces(idPlace, name, address, coordinate, activity, description,
                    information);
            connectEcoPlaces.updatePlace(updatedPlace);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: update");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idPlace = Integer.parseInt(request.getParameter("idPlace"));
            connectEcoPlaces.deletePlace(idPlace);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Servlet: delete");
        }
    }

}