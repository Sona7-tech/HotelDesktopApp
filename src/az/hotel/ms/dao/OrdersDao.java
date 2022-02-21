/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Orders;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface OrdersDao {
    
    List<Orders> getOrdersList() throws Exception;

    void addOrders(Orders orders) throws Exception;
    
    List<Orders> searchOrdersData(String keyword) throws Exception;

    Orders getOrdersById(Long ordersId) throws Exception;

    void updateOrders(Orders orders) throws Exception;
    void deleteOrders(Long ordersId) throws Exception;
}
