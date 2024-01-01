package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.PaymentDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO {
        public boolean savePayment(PaymentDto dto) throws SQLException;
        public List<PaymentDto> loadAllPayment() throws SQLException;
        public boolean updatePayment(PaymentDto dto) throws SQLException;
        public PaymentDto searchCustomer(String familyNo) throws SQLException;
        public boolean deletePayment(String familyNo) throws SQLException;
}
