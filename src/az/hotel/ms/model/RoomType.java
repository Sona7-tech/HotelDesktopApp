/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.model;

/**
 *
 * @author Lenovo
 */
public class RoomType extends CommonModel {
    
    private String roomName;
    private String desCription;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDesCription() {
        return desCription;
    }

    public void setDesCription(String desCription) {
        this.desCription = desCription;
    }
    @Override
    public String toString() {
        return roomName + "," + desCription;
    }
    
}
