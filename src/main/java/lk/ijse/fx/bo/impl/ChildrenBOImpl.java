package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.ChildrenBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.ChildrenDAO;
import lk.ijse.fx.dto.ChildrenDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChildrenBOImpl implements ChildrenBO {
    private ChildrenDAO childrenDAO = (ChildrenDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CHILDREN);

    @Override
    public boolean saveChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO children VALUES(?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getFamilyNo(), dto.getChildId(), dto.getChildName(), dto.getBirthday(), dto.getComplimentaryDate(), dto.getDate());
    }

    @Override
    public List<ChildrenDto> loadAllChildren() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM children";
        List<ChildrenDto> childrenList = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
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
        }

        return childrenList;
    }

    @Override
    public ChildrenDto searchChildren(String childId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM children WHERE child_id=?";
        ResultSet resultSet = SQLUtil.execute(sql, childId);

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

    @Override
    public boolean updateChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE children SET family_no = ?, child_name = ?, birthday = ?, complimentary_date = ?, date = ? WHERE child_id = ?";
        return SQLUtil.execute(sql, dto.getFamilyNo(), dto.getChildName(), dto.getBirthday(), dto.getComplimentaryDate(), dto.getDate(), dto.getChildId());
    }

    @Override
    public boolean deleteChildren(String childId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM children WHERE child_id = ?";
        return SQLUtil.execute(sql, childId);
    }
}
