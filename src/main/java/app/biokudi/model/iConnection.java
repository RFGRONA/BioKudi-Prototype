package app.biokudi.model;

import java.sql.SQLException;
import java.util.List;

public interface iConnection<T extends Places> {
    
    // Model methods for handling data inheriting from Places
    
    List<T> getListPlaces() throws SQLException;
    void addPlace(T object) throws SQLException;
    T getPlace(int id) throws SQLException;
    void updatePlace(T object) throws SQLException;
    void deletePlace(int id) throws SQLException;
}