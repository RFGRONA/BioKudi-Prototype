package app.biokudi.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class Conexion {

    private DataSource origenDatos;

    public Conexion(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    public List<Lugares> getLugares() throws Exception {

        List<Lugares> lugares = new ArrayList<>();
        Connection conexion = null;
        Statement declaracion = null;
        try {
            String instruccion = "SELECT * FROM lugares";
            conexion = origenDatos.getConnection();
            declaracion = conexion.createStatement();
            ResultSet resultado = declaracion.executeQuery(instruccion);

            while (resultado.next()) {
                int idLugar = resultado.getInt("ID");
                String nombre = resultado.getString("NOMBRE");
                String direccion = resultado.getString("DIRECCION");
                String coordenada = resultado.getString("COORDENADAS");
                String actividad = resultado.getString("ACTIVIDAD");
                String descripcion = resultado.getString("DESCRIPCION");
                String informacion = resultado.getString("INFORMACION");

                Lugares tempLugar = new Lugares(idLugar, nombre, direccion, coordenada, actividad, descripcion, informacion);
                lugares.add(tempLugar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en getLugares");
        } finally {
            declaracion.close();
            conexion.close();
        }

        return lugares;
    }

    public void agregarLugar(Lugares lugar) throws SQLException {
        Connection conexion = null;
        PreparedStatement declaracion = null;
        try {
            conexion = origenDatos.getConnection();
            String instruccion = "INSERT INTO lugares (NOMBRE, DIRECCION, COORDENADAS, ACTIVIDAD, DESCRIPCION, INFORMACION) VALUES (?, ?, ?, ?, ?, ?)";
            declaracion = conexion.prepareStatement(instruccion);
            declaracion.setString(1, lugar.getNombre());
            declaracion.setString(2, lugar.getDireccion());
            declaracion.setString(3, lugar.getCoordenada());
            declaracion.setString(4, lugar.getActividad());
            declaracion.setString(5, lugar.getDescripcion());
            declaracion.setString(6, lugar.getInformacion());
            declaracion.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en agregar lugar");
        } finally {
            declaracion.close();
            conexion.close();
        }
    }

    public Lugares getLugar(int idLugar) throws SQLException {
        int idSitio = idLugar;
        Lugares lugar = null;
        Connection conexion = null;
        PreparedStatement declaracion = null;
        try {
            conexion = origenDatos.getConnection();
            String instruccion = "SELECT * FROM lugares WHERE ID=?";
            declaracion = conexion.prepareStatement(instruccion);
            declaracion.setInt(1, idSitio);
            ResultSet resultado = declaracion.executeQuery();
            if (resultado.next()) {
                int id = resultado.getInt("ID");
                String nombre = resultado.getString("NOMBRE");
                String direccion = resultado.getString("DIRECCION");
                String coordenada = resultado.getString("COORDENADAS");
                String actividad = resultado.getString("ACTIVIDAD");
                String descripcion = resultado.getString("DESCRIPCION");
                String informacion = resultado.getString("INFORMACION");
                lugar = new Lugares(idLugar, nombre, direccion, coordenada, actividad, descripcion, informacion);
            } else {
                throw new Exception("No se ha encontrado datos con el ID: " + idSitio);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en agregar getLugar");
        } finally {
            declaracion.close();
            conexion.close();
        }
        return lugar;
    }

    public void actualizar(Lugares lugarActualizado) throws SQLException {
        Connection conexion = null;
        PreparedStatement declaracion = null;
        try {
            conexion = origenDatos.getConnection();
            String instruccion = "UPDATE lugares SET NOMBRE=?, DIRECCION=?, COORDENADAS=?, ACTIVIDAD=?, DESCRIPCION=?, INFORMACION=? WHERE ID=?";
            declaracion = conexion.prepareStatement(instruccion);
            declaracion.setString(1, lugarActualizado.getNombre());
            declaracion.setString(2, lugarActualizado.getDireccion());
            declaracion.setString(3, lugarActualizado.getCoordenada());
            declaracion.setString(4, lugarActualizado.getActividad());
            declaracion.setString(5, lugarActualizado.getDescripcion());
            declaracion.setString(6, lugarActualizado.getInformacion());
            declaracion.setInt(7, lugarActualizado.getIdLugar());
            System.out.println(lugarActualizado.getIdLugar());
            declaracion.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar lugar");
        } finally {
            declaracion.close();
            conexion.close();
        }
    }

    public void borrarLugar(int idLugar) throws SQLException {
        Connection conexion = null;
        PreparedStatement declaracion = null;
        try {
            conexion = origenDatos.getConnection();
            String instruccion = "DELETE FROM lugares WHERE ID=?";
            declaracion = conexion.prepareStatement(instruccion);
            declaracion.setInt(1, idLugar);
            declaracion.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al borrar lugar");
        } finally {
            declaracion.close();
            conexion.close();
        }
    }

}
