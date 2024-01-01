package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.custom.SignupDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.SignupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupDAOImpl implements SignupDAO {
        public static boolean registerUser(SignupDto signupDto) throws SQLException, ClassNotFoundException {
            Connection con = DbConnection.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO user (userName, password) VALUES (?, ?)");

            ps.setString(1, signupDto.getUserName());
            ps.setString(2, signupDto.getPassword());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }
    }


