package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.PaymentBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.PaymentDAO;
import lk.ijse.fx.dto.PaymentDto;
import lk.ijse.fx.entity.Payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean savePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getChurchNo(),dto.getFamilyNo(),dto.getDivisionNo(),dto.getFee(),dto.getDate()));
    }



    @Override
    public List<PaymentDto> loadAllPayment() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> payments=paymentDAO.loadAll();
        ArrayList<PaymentDto> paymentDtos=new ArrayList<>();
        for (Payment payment:payments) {
            paymentDtos.add(new PaymentDto(payment.getChurchNo(),payment.getFamilyNo(),payment.getDivisionNo(),payment.getFee(),payment.getDate()));
        }
        return paymentDtos;

    }



    @Override
    public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getChurchNo(),dto.getFamilyNo(),dto.getDivisionNo(),dto.getFee(),dto.getDate()));
    }



    @Override
    public PaymentDto searchPayment(String familyNo) throws SQLException, ClassNotFoundException {
        Payment payment=paymentDAO.search(familyNo);
        PaymentDto paymentDto=new PaymentDto(payment.getChurchNo(),payment.getFamilyNo(),payment.getDivisionNo(),payment.getFee(),payment.getDate());
        return paymentDto;

    }



    @Override
    public boolean deletePayment(String familyNo) throws SQLException, ClassNotFoundException {
       return paymentDAO.delete(familyNo);
    }
}
