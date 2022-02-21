/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Booking;
import az.hotel.ms.model.Payment;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface PaymentDao {

    List<Payment> getPaymentList() throws Exception;

    List<Payment> searchPaymentData(String keyword) throws Exception;

    Payment getPaymentById(Long paymentId) throws Exception;

    void updatePayment(Payment payment) throws Exception;
    
    void addPayment(Payment payment) throws Exception;
    
    void deletePayment(Long paymentId) throws Exception;

}
