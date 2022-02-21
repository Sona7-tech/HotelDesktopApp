/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.StatusDao;
import az.hotel.ms.model.Cleaning;
import az.hotel.ms.model.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class StatusDaoImpl implements StatusDao {

    @Override
    public List<Status> getStatusList() throws Exception {
        
        List<Status> statusList = new ArrayList<>();
       String sql = "SELECT S.ID,\n" +
"          S.STATUS_NAME\n" +
"     FROM STATUS S\n" +
"             WHERE S.ACTIVE = 1\n" +
"             ORDER BY S.ID";
       try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Status st = new Status();
                st.setID(rs.getLong("ID"));
                st.setStatusName(rs.getString("STATUS_NAME")); 
                statusList.add(st);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return statusList;
    }
    
    
    
}
