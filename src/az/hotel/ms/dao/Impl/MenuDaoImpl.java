/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.MenuDao;
import az.hotel.ms.model.Menu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class MenuDaoImpl implements MenuDao {

    @Override
    public List<Menu> getMenuList() throws Exception {
      
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT ID, MENU_NAME, MENU_DESCRIPTION FROM MENU \n"
                + "WHERE ACTIVE = 1";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Menu menu = new Menu();
                menu.setID(rs.getLong("ID"));
                menu.setMenuName(rs.getString("MENU_NAME"));
                menu.setDesCripTion(rs.getString("MENU_DESCRIPTION"));
                menuList.add(menu);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return menuList;
    }
    
}
