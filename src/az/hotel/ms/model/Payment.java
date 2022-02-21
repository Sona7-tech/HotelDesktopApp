package az.hotel.ms.model;

public class Payment extends CommonModel {
    
    private long cardNumber;
    private Double salePercent;
    private Double totalAmount;
    private PaymentOpt paymentOpt;
    private Client client;
    private Room room;
    private Booking booking;

    
    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(Double salePercent) {
        this.salePercent = salePercent;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentOpt getPaymentOpt() {
        return paymentOpt;
    }

    public void setPaymentOpt(PaymentOpt paymentOpt) {
        this.paymentOpt = paymentOpt;
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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    @Override
    public String toString() {
        return "Payment{" + "cardNumber=" + cardNumber + ", salePercent=" + salePercent + ", totalAmount=" + totalAmount + ", paymentOpt=" + paymentOpt + ", client=" + client + ", room=" + room + ", booking=" + booking + '}';
    }

   

}
