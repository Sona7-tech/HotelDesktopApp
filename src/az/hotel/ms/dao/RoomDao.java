/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Room;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface RoomDao {
    
     List<Room> getRoomList() throws Exception;
     List<Room> searchRoomData(String keyword) throws Exception;
     Room getRoomById (Long roomId) throws Exception;
     void updateRoom(Room room) throws Exception;
     void addRoom(Room room) throws Exception;

     void deleteRoom(Long roomId) throws Exception;
     
}
