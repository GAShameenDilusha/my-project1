package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.dto.SignupDto;
import lk.ijse.fx.entity.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface LoginDAO {
    static Optional<SignupDto> searchUser(LoginDto loginDto) throws SQLException, ClassNotFoundException {
        return null;
    }


    Login search(LoginDto loginDto);
}





