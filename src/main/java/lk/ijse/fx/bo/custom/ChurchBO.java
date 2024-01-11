package lk.ijse.fx.bo.custom;

import lk.ijse.fx.bo.SuperBO;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.ChurchDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ChurchBO extends SuperBO {
      List<ChurchDto> getAllChurches();
}
