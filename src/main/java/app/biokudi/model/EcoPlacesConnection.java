package app.biokudi.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class EcoPlacesConnection implements iConnection<EcoPlaces>{

    private DataSource rootData;
    
    // Calls the connection data set in context.xml
    public EcoPlacesConnection(DataSource rootData) {
        this.rootData = rootData;
    }
    
    @Override
    public List<EcoPlaces> getListPlaces() throws SQLException {
        // Returns in an ArrayList all the information from the database
        List<EcoPlaces> places = new ArrayList<>();
        Connection connect = null;
        Statement declaration = null;
        try {
            String instruction = "SELECT * FROM lugares";
            connect = rootData.getConnection();
            declaration = connect.createStatement();
            ResultSet result = declaration.executeQuery(instruction);

            while (result.next()) {
                int idPlace = result.getInt("ID");
                String name = result.getString("NOMBRE");
                String address = result.getString("DIRECCION");
                String coordinate = result.getString("COORDENADAS");
                String activity = result.getString("ACTIVIDAD");
                String description = result.getString("DESCRIPCION");
                String information = result.getString("INFORMACION");

                EcoPlaces tempPlace = new EcoPlaces(idPlace, name, address, coordinate, activity, description, information);
                places.add(tempPlace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getListPlaces - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }

        return places;
    }
    
    
    public List<EcoPlaces> getListActivities() throws SQLException {
        // Returns in an ArrayList all the information from the database 
        List<EcoPlaces> places = new ArrayList<>();
        Connection connect = null;
        Statement declaration = null;
        try {
            String instruction = "SELECT * FROM lugares";
            connect = rootData.getConnection();
            declaration = connect.createStatement();
            ResultSet result = declaration.executeQuery(instruction);

            while (result.next()) {
                String name = result.getString("NOMBRE");
                String address = result.getString("DIRECCION");
                String activity = result.getString("ACTIVIDAD");
                String description = result.getString("DESCRIPCION");
                String information = result.getString("INFORMACION");

                EcoPlaces tempPlace = new EcoPlaces(name, address, activity, description, information);
                places.add(tempPlace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getListPlaces - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }

        return places;
    }
    
    public List<EcoPlaces> getListMarkers() throws SQLException {
        // Returns in an ArrayList all the information from the database
        List<EcoPlaces> places = new ArrayList<>();
        Connection connect = null;
        Statement declaration = null;
        try {
            String instruction = "SELECT * FROM lugares";
            connect = rootData.getConnection();
            declaration = connect.createStatement();
            ResultSet result = declaration.executeQuery(instruction);

            while (result.next()) {
                int idPlace = result.getInt("ID");
                String name = result.getString("NOMBRE");
                String coordinate = result.getString("COORDENADAS");

                EcoPlaces tempPlace = new EcoPlaces(idPlace, name, coordinate);
                places.add(tempPlace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getListMarkers - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }

        return places;
    }
    
    
    
    @Override
    public void addPlace(EcoPlaces place) throws SQLException {
        // Receives an object of type EcoPlaces and adds it to the database
        Connection connect = null;
        PreparedStatement declaration = null;
        try {
            connect = rootData.getConnection();
            String instruction = "INSERT INTO lugares (NOMBRE, DIRECCION, COORDENADAS, ACTIVIDAD, DESCRIPCION, INFORMACION) VALUES (?, ?, ?, ?, ?, ?)";
            declaration = connect.prepareStatement(instruction);
            declaration.setString(1, place.getName());
            declaration.setString(2, place.getAddress());
            declaration.setString(3, place.getCoordinate());
            declaration.setString(4, place.getActivity());
            declaration.setString(5, place.getDescription());
            declaration.setString(6, place.getInformation());
            declaration.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error addPlace - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }
    }
    
    @Override
    public EcoPlaces getPlace(int idPlace) throws SQLException {
        /* Receives the ID of a corresponding place in the database and returns 
           all the information in the database in an object of type EcoPlaces */
        EcoPlaces place = null;
        Connection connect = null;
        PreparedStatement declaration = null;
        try {
            connect = rootData.getConnection();
            String instruction = "SELECT * FROM lugares WHERE ID=?";
            declaration = connect.prepareStatement(instruction);
            declaration.setInt(1, idPlace);
            ResultSet result = declaration.executeQuery();
            if (result.next()) {
                int id = result.getInt("ID");
                String name = result.getString("NOMBRE");
                String address = result.getString("DIRECCION");
                String coordinate = result.getString("COORDENADAS");
                String activity = result.getString("ACTIVIDAD");
                String description = result.getString("DESCRIPCION");
                String information = result.getString("INFORMACION");
                place = new EcoPlaces(idPlace, name, address, coordinate, activity, description, information);
            } else {
                throw new Exception("No data found with ID: " + idPlace);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error getPlace - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }
        return place;
    }
    
    @Override
    public void updatePlace(EcoPlaces updatedPlace) throws SQLException {
        /* Receives an object of type EcoPlaces and overwrites the 
           information in the database that has the same ID */
        Connection connect = null;
        PreparedStatement declaration = null;
        try {
            connect = rootData.getConnection();
            String instruction = "UPDATE lugares SET NOMBRE=?, DIRECCION=?, COORDENADAS=?, ACTIVIDAD=?, DESCRIPCION=?, INFORMACION=? WHERE ID=?";
            declaration = connect.prepareStatement(instruction);
            declaration.setString(1, updatedPlace.getName());
            declaration.setString(2, updatedPlace.getAddress());
            declaration.setString(3, updatedPlace.getCoordinate());
            declaration.setString(4, updatedPlace.getActivity());
            declaration.setString(5, updatedPlace.getDescription());
            declaration.setString(6, updatedPlace.getInformation());
            declaration.setInt(7, updatedPlace.getIdPlace());
            System.out.println(updatedPlace.getIdPlace());
            declaration.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updatePlace - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }
    }
    
    @Override
    public void deletePlace(int idPlace) throws SQLException {
        // Receives the id corresponding to a place in the database and deletes the record from the database
        Connection connect = null;
        PreparedStatement declaration = null;
        try {
            connect = rootData.getConnection();
            String instruction = "DELETE FROM lugares WHERE ID=?";
            declaration = connect.prepareStatement(instruction);
            declaration.setInt(1, idPlace);
            declaration.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error deletePlace - EcoPlaces");
        } finally {
            declaration.close();
            connect.close();
        }
    }

}