package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.FatherDAO;
import lk.ijse.fx.dto.FatherDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FatherDAOImpl implements FatherDAO {
        @Override
        public boolean save(FatherDto dto) throws SQLException, SQLException, ClassNotFoundException {
            return SQLUtil.execute("INSERT INTO father VALUES(?, ?, ?, ?, ?)",
                    dto.getChurchNo(),dto.getChurchFatherId(),dto.getName(),dto.getStartDate(),dto.getLeaveDate());
        }

        @Override
        public List<FatherDto> loadAll() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM father");

            List<FatherDto> fatherList = new ArrayList<>();
            while (resultSet.next()) {
                fatherList.add(new FatherDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)

                ));
            }
            return fatherList;
        }

        @Override
        public boolean update(FatherDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE father SET church_no = ?, name = ?, start_date = ?, leave_date = ? WHERE church_father_id = ?"
                    ,dto.getChurchNo(),dto.getName(),dto.getStartDate(),dto.getLeaveDate(),dto.getChurchFatherId());
        }







        @Override
        public FatherDto search(String churchFatherId) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM father WHERE church_father_id=?");

            FatherDto dto = null;

            if (resultSet.next()) {
                String father_churchNo = resultSet.getString(1);
                String father_churchFatherId = resultSet.getString(2);
                String father_name = resultSet.getString(3);
                String father_startDate = resultSet.getString(4);
                String father_leaveDate = resultSet.getString(5);

                dto = new FatherDto(father_churchNo, father_churchFatherId, father_name, father_startDate, father_leaveDate);
            }
            return dto;
        }



        @Override
        public boolean delete(String churchFatherId) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM father WHERE church_father_id = ?");
        }

}






