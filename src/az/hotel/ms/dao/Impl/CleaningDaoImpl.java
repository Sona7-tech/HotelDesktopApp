/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.CleaningDao;
import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.model.Cleaning;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class CleaningDaoImpl implements CleaningDao {

    @Override
    public List<Cleaning> getCleaningList() throws Exception {
       List<Cleaning> cleanIngList = new ArrayList<>();
       String sql = "SELECT ID, NAME FROM CLEANING \n"
                + "WHERE ACTIVE = 1";
       try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cleaning clean = new Cleaning();
                clean.setID(rs.getLong("ID"));
                clean.setCleanCondition(rs.getString("NAME"));
                cleanIngList.add(clean);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return cleanIngList;
    }
    
}
