package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.LoginDAO;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.dto.SignupDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LoginDAOImpl implements LoginDAO {
    public static Optional<SignupDto> searchUser(LoginDto loginDto) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE userName=? AND password=?";
        ResultSet rs = SQLUtil.execute(sql, loginDto.getUserName(), loginDto.getPassword());

        if (rs.next()) {
            String userName = rs.getString(1);
            String password = rs.getString(2);
            return Optional.of(new SignupDto(userName, password));
        }

        return Optional.empty();
    }
}
