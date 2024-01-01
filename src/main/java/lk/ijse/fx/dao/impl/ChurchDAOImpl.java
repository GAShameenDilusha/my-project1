package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.custom.ChurchDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.ChurchDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChurchDAOImpl implements ChurchDAO {
        public List<ChurchDto> getAllChurches() {
            List<ChurchDto> churchList = new ArrayList<>();
            Connection connection = null;

            try {
                connection = DbConnection.getInstance().getConnection();
                String sql = "SELECT * FROM church";

                try (PreparedStatement statement = connection.prepareStatement(sql);
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
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Note: Do not close the connection here; let the calling code manage it.
            }

            return churchList;
        }

    }



