package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.ChildrenDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.ChildrenDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChildrenDAOImpl implements ChildrenDAO {
        public boolean save(ChildrenDto dto) throws SQLException, ClassNotFoundException {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            return SQLUtil.execute("INSERT INTO children VALUES(?, ?, ?, ?, ?, ?)",
                    dto.getFamilyNo(),dto.getFamilyNo(),dto.getChildId(),dto.getChildName(),dto.getBirthday(),dto.getComplimentaryDate(),sqlDate);
        }






        public List<ChildrenDto> loadAll() throws SQLException, ClassNotFoundException {
            List<ChildrenDto> childrenList = new ArrayList<>();

            ResultSet resultSet = SQLUtil.execute("SELECT * FROM children");
            while (resultSet.next()) {
                childrenList.add(new ChildrenDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)

                ));
            }
            return childrenList;
        }






        public ChildrenDto search(String childId) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendence WHERE family_no=?");
            ChildrenDto dto = null;

            if (resultSet.next()) {
                String children_childId = resultSet.getString(1);
                String children_familyNo = resultSet.getString(2);
                String children_childName = resultSet.getString(3);
                String children_birthday = resultSet.getString(4);
                String children_complimentaryDate = resultSet.getString(5);
                String children_date = resultSet.getString(6);

                dto = new ChildrenDto(children_childId, children_familyNo, children_childName, children_birthday, children_complimentaryDate, children_date);
            }
            return dto;
        }








        public boolean update(ChildrenDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE children SET family_no = ?, child_name = ?, birthday = ?, complimentary_date = ?, date = ? WHERE child_id = ?"
                    ,dto.getFamilyNo(),dto.getChildName(),dto.getBirthday(),dto.getComplimentaryDate(),dto.getDate(),dto.getChildId());
        }







        public boolean delete(String childId) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM children WHERE child_id = ?");

        }
}







