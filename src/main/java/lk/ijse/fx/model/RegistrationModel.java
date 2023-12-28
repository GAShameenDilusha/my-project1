package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.RegistrationDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public class RegistrationModel {

    public List<RegistrationDto> loadAllRegistration() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM registration";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<RegistrationDto> registrationList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            registrationList.add(new RegistrationDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            ));
        }

        return registrationList;
    }

    public RegistrationDto searchCustomer(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM registration WHERE family_no=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, familyNo);

        ResultSet resultSet = pstm.executeQuery();

        RegistrationDto dto = null;

        if (resultSet.next()) {
            String registration_churchNo = resultSet.getString(1);
            String registration_divisionNo = resultSet.getString(2);
            String registration_familyNo = resultSet.getString(3);
            String registration_fatherId = resultSet.getString(4);
            String registration_motherId = resultSet.getString(5);
            String registration_fatherName = resultSet.getString(6);
            String registration_motherName = resultSet.getString(7);
            String registration_address = resultSet.getString(8);
            String registration_tel = resultSet.getString(9);
            String registration_date = resultSet.getString(10);

            dto = new RegistrationDto(registration_churchNo, registration_divisionNo, registration_familyNo, registration_fatherId, registration_motherId, registration_fatherName, registration_motherName, registration_address, registration_tel, registration_date);
        }
        return dto;
    }


    public boolean updateRegistration(RegistrationDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE registration SET church_no = ?, division_no = ?, father_id = ?, mother_id = ?, father_name = ?, mother_name = ?, address = ?, tel = ?, date = ? WHERE family_no = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getChurchNo());
        pstm.setString(2, dto.getDivisionNo());
        pstm.setString(3, dto.getFatherId());
        pstm.setString(4, dto.getMotherId());
        pstm.setString(5, dto.getFatherName());
        pstm.setString(6, dto.getMotherName());
        pstm.setString(7, dto.getAddress());
        pstm.setString(8, dto.getTel());
        pstm.setDate(9, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
        pstm.setString(10, dto.getFamilyNo());

        return pstm.executeUpdate() > 0;
    }


    public boolean deleteRegistration(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String selectSql = "SELECT division_no FROM registration WHERE family_no = ?";
        String deleteSql = "DELETE FROM registration WHERE family_no = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

            // Retrieve divisionNo before deletion
            selectStatement.setString(1, familyNo);
            ResultSet resultSet = selectStatement.executeQuery();

            String divisionNo = null;
            if (resultSet.next()) {
                divisionNo = resultSet.getString("division_no");
            }

            // Start transaction
            connection.setAutoCommit(false);

            try {
                // Delete the registration record
                deleteStatement.setString(1, familyNo);
                boolean isDeleted = deleteStatement.executeUpdate() > 0;

                // Update church table to increment the division count
                if (isDeleted && divisionNo != null) {
                    incrementDivisionCount(connection, divisionNo);
                }

                // Commit transaction
                connection.commit();

                return isDeleted;
            } catch (SQLException e) {
                // Rollback transaction in case of exception
                connection.rollback();
                throw e;
            }
        }
    }

    private void incrementDivisionCount(Connection connection, String divisionNo) throws SQLException {
        String updateChurchSql = "UPDATE church SET " +
                "A = A + CASE WHEN ? = 'A' THEN 1 ELSE 0 END, " +
                "B = B + CASE WHEN ? = 'B' THEN 1 ELSE 0 END, " +
                "C = C + CASE WHEN ? = 'C' THEN 1 ELSE 0 END, " +
                "D = D + CASE WHEN ? = 'D' THEN 1 ELSE 0 END " +
                "WHERE church_no = ?";

        try (PreparedStatement updateChurchStatement = connection.prepareStatement(updateChurchSql)) {
            for (int i = 1; i <= 5; i++) {
                updateChurchStatement.setString(i, divisionNo);
            }
            updateChurchStatement.setString(5, getChurchNoForDivision(divisionNo));

            updateChurchStatement.executeUpdate();
        }
    }


    private String getChurchNoForDivision(String divisionNo) {
        // You need to implement a method to map divisionNo to churchNo based on your application's logic.
        // This is a placeholder method and should be replaced with the actual implementation.
        // The mapping logic depends on your specific requirements and database structure.
        // For example, you might have a separate table that maps divisions to churches.
        // Replace the implementation accordingly.
        // This is just an example and may not fit your actual use case.
        return "CHURCH_A";
    }




    public boolean saveRegistration(RegistrationDto dto) throws SQLException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement registrationStatement = null;
        PreparedStatement updateChurchStatement = null;
        boolean isSaved = false;

        try {
            // Start transaction
            connection.setAutoCommit(false);

            // Save registration details
            String registrationSql = "INSERT INTO registration VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            registrationStatement = connection.prepareStatement(registrationSql);

            registrationStatement.setString(1, dto.getChurchNo());
            registrationStatement.setString(2, dto.getDivisionNo());
            registrationStatement.setString(3, dto.getFamilyNo());
            registrationStatement.setString(4, dto.getFatherId());
            registrationStatement.setString(5, dto.getMotherId());
            registrationStatement.setString(6, dto.getFatherName());
            registrationStatement.setString(7, dto.getMotherName());
            registrationStatement.setString(8, dto.getAddress());
            registrationStatement.setString(9, dto.getTel());
            registrationStatement.setDate(10, sqlDate);  // Use setDate to set the date


            isSaved = registrationStatement.executeUpdate() > 0;

            // Update church table to decrement the division count
            String updateChurchSql = "UPDATE church SET " +
                    "A = A - CASE WHEN ? = 'A' THEN 1 ELSE 0 END, " +
                    "B = B - CASE WHEN ? = 'B' THEN 1 ELSE 0 END, " +
                    "C = C - CASE WHEN ? = 'C' THEN 1 ELSE 0 END, " +
                    "D = D - CASE WHEN ? = 'D' THEN 1 ELSE 0 END " +
                    "WHERE church_no = ?";

            updateChurchStatement = connection.prepareStatement(updateChurchSql);

            updateChurchStatement.setString(1, dto.getDivisionNo());
            updateChurchStatement.setString(2, dto.getDivisionNo());
            updateChurchStatement.setString(3, dto.getDivisionNo());
            updateChurchStatement.setString(4, dto.getDivisionNo());
            updateChurchStatement.setString(5, dto.getChurchNo());

            updateChurchStatement.executeUpdate();

            // Commit transaction
            connection.commit();
        } catch (SQLException e) {
            // Rollback transaction in case of exception
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            // Set auto-commit back to true and close statements
            if (connection != null) {
                connection.setAutoCommit(true);
            }
            if (registrationStatement != null) {
                registrationStatement.close();
            }
            if (updateChurchStatement != null) {
                updateChurchStatement.close();
            }
        }

        return isSaved;
    }


    public int getNextFamilyNo() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT MAX(CAST(family_no AS SIGNED)) FROM registration";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        int maxFamilyNo = 0;
        if(resultSet.next()){
            maxFamilyNo = resultSet.getInt(1);
        }

        return maxFamilyNo + 1;
    }


}



