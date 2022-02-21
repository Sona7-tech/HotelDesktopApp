/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.ReserveDao;
import az.hotel.ms.model.Client;
import az.hotel.ms.model.Reservation;
import az.hotel.ms.model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ReserveDaoImpl implements ReserveDao {

    @Override
    public List<Reservation> getReservList() throws Exception {
        List<Reservation> reservList = new ArrayList<>();
		String sql = "SELECT R.ID,\n" +
"          C.ID AS C_ID,\n" +                        
"          C.NAME,\n" +
"          C.SURNAME,\n" +
"          A.ID AS A_ID,\n" +                        
"          A.ROOM_NUMBER,\n" +
"          R.RESERVE_DATE\n" +
"    FROM RESERVATION R \n" +
"          INNER JOIN ROOM A\n" +
"            ON R.ROOM_ID = A.ID\n" +
"          INNER JOIN CLIENT C\n" +
"           ON R.CUSTOMER_ID = C.ID\n" +
"                 WHERE R.ACTIVE = 1\n" +
"             ORDER BY R.ID";

		try (Connection c = DBHelper.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql)) {
			while (rs.next()) {

				Reservation reservation = new Reservation();
                                reservation.setID(rs.getLong("ID"));
                                reservation.setReserveDate(rs.getDate("RESERVE_DATE"));
                                Room room = new Room();
                                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                                room.setID(rs.getLong("A_ID"));
                                Client client = new Client();
                                client.setID(rs.getLong("C_ID"));
                                client.setName(rs.getString("NAME"));
                                client.setSurname(rs.getString("SURNAME"));
                                reservation.setRoom(room);
                                reservation.setClient(client);
                                reservList.add(reservation);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return reservList;
    }

    @Override
    public void addReservation(Reservation reservation) throws Exception {
         String sql = "INSERT INTO RESERVATION(ID, ROOM_ID, CUSTOMER_ID, RESERVE_DATE)\n"
                + "VALUES(RESERVE_SEQ.NEXTVAL,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, reservation.getRoom().getID());
            ps.setLong(2, reservation.getClient().getID());
            ps.setDate(3, new java.sql.Date(reservation.getReserveDate().getTime()));
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteReserve(Long reserveId) throws Exception {
          String sql = "UPDATE RESERVATION SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, reserveId);
            ps.execute();
            c.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> searchReservationData(String keyword) throws Exception {
        List<Reservation> reserveList = new ArrayList<>();
         String sql = "SELECT B.ID,\n"
                + "C.ID AS CLIENT_ID,\n"
                + "C.NAME,\n"
                + "C.SURNAME,\n"
                + "R.ID AS ROOM_ID,\n"
                + "R.ROOM_NUMBER,\n"
                + "B.RESERVE_DATE\n"
                + "FROM RESERVATION B\n"
                + "INNER JOIN CLIENT C ON B.CUSTOMER_ID = C.ID\n"
                + "INNER JOIN ROOM R ON B.ROOM_ID = R.ID\n"
                + "WHERE B.ACTIVE = 1\n"
                + "AND (LOWER(C.NAME) LIKE LOWER(?) OR LOWER (C.SURNAME) LIKE LOWER(?) "
                + "OR LOWER (R.ROOM_NUMBER) LIKE LOWER (?) OR "
                + "LOWER (B.RESERVE_DATE) LIKE LOWER(?))\n"
                + "ORDER BY B.ID";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            
            ResultSet rs = ps.executeQuery();
            
             while (rs.next()) {

                Client client  = new Client();
                client.setID(rs.getLong("CLIENT_ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));

                Room room = new Room();
                room.setID(rs.getLong("ROOM_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));

                Reservation reservation = new Reservation();
                reservation.setID(rs.getLong("ID"));
                reservation.setReserveDate(rs.getDate("RESERVE_DATE"));
                reservation.setClient(client);
                reservation.setRoom(room);
                reserveList.add(reservation);
            }
        }
        return reserveList;
    }

    @Override
    public Reservation getReserveById(Long reservegId) throws Exception {
          Reservation reservation = new Reservation();
		String sql = "SELECT R.ID,\n" +
"          C.ID AS C_ID,\n" +                        
"          C.NAME,\n" +
"          C.SURNAME,\n" +
"          A.ID AS A_ID,\n" +                        
"          A.ROOM_NUMBER,\n" +
"          R.RESERVE_DATE\n" +
"    FROM RESERVATION R \n" +
"          INNER JOIN ROOM A\n" +
"            ON R.ROOM_ID = A.ID\n" +
"          INNER JOIN CLIENT C\n" +
"           ON R.CUSTOMER_ID = C.ID\n" +
"                 WHERE R.ACTIVE = 1\n" +
"             AND R.ID = ?";

		try (Connection c = DBHelper.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
                       ps.setLong(1, reservegId);
                       ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				//Reservation reservation = new Reservation();
                                reservation.setID(rs.getLong("ID"));
                                reservation.setReserveDate(rs.getDate("RESERVE_DATE"));
                                Room room = new Room();
                                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                                room.setID(rs.getLong("A_ID"));
                                Client client = new Client();
                                client.setID(rs.getLong("C_ID"));
                                client.setName(rs.getString("NAME"));
                                client.setSurname(rs.getString("SURNAME"));
                                reservation.setRoom(room);
                                reservation.setClient(client);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return reservation;
    }

    @Override
    public void updateReserve(Reservation reservation) throws Exception {
        
        String sql = "UPDATE RESERVATION SET ROOM_ID = ?, CUSTOMER_ID = ?, RESERVE_DATE = ? WHERE ID = ? ";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, reservation.getRoom().getID());
            ps.setLong(2, reservation.getClient().getID());
            ps.setDate(3, new java.sql.Date(reservation.getReserveDate().getTime()));
            ps.setLong(4, reservation.getID());
            ps.execute();
            c.commit();
        }
    }

   
}
