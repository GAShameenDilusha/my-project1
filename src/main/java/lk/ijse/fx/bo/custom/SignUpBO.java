package lk.ijse.fx.bo.custom;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.SignupDto;

import java.sql.SQLException;

public interface SignUpBO {
     static boolean registerUser(SignupDto signupDto) throws SQLException, ClassNotFoundException;

}
