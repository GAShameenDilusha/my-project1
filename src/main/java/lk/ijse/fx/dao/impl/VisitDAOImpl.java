package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.VisitDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VisitDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitDAOImpl implements VisitDAO {
        public boolean saveVisit(VisitDto dto) throws SQLException, ClassNotFoundException {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            return SQLUtil.execute("INSERT INTO visit VALUES(?, ?, ?, ?, ?)",
                    dto.getFamilyNo(),dto.getChurchFatherId(),sqlDate,dto.getTime(),dto.getDiscription());

        }










        public List<VisitDto> loadAllVisit() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendence");

            List<VisitDto> visitList = new ArrayList<>();

            while (resultSet.next()) {
                visitList.add(new VisitDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }


            return visitList;
        }





        public boolean updateVisit(VisitDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE visit SET church_father_id = ?, date = ?, time = ?, discription = ? WHERE family_no = ?"
                    ,dto.getChurchFatherId(),dto.getDate(),dto.getTime(),dto.getDiscription(),dto.getFamilyNo());
        }






        public VisitDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM visit WHERE family_no=?");

            VisitDto dto = null;

            if (resultSet.next()) {
                String visit_familyNo = resultSet.getString(1);
                String visit_churchFatherId = resultSet.getString(2);
                String visit_date = resultSet.getString(3);
                String vist_time = resultSet.getString(4);
                String visit_discription = resultSet.getString(5);

                dto = new VisitDto(visit_familyNo, visit_churchFatherId, visit_date, vist_time, visit_discription);
            }
            return dto;
        }



        public boolean deleteVisit(String familyNo) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM visit WHERE family_no = ?");
            }
        }







