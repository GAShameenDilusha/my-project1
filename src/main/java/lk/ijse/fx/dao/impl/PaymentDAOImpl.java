package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.PaymentDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.dto.PaymentDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
        public boolean save(PaymentDto dto) throws SQLException, ClassNotFoundException {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            return SQLUtil.execute("INSERT INTO payment (church_no, family_no, division_no, fee, date) VALUES (?, ?, ?, ?, ?)",
                    dto.getFamilyNo(),dto.getChurchNo(),dto.getFamilyNo(),dto.getDivisionNo(),dto.getFee(),sqlDate);
        }




        public List<PaymentDto> loadAll() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");

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


        public boolean update(PaymentDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE payment SET church_no = ?, division_no = ?, fee = ?, date = ? WHERE family_no = ?"
                    ,dto.getChurchNo(),dto.getDivisionNo(),dto.getFee(),dto.getDate(),dto.getFamilyNo());
        }



    public PaymentDto search(String familyNo) throws SQLException, ClassNotFoundException {
        PaymentDto paymentDto = null;

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE family_no=?", familyNo);
        if (resultSet.next()) {
            String payment_churchNo = resultSet.getString("church_no");
            String payment_familyNo = resultSet.getString("family_no");
            String payment_divisionNo = resultSet.getString("division_no");
            String payment_fee = resultSet.getString("fee");
            String payment_date = resultSet.getString("date");

            paymentDto = new PaymentDto(payment_churchNo, payment_familyNo, payment_divisionNo, payment_fee, payment_date);
        }

        return paymentDto;
    }


    public boolean delete(String familyNo) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM payment WHERE family_no = ?");

            }
        }







