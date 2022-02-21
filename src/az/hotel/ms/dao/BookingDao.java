/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Booking;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface BookingDao {

    List<Booking> getBookingList() throws Exception;

    void addBooking(Booking booking) throws Exception;

    Booking getBookingById(Long bookingId) throws Exception;

    void updateBooking(Booking booking) throws Exception;

    void deleteBooking(Long bookingId) throws Exception;

    List<Booking> searchBookingData(String keyword) throws Exception;
}
