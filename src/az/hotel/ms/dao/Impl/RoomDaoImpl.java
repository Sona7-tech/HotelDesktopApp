/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.RoomDao;
import az.hotel.ms.model.Cleaning;
import az.hotel.ms.model.Room;
import az.hotel.ms.model.RoomType;
import az.hotel.ms.model.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class RoomDaoImpl implements RoomDao {

    @Override
    public List<Room> getRoomList() throws Exception {

        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT R.ID,\n"
                + "          R.ROOM_NUMBER,\n"
                + "          T.ID AS T_ID,\n"
                + "          T.NAME,\n"
                + "          T.DESCRIPTION,\n"
                + "          R.COST,\n"
                + "          S.ID AS S_ID,\n"
                + "          S.STATUS_NAME,\n"
                + "          C.ID AS C_ID,\n"
                + "          C.NAME CLEANING_CONDITION\n"
                + "    FROM ROOM R\n"
                + "          INNER JOIN ROOM_TYPE T\n"
                + "            ON R.ROOMTYPE_ID = T.ID\n"
                + "          INNER JOIN STATUS S\n"
                + "           ON R.STATUS_ID = S.ID\n"
                + "          INNER JOIN CLEANING C\n"
                + "           ON R.CLEANING_ID = C.ID  \n"
                + "                 WHERE R.ACTIVE = 1\n"
                + "             ORDER BY R.ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {

                Room room = new Room();
                room.setID(rs.getLong("ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                room.setCost(rs.getInt("COST"));

                RoomType roomType = new RoomType();
                roomType.setRoomName(rs.getString("NAME"));
                roomType.setDesCription(rs.getString("DESCRIPTION"));

                Cleaning cleaning = new Cleaning();
                cleaning.setID(rs.getLong("C_ID"));
                cleaning.setCleanCondition(rs.getString("CLEANING_CONDITION"));

                Status status = new Status();
                status.setID(rs.getLong("S_ID"));
                status.setStatusName(rs.getString("STATUS_NAME"));

                room.setRoomType(roomType);
                room.setCleaning(cleaning);
                room.setStatus(status);
                roomList.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return roomList;
    }

    @Override
    public List<Room> searchRoomData(String keyword) throws Exception {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT R.ID,\n"
                + "          R.ROOM_NUMBER,\n"
                + "          T.ID AS T_ID,\n"
                + "          T.NAME,\n"
                + "          T.DESCRIPTION,\n"
                + "          R.COST,\n"
                + "          S.ID AS S_ID,\n"
                + "          S.STATUS_NAME,\n"
                + "          C.ID AS C_ID,\n"
                + "          C.NAME AS C_NAME\n"
                + "    FROM ROOM R\n"
                + "          INNER JOIN ROOM_TYPE T\n"
                + "            ON R.ROOMTYPE_ID = T.ID\n"
                + "          INNER JOIN STATUS S\n"
                + "           ON R.STATUS_ID = S.ID\n"
                + "          INNER JOIN CLEANING C\n"
                + "           ON R.CLEANING_ID = C.ID  \n"
                + "                 WHERE R.ACTIVE = 1\n"
                + "                 AND (LOWER(R.ROOM_NUMBER) LIKE LOWER(?) OR LOWER (T.NAME) LIKE LOWER(?)\n"
                + "                 OR LOWER (R.COST) LIKE LOWER (?) OR\n"
                + "                 LOWER (S.STATUS_NAME) LIKE LOWER(?) OR LOWER (C.NAME) LIKE LOWER(?)) \n"
                + "             ORDER BY R.ID";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                RoomType roomType = new RoomType();
                roomType.setID(rs.getLong("T_ID"));
                roomType.setRoomName(rs.getString("NAME"));
                roomType.setDesCription(rs.getString("DESCRIPTION"));

                Cleaning cleaning = new Cleaning();
                cleaning.setID(rs.getLong("C_ID"));
                cleaning.setCleanCondition(rs.getString("C_NAME"));
                
                Status status = new Status();
                status.setID(rs.getLong("S_ID"));
                status.setStatusName(rs.getString("STATUS_NAME"));
                
                Room room = new Room();
                room.setID(rs.getLong("ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                room.setCost(rs.getInt("COST"));
                
                room.setRoomType(roomType);
                room.setCleaning(cleaning);
                room.setStatus(status);
                roomList.add(room);

               
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return roomList;
    }

    @Override
    public Room getRoomById(Long roomId) throws Exception {
        Room room = new Room();
        String sql = "SELECT R.ID,\n"
                + "          R.ROOM_NUMBER,\n"
                + "          T.ID AS T_ID,\n"
                + "          T.NAME,\n"
                + "          T.DESCRIPTION,\n"
                + "          R.COST,\n"
                + "          S.ID AS S_ID,\n"
                + "          S.STATUS_NAME,\n"
                + "          C.ID AS C_ID,\n"
                + "          C.NAME CLEANING_CONDITION\n"
                + "    FROM ROOM R\n"
                + "          INNER JOIN ROOM_TYPE T\n"
                + "            ON R.ROOMTYPE_ID = T.ID\n"
                + "          INNER JOIN STATUS S\n"
                + "           ON R.STATUS_ID = S.ID\n"
                + "          INNER JOIN CLEANING C\n"
                + "           ON R.CLEANING_ID = C.ID  \n"
                + "                 WHERE R.ACTIVE = 1\n"
                + "             AND R.ID = ?";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, roomId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                
                room.setID(rs.getLong("ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                room.setCost(rs.getInt("COST"));

                RoomType roomType = new RoomType();
                roomType.setID(rs.getLong("T_ID"));
                roomType.setRoomName(rs.getString("NAME"));
                roomType.setDesCription(rs.getString("DESCRIPTION"));

                Cleaning cleaning = new Cleaning();
                cleaning.setID(rs.getLong("C_ID"));
                cleaning.setCleanCondition(rs.getString("CLEANING_CONDITION"));

                Status status = new Status();
                status.setID(rs.getLong("S_ID"));
                status.setStatusName(rs.getString("STATUS_NAME"));

                room.setRoomType(roomType);
                room.setCleaning(cleaning);
                room.setStatus(status);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return room;
        
    }

    @Override
    public void updateRoom(Room room) throws Exception {
        
          String sql = "UPDATE ROOM SET ROOMTYPE_ID = ?, ROOM_NUMBER = ?,  COST = ?, STATUS_ID = ?, CLEANING_ID = ? WHERE ID = ? ";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, room.getRoomType().getID());
            ps.setString(2,room.getRoomNumber());
            System.out.println("1 " + room.getRoomNumber());
            ps.setDouble(3, room.getCost());
            ps.setLong(4, room.getStatus().getID());
            ps.setLong(5, room.getCleaning().getID());
            ps.setLong(6, room.getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void addRoom(Room room) throws Exception {
      
        String sql = "INSERT INTO ROOM(ID, ROOMTYPE_ID, ROOM_NUMBER, COST,STATUS_ID,CLEANING_ID)\n"
                + "VALUES(ROOM_SEQ.NEXTVAL,?,?,?,?,?)";
        try(Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            
           ps.setLong(1, room.getRoomType().getID());
           ps.setString(2, room.getRoomNumber());
           ps.setDouble(3, room.getCost());
           ps.setLong(4, room.getStatus().getID());
           ps.setLong(5, room.getCleaning().getID());
           ps.execute();
           c.commit();
           
        }
    }

    @Override
    public void deleteRoom(Long roomId) throws Exception {
         String sql = "UPDATE ROOM SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, roomId);
            ps.execute();
            c.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
