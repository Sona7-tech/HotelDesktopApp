/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.model;

/**
 *
 * @author Lenovo
 */
public class Menu extends CommonModel {
    
    private String menuName;
    private String desCripTion;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDesCripTion() {
        return desCripTion;
    }

    public void setDesCripTion(String desCripTion) {
        this.desCripTion = desCripTion;
    }

    @Override
    public String toString() {
        return menuName + "-" + desCripTion;
    }

    
}
