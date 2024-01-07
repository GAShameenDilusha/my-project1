package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.ChildrenBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.ChildrenDAO;
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.entity.Children;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChildrenBOImpl implements ChildrenBO {
    ChildrenDAO childrenDAO = (ChildrenDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CHILDREN);
    @Override
    public boolean saveChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException {
        return ChildrenDAO.save(new Children(dto.getFamilyNo(),dto.getChildId(),dto.getChildName(),dto.getBirthday(),dto.getComplimentaryDate(),dto.getDate()));
    }




    @Override
    public List<ChildrenDto> loadAllChildren() throws SQLException, ClassNotFoundException {
        ArrayList<Children> childrens=childrenDAO.loadAll();
        ArrayList<ChildrenDto> childrenDtos=new ArrayList<>();
        for (Children children:childrens) {
            childrenDtos.add(new ChildrenDto(children.getFamilyNo(),children.getChildId(),children.getChildName(),children.getBirthday(),children.getComplimentaryDate(),children.getDate()));
        }
        return childrenDtos;

    }




    @Override
    public ChildrenDto searchChildren(String childId) throws SQLException, ClassNotFoundException {
        Children children=childrenDAO.search(childId);
        ChildrenDto childrenDto=new ChildrenDto(children.getFamilyNo(),children.getChildId(),children.getChildName(),children.getBirthday(),children.getComplimentaryDate(),children.getDate());
        return childrenDto;
    }






    @Override
    public boolean updateChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException {
        return childrenDAO.update(new Children(dto.getFamilyNo(),dto.getChildId(),dto.getChildName(),dto.getBirthday(),dto.getComplimentaryDate(),dto.getDate()));
    }





    @Override
    public boolean deleteChildren(String childId) throws SQLException, ClassNotFoundException {
        return childrenDAO.delete(childId);
    }
}
