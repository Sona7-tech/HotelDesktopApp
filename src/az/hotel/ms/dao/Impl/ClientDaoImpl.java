package az.hotel.ms.dao.Impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import az.hotel.ms.dao.ClientDao;
import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.model.Client;

public class ClientDaoImpl implements ClientDao {

    @Override
    public List<Client> getClientList() throws Exception {

        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT ID, NAME, SURNAME, GENDER, CONTACT_NUMBER, NATIONALITY FROM CLIENT\r\n"
                + "WHERE ACTIVE = 1";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {

                Client client = new Client();
                client.setID(rs.getLong("ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                client.setGender(rs.getString("GENDER"));
                client.setPhone(rs.getString("CONTACT_NUMBER"));
                client.setNationality(rs.getString("NATIONALITY"));
                clientList.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return clientList;
    }

    @Override
    public void addClient(Client client) throws Exception {

        String sql = "INSERT INTO CLIENT(ID, NAME, SURNAME, GENDER, CONTACT_NUMBER, NATIONALITY)\r\n"
                + "VALUES(CLIENT_SEQ.NEXTVAL, ?,?,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getGender());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getNationality());
            ps.execute();
            c.commit();

        }

    }

    @Override
    public Client getClientById(Long clientId) throws Exception {
        Client client = new Client();
        String sql = "SELECT ID, NAME, SURNAME, GENDER, CONTACT_NUMBER, NATIONALITY FROM CLIENT\r\n"
                + "WHERE ACTIVE = 1 AND ID = ?";

        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, clientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                client.setID(rs.getLong("ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                client.setGender(rs.getString("GENDER"));
                client.setPhone(rs.getString("CONTACT_NUMBER"));
                client.setNationality(rs.getString("NATIONALITY"));
            } else {

                client = null;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return client;
    }

    @Override
    public void updateClient(Client client) throws Exception {
        String sql = "UPDATE CLIENT SET NAME = ?, SURNAME = ?, GENDER = ?, CONTACT_NUMBER = ?, NATIONALITY = ? "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getGender());
            ps.setString(4, client.getPhone());
            ps.setString(5, client.getNationality());
            ps.setLong(6, client.getID());
            ps.execute();
            c.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void deleteClient(Long clientId) throws Exception {
        String sql = "UPDATE CLIENT SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, clientId);
            ps.execute();
            c.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public List<Client> searchClientData(String keyword) throws Exception {
        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT ID, NAME, SURNAME, GENDER, CONTACT_NUMBER, NATIONALITY FROM CLIENT WHERE ACTIVE = 1\r\n"
                + "AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?) OR LOWER(NATIONALITY) LIKE LOWER(?)) ORDER BY ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = rs = ps.executeQuery();
            while (rs.next()) {

                Client client = new Client();
                client.setID(rs.getLong("ID"));
                client.setName(rs.getString("NAME"));
                client.setSurname(rs.getString("SURNAME"));
                client.setGender(rs.getString("GENDER"));
                client.setPhone(rs.getString("CONTACT_NUMBER"));
                client.setNationality(rs.getString("NATIONALITY"));
                clientList.add(client);
            }
        }
        return clientList;

    }
}
