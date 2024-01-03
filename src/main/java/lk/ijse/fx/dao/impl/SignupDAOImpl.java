package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.SignupDAO;
import lk.ijse.fx.dto.SignupDto;

import java.sql.SQLException;

public class SignupDAOImpl implements SignupDAO {
    public static boolean registerUser(SignupDto signupDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO user (userName, password) VALUES (?, ?)";
        return SQLUtil.execute(sql, signupDto.getUserName(), signupDto.getPassword());
    }
}
