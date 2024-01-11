package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.ChurchDAO;
import lk.ijse.fx.dto.ChurchDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChurchDAOImpl implements ChurchDAO {
    @Override
    public List<ChurchDto> getAllChurches() {
        List<ChurchDto> churchList = new ArrayList<>();

        // Using try-with-resources to ensure proper resource management
        try (PreparedStatement statement = SQLUtil.execute("SELECT * FROM church");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String churchNo = resultSet.getString("church_no");
                int a = resultSet.getInt("A");
                int b = resultSet.getInt("B");
                int c = resultSet.getInt("C");
                int d = resultSet.getInt("D");

                ChurchDto church = new ChurchDto(churchNo, a, b, c, d);
                churchList.add(church);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return churchList;
    }


    @Override
    public boolean save(ChurchDto churchDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ChurchDto> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ChurchDto churchDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ChurchDto search(String familyNo) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String familyNo) throws SQLException, ClassNotFoundException {
        return false;
    }
}