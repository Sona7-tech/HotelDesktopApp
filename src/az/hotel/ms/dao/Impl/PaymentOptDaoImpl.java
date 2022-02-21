/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.PaymentOptDao;
import az.hotel.ms.model.PaymentOpt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class PaymentOptDaoImpl implements PaymentOptDao {

    @Override
    public List<PaymentOpt> getPaymentOptList() throws Exception {
        
         List<PaymentOpt> paymentOptList = new ArrayList<>();
       String sql = "SELECT ID, NAME FROM PAYMENTOPT WHERE ACTIVE = 1";
       try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                PaymentOpt paymentOpt = new PaymentOpt();
                paymentOpt.setID(rs.getLong("ID"));
                paymentOpt.setPaymentName(rs.getString("NAME"));
                paymentOptList.add(paymentOpt);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return paymentOptList;
    }
    
    
    
}
