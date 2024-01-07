package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.SignUpBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.SignupDAO;
import lk.ijse.fx.dto.SignupDto;
import lk.ijse.fx.entity.Signup;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    SignupDAO signupDAO= (SignupDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SIGNUP);
    public static boolean registerUser(SignupDto signupDto) throws SQLException, ClassNotFoundException {
       return signupDAO.save(new Signup(signupDto.getUserName(),signupDto.getPassword()));
    }
}
