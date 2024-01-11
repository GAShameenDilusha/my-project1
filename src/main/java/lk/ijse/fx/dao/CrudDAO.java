package lk.ijse.fx.dao;

import lk.ijse.fx.entity.Attendence;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T t) throws SQLException, ClassNotFoundException;
    List<T> loadAll() throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    T search(String familyNo) throws SQLException, ClassNotFoundException;
    boolean delete(String familyNo) throws SQLException, ClassNotFoundException;
}
