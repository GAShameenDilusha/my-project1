package lk.ijse.fx.bo.custom;

import lk.ijse.fx.bo.SuperBO;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.dto.SignupDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface LoginBO extends SuperBO {
     static Optional<SignupDto> searchUser(LoginDto loginDto) throws SQLException, ClassNotFoundException {
          return null;
     }

}
