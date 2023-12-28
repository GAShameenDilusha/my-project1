package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.PaymentDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {
    public boolean savePayment(PaymentDto dto) throws SQLException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO payment (church_no, family_no, division_no, fee, date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getChurchNo());
            pstm.setString(2, dto.getFamilyNo());
            pstm.setString(3, dto.getDivisionNo());
            pstm.setString(4, dto.getFee());
            pstm.setDate(5, sqlDate);  // Use setDate to set the date

            return pstm.executeUpdate() > 0;
        }
    }

    public List<PaymentDto> loadAllPayment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM payment";

        try (PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

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

    public boolean updatePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE payment SET church_no = ?, division_no = ?, fee = ?, date = ? WHERE family_no = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, dto.getChurchNo());
            pstm.setString(2, dto.getDivisionNo());
            pstm.setString(3, dto.getFee());
            pstm.setDate(4, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
            pstm.setString(5, dto.getFamilyNo());

            return pstm.executeUpdate() > 0;
        }
    }



    public PaymentDto searchCustomer(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM payment WHERE family_no=?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, familyNo);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    String payment_churchNo = resultSet.getString("church_no");
                    String payment_familyNo = resultSet.getString("family_no");
                    String payment_divisionNo = resultSet.getString("division_no");
                    String payment_fee = resultSet.getString("fee");
                    String payment_date = resultSet.getString("date");

                    return new PaymentDto(payment_churchNo, payment_familyNo, payment_divisionNo, payment_fee, payment_date);
                }
            }
        }
        return null;
    }


    public boolean deletePayment(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM payment WHERE family_no = ?";


        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, familyNo);
            return pstm.executeUpdate() > 0;
        }
    }




}
