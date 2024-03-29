package lk.ijse.fx.dao.custom;

import lk.ijse.fx.dao.CrudDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VisitDto;
import lk.ijse.fx.entity.Visit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VisitDAO extends CrudDAO<VisitDto> {


}
