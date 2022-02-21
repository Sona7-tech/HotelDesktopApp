/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.PositionsDao;
import az.hotel.ms.model.Positions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PositionsDaoImpl implements PositionsDao {

    @Override
    public List<Positions> getPositionsList() throws Exception {
        
        List<Positions> positionsList = new ArrayList<>();
       String sql = "SELECT ID, POSITION_NAME FROM POSITIONS\r\n"
               + "WHERE ACTIVE =1";
       try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql);
               ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                Positions positions = new Positions();
                positions.setID(rs.getLong("ID"));
                positions.setPositionName(rs.getString("POSITION_NAME"));
                positionsList.add(positions);
                
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return positionsList;
    }
    
}
