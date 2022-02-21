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
public class Cleaning extends CommonModel {
    
    private String cleanCondition;

    public String getCleanCondition() {
        return cleanCondition;
    }

    public void setCleanCondition(String cleanCondition) {
        this.cleanCondition = cleanCondition;
    }

    @Override
    public String toString() {
        return cleanCondition;
    }
    
}
