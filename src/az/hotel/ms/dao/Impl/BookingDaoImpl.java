/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.BookingDao;
import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.model.Booking;
import az.hotel.ms.model.Client;
import az.hotel.ms.model.Room;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Lenovo
 */
public class BookingDaoImpl implements BookingDao {

    @Override
    public List<Booking> getBookingList() throws Exception {

        List<Booking> bookingList = new ArrayList<>();
        String sql = "SELECT B.ID,\n"
                + "          C.NAME,\n"
                + "          C.SURNAME,\n"
                + "          R.ROOM_NUMBER,\n"
                + "          B.CHECK_IN,\n"
                + "          B.CHECK_OUT\n"
                + "    FROM BOOKING B \n"
                + "          INNER JOIN ROOM R\n"
                + "            ON B.ROOM_ID = R.ID\n"
                + "          INNER JOIN CLIENT C\n"
                + "           ON B.CUSTOMER_ID = C.ID\n"
                + "                 WHERE B.ACTIVE = 1\n"
                + "             ORDER BY B.ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {

                Booking booking = new Booking();
                booking.setID(rs.getLong("ID"));
                booking.setCheckIn(rs.getDate("CHECK_IN"));
                booking.setCheckOut(rs.getDate("CHECK_OUT"));
                Room room = new Room();
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));

                Client client = new Client();
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                booking.setRoom(room);
                booking.setClient(client);
                bookingList.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bookingList;
    }

    @Override
    public void addBooking(Booking booking) throws Exception {

        String sql = "INSERT INTO BOOKING(ID, ROOM_ID, CUSTOMER_ID, CHECK_IN,CHECK_OUT)\n"
                + "VALUES(BOOKING_SEQ.NEXTVAL,?,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, booking.getRoom().getID());
            ps.setLong(2, booking.getClient().getID());
            ps.setDate(3, new java.sql.Date(booking.getCheckIn().getTime()));
            ps.setDate(4, new java.sql.Date(booking.getCheckOut().getTime()));
            ps.execute();
            c.commit();
        }
    }

    @Override
    public Booking getBookingById(Long bookingId) throws Exception {
        Booking booking = new Booking();
        String sql = "SELECT B.ID as B_ID,\n"
                + "          C.ID as C_ID,\n"
                + "          C.NAME,\n"
                + "          C.SURNAME,\n"
                + "          R.ID as R_ID,\n"
                + "          R.ROOM_NUMBER,\n"
                + "          B.CHECK_IN,\n"
                + "          B.CHECK_OUT\n"
                + "    FROM BOOKING B \n"
                + "          INNER JOIN ROOM R\n"
                + "            ON B.ROOM_ID = R.ID\n"
                + "          INNER JOIN CLIENT C\n"
                + "           ON B.CUSTOMER_ID = C.ID\n"
                + "                 WHERE B.ACTIVE = 1\n"
                + "             AND B.ID = ?";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setLong(1, bookingId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                booking.setID(rs.getLong("B_ID"));
                booking.setCheckIn(rs.getDate("CHECK_IN"));
                booking.setCheckOut(rs.getDate("CHECK_OUT"));
                Room room = new Room();
                room.setID(rs.getLong("R_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                Client client = new Client();
                client.setID(rs.getLong("C_ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                booking.setRoom(room);
                booking.setClient(client);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return booking;
    }

    @Override
    public void updateBooking(Booking booking) throws Exception {

        String sql = "UPDATE BOOKING SET ROOM_ID = ?, CUSTOMER_ID = ?, CHECK_IN = ?, CHECK_OUT = ? WHERE ID = ? ";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, booking.getRoom().getID());
            ps.setLong(2, booking.getClient().getID());
            ps.setDate(3, new java.sql.Date(booking.getCheckIn().getTime()));
            ps.setDate(4, new java.sql.Date(booking.getCheckOut().getTime()));
            ps.setLong(5, booking.getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteBooking(Long bookingId) throws Exception {

        String sql = "UPDATE BOOKING SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, bookingId);
            ps.execute();
            c.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> searchBookingData(String keyword) throws Exception {

        List<Booking> bookingList = new ArrayList();
        String sql = "SELECT B.ID,\n"
                + "C.ID AS CLIENT_ID,\n"
                + "C.NAME,\n"
                + "C.SURNAME,\n"
                + "R.ID AS ROOM_ID,\n"
                + "R.ROOM_NUMBER,\n"
                + "B.CHECK_IN, B.CHECK_OUT\n"
                + "FROM BOOKING B\n"
                + "INNER JOIN CLIENT C ON B.CUSTOMER_ID = C.ID\n"
                + "INNER JOIN ROOM R ON B.ROOM_ID = R.ID\n"
                + "WHERE B.ACTIVE = 1\n"
                + "AND (LOWER(C.NAME) LIKE LOWER(?) OR LOWER (C.SURNAME) LIKE LOWER(?) "
                + "OR LOWER (R.ROOM_NUMBER) LIKE LOWER (?) OR "
                + "LOWER (B.CHECK_IN) LIKE LOWER(?) OR LOWER (B.CHECK_OUT) LIKE LOWER(?))\n"
                + "ORDER BY B.ID";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");
            
            ResultSet rs = ps.executeQuery();
            
             while (rs.next()) {

                Client client  = new Client();
                client.setID(rs.getLong("CLIENT_ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));

                Room room = new Room();
                room.setID(rs.getLong("ROOM_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));

                Booking booking = new Booking();
                booking.setID(rs.getLong("ID"));
                booking.setCheckIn(rs.getDate("CHECK_IN"));
                booking.setCheckOut(rs.getDate("CHECK_OUT"));
                booking.setClient(client);
                booking.setRoom(room);
                bookingList.add(booking);
            }
        }
        
        
        return bookingList;
    }
}
