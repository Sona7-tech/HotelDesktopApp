package az.hotel.ms.model;

import java.util.Date;

public class Reservation extends CommonModel {

    private Room room;
    private Client client;
    private Date reserveDate;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    @Override
    public String toString() {
        return "Reservation{" + "room=" + room + ", client=" + client + ", reserveDate=" + reserveDate + '}';
    }
    
}
