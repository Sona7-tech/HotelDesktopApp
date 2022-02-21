/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.RoomTypeDao;
import az.hotel.ms.model.RoomType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class RoomTypeDaoImpl implements RoomTypeDao {

    @Override
    public List<RoomType> getRoomTypeList() throws Exception {

        List<RoomType> roomTypeList = new ArrayList<>();
        String sql = "SELECT ID, NAME, DESCRIPTION FROM ROOM_TYPE \n"
                + "WHERE ACTIVE = 1";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                RoomType r = new RoomType();
                r.setID(rs.getLong("ID"));
                r.setRoomName(rs.getString("NAME"));
                r.setDesCription(rs.getString("DESCRIPTION"));
                roomTypeList.add(r);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return roomTypeList;
    }
}
