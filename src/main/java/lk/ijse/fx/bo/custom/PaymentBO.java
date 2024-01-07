package lk.ijse.fx.bo.custom;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.PaymentDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO {
     boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
     List<PaymentDto> loadAllPayment() throws SQLException, ClassNotFoundException;
     boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
     PaymentDto searchPayment(String familyNo) throws SQLException, ClassNotFoundException;
     boolean deletePayment(String familyNo) throws SQLException, ClassNotFoundException;
}
