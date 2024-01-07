package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.LoginBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.LoginDAO;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.dto.SignupDto;
import lk.ijse.fx.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO= (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    public static Optional<SignupDto> searchUser(LoginDto loginDto) throws SQLException, ClassNotFoundException {
        Login login=loginDAO.search(loginDto);
        LoginDto loginDto=new LoginDto(login.getUserName(),login.getPassword());
        return loginDto;

    }
}
