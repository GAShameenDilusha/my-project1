package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.AttendenceBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendenceBOImpl implements AttendenceBO {
    AttendenceDAO attendenceDAO= (AttendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDENCE);

    @Override
    public boolean saveAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException {
      return attendenceDAO.save(new Attendence(dto.getFamilyNo(),dto.getPurpose(),dto.getArrangedTime(),dto.getLeaveTime(),dto.getDate()));
    }





    @Override
    public List<AttendenceDto> loadAllAttendence() throws SQLException, ClassNotFoundException {
        ArrayList<Attendence> attendences=attendenceDAO.loadAll();
        ArrayList<AttendenceDto> attendenceDtos=new ArrayList<>();
        for (Attendence attendence:attendences) {
            attendenceDtos.add(new AttendenceDto(attendence.getFamilyNo(),attendence.getPurpose(),attendence.getArrangedTime(),attendence.getLeaveTime(),attendence.getDate()));
        }
        return attendenceDtos;

    }




    @Override
    public boolean updateAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException {
        return AttendenceDAO.update(new Attendence(dto.getFamilyNo(),dto.getPurpose(),dto.getArrangedTime(),dto.getLeaveTime(),dto.getDate()));

    }





    @Override
    public AttendenceDto searchAttendence(String familyNo) throws SQLException, ClassNotFoundException {
        Attendence attendence=attendenceDAO.search(familyNo);
        AttendenceDto attendenceDto=new AttendenceDto(attendence.getFamilyNo(),attendence.getPurpose(),attendence.getArrangedTime(),attendence.getLeaveTime(),attendence.getDate());
        return attendenceDto;
    }




    @Override
    public boolean deleteAttendence(String familyNo) throws SQLException, ClassNotFoundException {
        return attendenceDAO.delete(familyNo);
    }
}
