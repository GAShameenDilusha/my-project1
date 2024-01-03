package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.PaymentDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO {
        public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
        public List<PaymentDto> loadAllPayment() throws SQLException, ClassNotFoundException;
        public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
        public PaymentDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException;
        public boolean deletePayment(String familyNo) throws SQLException, ClassNotFoundException;
}
