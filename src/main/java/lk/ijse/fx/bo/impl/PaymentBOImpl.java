package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.PaymentBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.PaymentDAO;
import lk.ijse.fx.dto.PaymentDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment (church_no, family_no, division_no, fee, date) VALUES (?, ?, ?, ?, ?)";
        LocalDate currentDate = LocalDate.now();
        return SQLUtil.execute(sql, dto.getChurchNo(), dto.getFamilyNo(), dto.getDivisionNo(), dto.getFee(), currentDate);
    }

    @Override
    public List<PaymentDto> loadAllPayment() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            List<PaymentDto> paymentList = new ArrayList<>();

            while (resultSet.next()) {
                paymentList.add(new PaymentDto(
                        resultSet.getString("church_no"),
                        resultSet.getString("family_no"),
                        resultSet.getString("division_no"),
                        resultSet.getString("fee"),
                        resultSet.getString("date")
                ));
            }

            return paymentList;
        }
    }

    @Override
    public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE payment SET church_no = ?, division_no = ?, fee = ?, date = ? WHERE family_no = ?";
        return SQLUtil.execute(sql, dto.getChurchNo(), dto.getDivisionNo(), dto.getFee(), LocalDate.parse(dto.getDate()), dto.getFamilyNo());
    }

    @Override
    public PaymentDto searchPayment(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE family_no=?";
        ResultSet resultSet = SQLUtil.execute(sql, familyNo);

        if (resultSet.next()) {
            String payment_churchNo = resultSet.getString("church_no");
            String payment_familyNo = resultSet.getString("family_no");
            String payment_divisionNo = resultSet.getString("division_no");
            String payment_fee = resultSet.getString("fee");
            String payment_date = resultSet.getString("date");

            return new PaymentDto(payment_churchNo, payment_familyNo, payment_divisionNo, payment_fee, payment_date);
        }

        return null;
    }

    @Override
    public boolean deletePayment(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE family_no = ?";
        return SQLUtil.execute(sql, familyNo);
    }
}
