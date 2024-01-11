package lk.ijse.fx.dao.custom;

import lk.ijse.fx.dao.CrudDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AttendenceDAO extends CrudDAO<AttendenceDto> {

}


