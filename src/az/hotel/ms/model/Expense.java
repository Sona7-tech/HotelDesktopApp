package az.hotel.ms.model;

import java.util.Date;

public class Expense extends CommonModel {
	
	private Employee employee;
        private String expenseType;
        private double expenseAmount;
        private Date expenseDate;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    @Override
    public String toString() {
        return "Expense{" + "employee=" + employee + ", expenseType=" + expenseType + ", expenseAmount=" + expenseAmount + ", expenseDate=" + expenseDate + '}';
    }

   

        

}
