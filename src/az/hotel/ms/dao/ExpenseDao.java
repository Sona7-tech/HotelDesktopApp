/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao;

import az.hotel.ms.model.Expense;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface ExpenseDao {
    
    List <Expense> getExpenseList() throws Exception;

    void addExpense(Expense expense) throws Exception;
    
    List<Expense> searchExpenseData(String keyword) throws Exception;
    
    Expense getExpenseById(Long expenseId) throws Exception;
    
    void updateExpense(Expense expense) throws Exception;
    
    void deleteExpense(Long expenseId) throws Exception;
}
