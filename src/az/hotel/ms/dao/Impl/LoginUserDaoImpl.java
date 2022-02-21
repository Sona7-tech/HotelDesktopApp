/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.LoginUserDao;
import az.hotel.ms.model.LoginUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */
public class LoginUserDaoImpl implements LoginUserDao {

    @Override
    public LoginUser login(String username, String password) throws Exception {
        LoginUser loginUser = new LoginUser();
        String sql = "SELECT ID, USERNAME, NAME, SURNAME FROM LOGIN_USER\n"
                + "WHERE ACTIVE = 1 AND USERNAME = ? AND PASSWORD = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                loginUser.setID(rs.getLong("ID"));
                loginUser.setUsername(rs.getString("USERNAME"));
                loginUser.setName(rs.getString("NAME"));
                loginUser.setSurname(rs.getString("SURNAME"));
            }
            else{
                
                loginUser = null;
            }
        }
     return loginUser;
    }
    

}
