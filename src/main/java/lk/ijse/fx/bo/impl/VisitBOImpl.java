package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.VisitBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.VisitDAO;
import lk.ijse.fx.dto.VisitDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitBOImpl implements VisitBO {
    private VisitDAO visitDAO = (VisitDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISIT);

    @Override
    public boolean saveVisit(VisitDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO visit VALUES(?, ?, ?, ?, ?)";
        LocalDate currentDate = LocalDate.now();
        return SQLUtil.execute(sql, dto.getFamilyNo(), dto.getChurchFatherId(), currentDate, dto.getTime(), dto.getDiscription());
    }

    @Override
    public List<VisitDto> loadAllVisit() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM visit";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
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
    }

    @Override
    public boolean updateVisit(VisitDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE visit SET church_father_id = ?, date = ?, time = ?, discription = ? WHERE family_no = ?";
        return SQLUtil.execute(sql, dto.getChurchFatherId(), LocalDate.parse(dto.getDate()), dto.getTime(), dto.getDiscription(), dto.getFamilyNo());
    }

    @Override
    public VisitDto searchVisit(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM visit WHERE family_no=?";
        ResultSet resultSet = SQLUtil.execute(sql, familyNo);

        if (resultSet.next()) {
            String visit_familyNo = resultSet.getString(1);
            String visit_churchFatherId = resultSet.getString(2);
            String visit_date = resultSet.getString(3);
            String vist_time = resultSet.getString(4);
            String visit_discription = resultSet.getString(5);

            return new VisitDto(visit_familyNo, visit_churchFatherId, visit_date, vist_time, visit_discription);
        }

        return null;
    }

    @Override
    public boolean deleteVisit(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM visit WHERE family_no = ?";
        return SQLUtil.execute(sql, familyNo);
    }
}
