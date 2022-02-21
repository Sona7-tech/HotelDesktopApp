/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Reservation;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ReserveDao {
    
    List<Reservation> getReservList() throws Exception;

    void addReservation(Reservation reservation) throws Exception;

    void deleteReserve(Long reserveId) throws Exception;
    
    List<Reservation> searchReservationData(String keyword) throws Exception;
    
    Reservation getReserveById (Long reservegId) throws Exception;
    
    void updateReserve(Reservation reservation) throws Exception;
}
