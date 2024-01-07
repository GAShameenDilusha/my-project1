package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.VisitBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.VisitDAO;
import lk.ijse.fx.dto.VisitDto;
import lk.ijse.fx.entity.Visit;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitBOImpl implements VisitBO {
    VisitDAO visitDAO= (VisitDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VISIT);

    @Override
    public boolean saveVisit(VisitDto dto) throws SQLException, ClassNotFoundException {
        return visitDAO.save(new Visit(dto.getFamilyNo(),dto.getChurchFatherId(),dto.getDate(),dto.getTime(),dto.getDiscription()));
    }







    @Override
    public List<VisitDto> loadAllVisit() throws SQLException, ClassNotFoundException {
        ArrayList<Visit> visits=visitDAO.loadAll();
        ArrayList<VisitDto> visitDtos=new ArrayList<>();
        for (Visit visit:visits) {
            visitDtos.add(new VisitDto(visit.getFamilyNo(),visit.getChurchFatherId(),visit.getDate(),visit.getTime(),visit.getDiscription()));
        }
        return visitDtos;

    }





    @Override
    public boolean updateVisit(VisitDto dto) throws SQLException, ClassNotFoundException {
        return visitDAO.update(new Visit(dto.getFamilyNo(),dto.getChurchFatherId(),dto.getDate(),dto.getTime(),dto.getDiscription()));

    }





    @Override
    public VisitDto searchVisit(String familyNo) throws SQLException, ClassNotFoundException {
        Visit visit=visitDAO.search(familyNo);
        VisitDto visitDto=new VisitDto(visit.getFamilyNo(),visit.getChurchFatherId(),visit.getDate(),visit.getTime(),visit.getDiscription());
        return visitDto;
    }




    @Override
    public boolean deleteVisit(String familyNo) throws SQLException, ClassNotFoundException {
       return visitDAO.delete(familyNo)
    }
}
