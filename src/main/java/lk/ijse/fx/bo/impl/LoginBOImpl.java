package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.LoginBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.custom.LoginDAO;
import lk.ijse.fx.dto.LoginDto;
import lk.ijse.fx.entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    static LoginDAO loginDAO= (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);
    public static LoginDto searchUser(LoginDto loginDto) throws SQLException, ClassNotFoundException {
        Login login=loginDAO.search(loginDto);
        loginDto = new LoginDto(login.getUserName(), login.getPassword());
        return loginDto;

    }
}
