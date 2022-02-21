/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Menu;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface MenuDao {
    
    List<Menu> getMenuList() throws Exception;
    
}
