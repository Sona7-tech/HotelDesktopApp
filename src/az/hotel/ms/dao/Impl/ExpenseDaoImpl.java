 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.ExpenseDao;
import az.hotel.ms.model.Employee;
import az.hotel.ms.model.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ExpenseDaoImpl implements ExpenseDao {

    @Override
    public List<Expense> getExpenseList() throws Exception {
        List<Expense> expenseList = new ArrayList<>();
		String sql = "SELECT EX.ID EX_ID,\n" +
"          E.ID,\n" +                        
"          E.NAME,\n" +
"          E.SURNAME,\n" +
"          EX.EXPENSE_AMOUNT, EX.EXPENSE_TYPE, EX.EXPENSE_DATE\n" +
"    FROM EXPENSE EX\n" +
"          INNER JOIN EMPLOYEE E\n" +
"             ON EX.EMPLOYEE_ID = E.ID\n" +
"                 WHERE EX.ACTIVE = 1\n" +
"             ORDER BY EX.ID";

		try (Connection c = DBHelper.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql)) {
			while (rs.next()) {

				Expense expense = new Expense();
                                expense.setID(rs.getLong("EX_ID"));
                                Employee employee = new Employee();
                                employee.setID(rs.getLong("ID"));
                                employee.setName(rs.getString("NAME"));
                                employee.setSurname(rs.getString("SURNAME"));
                                expense.setExpenseAmount(rs.getInt("EXPENSE_AMOUNT"));
                                expense.setExpenseType(rs.getString("EXPENSE_TYPE"));
                                expense.setExpenseDate(rs.getDate("EXPENSE_DATE"));
                                expense.setEmployee(employee);
                                expenseList.add(expense);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return expenseList;
    }

    @Override
    public void addExpense(Expense expense) throws Exception {
           String sql = "INSERT INTO EXPENSE(ID, EMPLOYEE_ID, EXPENSE_TYPE, EXPENSE_AMOUNT, EXPENSE_DATE)\n"
                + "VALUES(EXPENSE_SEQ.NEXTVAL,?,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, expense.getEmployee().getID());
            ps.setString(2, expense.getExpenseType());
            ps.setDouble(3, expense.getExpenseAmount());
            ps.setDate(4, new java.sql.Date(expense.getExpenseDate().getTime()));
            ps.execute();
            c.commit();
        }
    }

    @Override
    public List<Expense> searchExpenseData(String keyword) throws Exception {
    
        List<Expense> expenseList = new ArrayList();
        String sql = "SELECT E.ID,\n" +
"                M.ID AS M_ID,\n" +                
"                M.NAME,\n" +
"                M.SURNAME,\n" +
"                E.EXPENSE_TYPE,\n" +
"                E.EXPENSE_AMOUNT,\n" +
"                E.EXPENSE_DATE\n" +
"               FROM EXPENSE E\n" +
"               INNER JOIN EMPLOYEE M ON E.EMPLOYEE_ID = M.ID\n" +
"               WHERE E.ACTIVE = 1\n" +
"              AND (LOWER(M.NAME) LIKE LOWER(?) OR LOWER (M.SURNAME) LIKE  LOWER(?) OR LOWER (E.EXPENSE_TYPE) LIKE LOWER (?) OR LOWER (E.EXPENSE_AMOUNT) LIKE LOWER(?) OR LOWER (E.EXPENSE_DATE) LIKE LOWER(?))\n" +
"               ORDER BY E.ID";
         try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ps.setString(5, "%" + keyword + "%");
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Employee employee = new Employee();
                employee.setID(rs.getLong("M_ID"));
                employee.setName(rs.getString("NAME"));
                employee.setSurname(rs.getString("SURNAME"));
                
                Expense expense = new Expense();
                expense.setID(rs.getLong("ID"));
                expense.setExpenseType(rs.getString("EXPENSE_TYPE"));
                expense.setExpenseAmount(rs.getDouble("EXPENSE_AMOUNT"));
                expense.setExpenseDate(rs.getDate("EXPENSE_DATE"));
                expense.setEmployee(employee);
                expenseList.add(expense);
            }
         }catch(Exception ex){
             
             ex.printStackTrace();
         }
        
        
        
        return expenseList;
    }

    @Override
    public Expense getExpenseById(Long expenseId) throws Exception {
           Expense expense = new Expense();
		String sql = "SELECT EX.ID AS EX_ID,\n" +
"          E.ID AS E_ID,\n" +                        
"          E.NAME,\n" +
"          E.SURNAME,\n" +
"          EX.EXPENSE_AMOUNT, EX.EXPENSE_TYPE, EX.EXPENSE_DATE\n" +
"    FROM EXPENSE EX\n" +
"          INNER JOIN EMPLOYEE E\n" +
"             ON EX.EMPLOYEE_ID = E.ID\n" +
"                 WHERE E.ACTIVE = 1\n" +
"             AND EX.ID = ?";

		try (Connection c = DBHelper.getConnection();
				PreparedStatement ps = c.prepareStatement(sql)) {
                        ps.setLong(1, expenseId);
                        ResultSet rs = ps.executeQuery();
			while (rs.next()) {
                                expense.setID(rs.getLong("EX_ID"));
                                Employee employee = new Employee();
                                employee.setID(rs.getLong("E_ID"));
                                employee.setName(rs.getString("NAME"));
                                employee.setSurname(rs.getString("SURNAME"));
                                expense.setExpenseAmount(rs.getInt("EXPENSE_AMOUNT"));
                                expense.setExpenseType(rs.getString("EXPENSE_TYPE"));
                                expense.setExpenseDate(rs.getDate("EXPENSE_DATE"));
                                expense.setEmployee(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return expense;
    }



    @Override
    public void updateExpense(Expense expense) throws Exception {
        
        String sql = "UPDATE EXPENSE SET EMPLOYEE_ID = ?, EXPENSE_TYPE = ?, EXPENSE_AMOUNT = ?, EXPENSE_DATE = ?\n"
                + "WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, expense.getEmployee().getID());
            ps.setString(2, expense.getExpenseType());
            ps.setDouble(3, expense.getExpenseAmount());
            ps.setDate(4, new java.sql.Date(expense.getExpenseDate().getTime()));
            ps.setLong(5, expense.getID());
            ps.execute();
            c.commit();
        }
    }

    @Override
    public void deleteExpense(Long expenseId) throws Exception {
       
           String sql = "UPDATE EXPENSE SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, expenseId);
            ps.execute();
            c.commit();
        }
    }
    
    
    
}
