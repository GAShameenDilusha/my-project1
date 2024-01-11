package lk.ijse.fx.dao.custom;

import lk.ijse.fx.dao.CrudDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.PaymentDto;
import lk.ijse.fx.entity.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<PaymentDto> {



}
