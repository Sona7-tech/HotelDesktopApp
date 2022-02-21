/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.PaymentDao;
import az.hotel.ms.model.Booking;
import az.hotel.ms.model.Client;
import az.hotel.ms.model.Payment;
import az.hotel.ms.model.PaymentOpt;
import az.hotel.ms.model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class PaymentDaoImpl implements PaymentDao {

    @Override
    public List<Payment> getPaymentList() throws Exception {

        List<Payment> paymentList = new ArrayList<>();
        String sql = "SELECT P.ID P_ID,\n"
                + "                          C.ID C_ID,\n"
                + "                         B.ID B_ID,\n"
                + "                          C.NAME C_NAME,\n"
                + "                         C.SURNAME C_SURNAME,\n"
                + "                          P.CARD_NUMBER,\n"
                + "                         P.TOTAL_AMOUNT,\n"
                + "                         O.ID O_ID,\n"
                + "                          O.NAME PAYMENT_OPTION,\n"
                + "                          R.ID R_ID,\n"
                + "                          R.ROOM_NUMBER\n"
                + "                    FROM PAYMENT P\n"
                + "                          INNER JOIN PAYMENTOPT O\n"
                + "                          ON P.OPTION_ID = O.ID\n"
                + "                          INNER JOIN BOOKING B\n"
                + "                          ON P.BOOKING_ID = B.ID\n"
                + "                          INNER JOIN CLIENT C\n"
                + "                           ON B.CUSTOMER_ID = C.ID\n"
                + "                          INNER JOIN ROOM R\n"
                + "                           ON B.ROOM_ID =  R.ID\n"
                + "                                 WHERE P.ACTIVE = 1 AND B.ACTIVE = 1\n"
                + "                             ORDER BY P_ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {

                Client client = new Client();
                client.setID(rs.getLong("C_ID"));
                client.setName(rs.getString("C_NAME"));
                client.setSurname(rs.getString("C_SURNAME"));

                PaymentOpt paymentOpt = new PaymentOpt();
                paymentOpt.setID(rs.getLong("O_ID"));
                paymentOpt.setPaymentName(rs.getString("PAYMENT_OPTION"));

                Room room = new Room();
                room.setID(rs.getLong("R_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));

                Booking booking = new Booking();
                booking.setID(rs.getLong("B_ID"));
                booking.setClient(client);
                booking.setRoom(room);
                Payment payment = new Payment();
                payment.setID(rs.getLong("P_ID"));
                payment.setCardNumber(rs.getLong("CARD_NUMBER"));
                payment.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
                payment.setPaymentOpt(paymentOpt);
                payment.setBooking(booking);
                paymentList.add(payment);
            }
        }
        return paymentList;
    }

    @Override
    public List<Payment> searchPaymentData(String keyword) throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        String sql = "SELECT P.ID,\n"
                + "                          C.ID C_ID,\n"
                + "                          C.NAME,\n"
                + "                          C.SURNAME,\n"
                + "                          P.CARD_NUMBER,\n"
                + "                          P.TOTAL_AMOUNT,\n"
                + "                          O.ID O_ID,\n"
                + "                          O.NAME PAYMENT_OPTION,\n"
                + "                          B.ID B_ID,\n"
                + "                          R.ID R_ID,\n"
                + "                          R.ROOM_NUMBER\n"
                + "                    FROM PAYMENT P\n"
                + "                          INNER JOIN PAYMENTOPT O\n"
                + "                          ON P.OPTION_ID = O.ID\n"
                + "                          INNER JOIN BOOKING B\n"
                + "                          ON P.BOOKING_ID = B.ID\n"
                + "                          INNER JOIN CLIENT C\n"
                + "                           ON B.CUSTOMER_ID = C.ID\n"
                + "                          INNER JOIN ROOM R\n"
                + "                           ON B.ROOM_ID =  R.ID  \n"
                + "                                 WHERE P.ACTIVE = 1\n"
                + "                                 AND ( LOWER(C.NAME) LIKE LOWER(?) OR LOWER(C.SURNAME) LIKE LOWER(?) OR LOWER(P.CARD_NUMBER) LIKE LOWER(?) OR LOWER(C.NATIONALITY) LIKE LOWER(?)\n"
                + "                                 OR LOWER(P.TOTAL_AMOUNT) LIKE LOWER(?) OR LOWER(O.NAME) LIKE LOWER(?)\n"
                + "                                 OR LOWER(R.ROOM_NUMBER) LIKE LOWER(?))\n"
                + "                             ORDER BY P.ID";

        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");
            ps.setString(6, "%" + keyword + "%");
            ps.setString(7, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Client client = new Client();
                client.setID(rs.getLong("C_ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                //client.setNationality(rs.getString("NATIONALITY"));

                Room room = new Room();
                room.setID(rs.getLong("R_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));

                Booking booking = new Booking();
                booking.setID(rs.getLong("B_ID"));
                booking.setClient(client);
                booking.setRoom(room);
                PaymentOpt paymentOpt = new PaymentOpt();
                paymentOpt.setID(rs.getLong("O_ID"));
                paymentOpt.setPaymentName(rs.getString("PAYMENT_OPTION"));

                Payment payment = new Payment();
                payment.setID(rs.getLong("ID"));
                payment.setCardNumber(rs.getLong("CARD_NUMBER"));
                payment.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
                payment.setBooking(booking);
                payment.setPaymentOpt(paymentOpt);
                
                paymentList.add(payment);
                
            }
        } 
        return paymentList;
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        Payment payment = new Payment();
        String sql = "SELECT P.ID P_ID,\n" +
"                          B.ID B_ID,\n" +
"                          C.ID C_ID,\n" +
"                          C.NAME,\n" +
"                          C.SURNAME,\n" +
"                          P.CARD_NUMBER,\n" +
"                          P.TOTAL_AMOUNT,\n" +
"                          O.ID O_ID,\n" +
"                          O.NAME PAYMENT_OPTION,\n" +
"                          R.ID R_ID,\n" +
"                          R.ROOM_NUMBER\n" +
"                    FROM PAYMENT P\n" +
"                          INNER JOIN PAYMENTOPT O\n" +
"                          ON P.OPTION_ID = O.ID\n" +
"                          INNER JOIN BOOKING B\n" +
"                         ON P.BOOKING_ID = B.ID\n" +
"                          INNER JOIN CLIENT C\n" +
"                           ON B.CUSTOMER_ID = C.ID\n" +
"                          INNER JOIN ROOM R\n" +
"                           ON B.ROOM_ID =  R.ID  \n" +
"                                 WHERE P.ACTIVE = 1\n" +
"                             AND  P.ID = ?";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, paymentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payment.setID(rs.getLong("P_ID"));
                payment.setCardNumber(rs.getLong("CARD_NUMBER"));
                payment.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));

                Client client = new Client();
                client.setID(rs.getLong("C_ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
               // client.setNationality(rs.getString("NATIONALITY"));

                
                PaymentOpt paymentOpt = new PaymentOpt();
                paymentOpt.setID(rs.getLong("O_ID"));
                paymentOpt.setPaymentName(rs.getString("PAYMENT_OPTION"));

                Room room = new Room();
                room.setID(rs.getLong("R_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                
                Booking booking = new Booking();
                booking.setID(rs.getLong("B_ID"));
                booking.setClient(client);
                booking.setRoom(room);
                payment.setPaymentOpt(paymentOpt);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return payment;
    }

    @Override
    public void updatePayment(Payment payment) throws Exception {

        String sql = "UPDATE PAYMENT SET CARD_NUMBER = ?, OPTION_ID = ?, TOTAL_AMOUNT = ?\n"
                + "WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, payment.getCardNumber());
            ps.setLong(2, payment.getPaymentOpt().getID());
            ps.setDouble(3, payment.getTotalAmount());
            ps.setLong(4, payment.getID());
            ps.execute();
            c.commit();

        }
    }

    @Override
    public void addPayment(Payment payment) throws Exception {
        String sql = "INSERT INTO PAYMENT(ID, CARD_NUMBER, BOOKING_ID, TOTAL_AMOUNT, OPTION_ID)\n"
                + "VALUES(PAYMENT_SEQ.NEXTVAL,?,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, payment.getCardNumber());
            ps.setLong(2, payment.getBooking().getID());
            ps.setDouble(3, payment.getTotalAmount());
            ps.setLong(4, payment.getPaymentOpt().getID());
            
            ps.execute();
            c.commit();

        }
    }

    @Override
    public void deletePayment(Long paymentId) throws Exception {
         String sql = "UPDATE PAYMENT SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, paymentId);
            ps.execute();
            c.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
