package az.hotel.ms.model;

public class Orders extends CommonModel {
    
    private Menu menu;
    private double cost;
    private Client client;
    private Room room;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Orders{" + "menu=" + menu + ", cost=" + cost + ", client=" + client + ", room=" + room + '}';
    }
    

}
