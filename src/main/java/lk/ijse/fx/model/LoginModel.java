package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.dto.SignupDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LoginModel {
    public static Optional<SignupDto> searchUser(LoginDto loginDto) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement("select * from user where userName= ? and password = ?");
        ps.setString(1, loginDto.getUserName());
        ps.setString(2, loginDto.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String userName = rs.getString(1);
            String password = rs.getString(2);
            return Optional.of(new SignupDto(userName, password));
        }
        return Optional.empty();
    }
}



