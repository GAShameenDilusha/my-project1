package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.SignupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SignupDAO {
        public static boolean registerUser(SignupDto signupDto) throws SQLException, ClassNotFoundException {
                return false;
        }

}
