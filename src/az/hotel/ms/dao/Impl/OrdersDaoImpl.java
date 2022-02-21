/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.OrdersDao;
import az.hotel.ms.model.Client;
import az.hotel.ms.model.Menu;
import az.hotel.ms.model.Orders;
import az.hotel.ms.model.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class OrdersDaoImpl implements OrdersDao {

    @Override
    public List<Orders> getOrdersList() throws Exception {

        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT O.ID,\n"
                + "          M.MENU_NAME,\n"
                + "          M.MENU_DESCRIPTION,\n"
                + "          O.COST,\n"
                + "          R.ROOM_NUMBER\n"
                + "    FROM ORDERS O\n"
                + "          INNER JOIN MENU M\n"
                + "            ON O.MENU_ID = M.ID\n"
                + "          INNER JOIN ROOM R\n"
                + "           ON O.ROOM_ID = R.ID\n"
                + "                 WHERE O.ACTIVE = 1\n"
                + "             ORDER BY O.ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setID(rs.getLong("ID"));
                orders.setCost(rs.getInt("COST"));
                Menu menu = new Menu();
                menu.setMenuName(rs.getString("MENU_NAME"));
                menu.setDesCripTion(rs.getString("MENU_DESCRIPTION"));

                Room room = new Room();
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                orders.setMenu(menu);
                orders.setRoom(room);
                ordersList.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ordersList;
    }

    @Override
    public void addOrders(Orders orders) throws Exception {
        String sql = "INSERT INTO ORDERS(ID, MENU_ID, COST, ROOM_ID)\n"
                + "VALUES(ORDERS_SEQ.NEXTVAL,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, orders.getMenu().getID());
            ps.setDouble(2, orders.getCost());
            ps.setLong(3, orders.getRoom().getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Orders> searchOrdersData(String keyword) throws Exception {
        List<Orders> ordersList = new ArrayList<>();

        String sql = "SELECT O.ID,     \n" +
"                                M.ID AS M_ID,\n" +
"                                M.MENU_NAME,\n" +
"                                M.MENU_DESCRIPTION,\n" +
"                                O.COST,\n" +
"                                R.ID R_ID,\n" +
"                                R.ROOM_NUMBER\n" +
"                               FROM ORDERS O\n" +
"                               INNER JOIN MENU M ON O.MENU_ID = M.ID\n" +
"                               INNER JOIN ROOM R ON O.ROOM_ID = R.ID\n" +
"                               WHERE O.ACTIVE = 1\n" +
"                               AND (LOWER(M.MENU_NAME) LIKE LOWER(?) OR (LOWER(M.MENU_DESCRIPTION) LIKE LOWER(?) OR LOWER (O.COST) LIKE  LOWER(?) OR LOWER (R.ROOM_NUMBER) LIKE LOWER (?)))";

        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Room room = new Room();
                room.setID(rs.getLong("R_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));

                Menu menu = new Menu();
                menu.setID(rs.getLong("M_ID"));
                menu.setMenuName(rs.getString("MENU_NAME"));
                menu.setDesCripTion(rs.getString("MENU_DESCRIPTION"));

                Orders orders = new Orders();
                orders.setID(rs.getLong("ID"));
                orders.setCost(rs.getDouble("COST"));
                orders.setMenu(menu);
                orders.setRoom(room);
                ordersList.add(orders);
            }
        }
        return ordersList;
    }

    @Override
    public Orders getOrdersById(Long ordersId) throws Exception {
        Orders orders = new Orders();
        String sql = "SELECT O.ID,\n"
                + "         M.ID AS MENU_ID,\n"
                + "          M.MENU_NAME,\n"
                + "          M.MENU_DESCRIPTION,\n"
                + "          O.COST,\n"
                + "          R.ID AS R_ID,\n"
                + "          R.ROOM_NUMBER\n"
                + "    FROM ORDERS O\n"
                + "          INNER JOIN MENU M\n"
                + "            ON O.MENU_ID = M.ID\n"
                + "          INNER JOIN ROOM R\n"
                + "           ON O.ROOM_ID = R.ID\n"
                + "                 WHERE O.ACTIVE = 1\n"
                + "             AND O.ID = ?";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, ordersId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.setID(rs.getLong("ID"));
                orders.setCost(rs.getInt("COST"));
                Menu menu = new Menu();
                menu.setID(rs.getLong("MENU_ID"));
                menu.setMenuName(rs.getString("MENU_NAME"));
                menu.setDesCripTion(rs.getString("MENU_DESCRIPTION"));
                Room room = new Room();
                room.setID(rs.getLong("R_ID"));
                room.setRoomNumber(rs.getString("ROOM_NUMBER"));
                orders.setMenu(menu);
                orders.setRoom(room);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return orders;
    }

    @Override
    public void updateOrders(Orders orders) throws Exception {
        String sql = "UPDATE ORDERS SET  MENU_ID = ?, ROOM_ID = ?, COST = ? WHERE ID = ? ";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, orders.getMenu().getID());
            ps.setLong(2, orders.getRoom().getID());
            ps.setDouble(3, orders.getCost());
            ps.setLong(4, orders.getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteOrders(Long ordersId) throws Exception {
        String sql = "UPDATE ORDERS SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, ordersId);
            ps.execute();
            c.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
