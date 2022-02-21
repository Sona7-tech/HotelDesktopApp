/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.dao.Impl;

import az.hotel.ms.dao.DBHelper;
import az.hotel.ms.dao.EmployeeDao;
import az.hotel.ms.model.Employee;
import az.hotel.ms.model.Positions;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public List<Employee> getEmployeeList() throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT E.ID E_ID,\n"
                + "                          E.NAME,\n"
                + "                          E.SURNAME,\n"
                + "                          E.GENDER,\n"
                + "                          E.EMAIL,\n"
                + "                          E.CONTACT,\n"
                + "                          P.ID P_ID,\n"
                + "                          P.POSITION_NAME\n"
                + "                    FROM EMPLOYEE E \n"
                + "                          INNER JOIN POSITIONS P\n"
                + "                             ON E.POSITION_ID = P.ID \n"
                + "                                 WHERE E.ACTIVE = 1\n"
                + "                             ORDER BY E.ID";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {

                Employee employee = new Employee();
                employee.setID(rs.getLong("E_ID"));
                employee.setName(rs.getString("NAME"));
                employee.setSurname(rs.getString("SURNAME"));
                employee.setGender(rs.getString("GENDER"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setContact(rs.getString("CONTACT"));
                Positions positions = new Positions();
                positions.setID(rs.getLong("P_ID"));
                positions.setPositionName(rs.getString("POSITION_NAME"));
                employee.setPosition(positions);
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return employeeList;
    }

    @Override
    public void addEmployee(Employee employee) throws Exception {

        String sql = "INSERT INTO EMPLOYEE(ID,NAME, SURNAME,GENDER,EMAIL, CONTACT, POSITION_ID)\n"
                + "VALUES(EMPLOYEE_SEQ.NEXTVAL,?,?,?,?,?,?)";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            System.out.println("1 " + employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getGender());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getContact());
            ps.setLong(6, employee.getPosition().getID());
            ps.execute();
            c.commit();

        }
    }

    @Override
    public List<Employee> searchEmployeeData(String keyword) throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT E.ID,\n"
                + "E.NAME,\n"
                + "E.SURNAME,\n"
                + "E.GENDER,\n"
                + "E.EMAIL,\n"
                + "E.CONTACT,\n"
                + "P.ID AS P_ID,\n"
                + "P.POSITION_NAME \n"
                + "FROM EMPLOYEE E\n"
                + "INNER JOIN POSITIONS P ON E.POSITION_ID = P.ID\n"
                + "WHERE E.ACTIVE = 1\n"
                + "AND (LOWER(E.NAME) LIKE LOWER(?) OR LOWER (E.SURNAME) LIKE LOWER(?) OR LOWER (E.EMAIL) LIKE LOWER(?)\n"
                + "OR LOWER (P.POSITION_NAME) LIKE LOWER(?))\n"
                + "ORDER BY E.ID";

         try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setString(4, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Positions positions = new Positions();
                positions.setID(rs.getLong("P_ID"));
                positions.setPositionName(rs.getString("POSITION_NAME"));

                Employee employee = new Employee();
                employee.setID(rs.getLong("ID"));
                employee.setName(rs.getString("NAME"));
                employee.setSurname(rs.getString("SURNAME"));
                employee.setGender(rs.getString("GENDER"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setContact(rs.getString("CONTACT"));
                employee.setPosition(positions);
                employeeList.add(employee);
            }
         }
         
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws Exception {
          Employee employee = new Employee();
        String sql = "SELECT E.ID E_ID,\n"
                + "                          E.NAME,\n"
                + "                          E.SURNAME,\n"
                + "                          E.GENDER,\n"
                + "                          E.EMAIL,\n"
                + "                          E.CONTACT,\n"
                + "                          P.ID P_ID,\n"
                + "                          P.POSITION_NAME\n"
                + "                    FROM EMPLOYEE E \n"
                + "                          INNER JOIN POSITIONS P\n"
                + "                             ON E.POSITION_ID = P.ID \n"
                + "                                 WHERE E.ACTIVE = 1\n"
                + "                             AND E.ID = ?";

        try (Connection c = DBHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

               // Employee employee = new Employee();
                employee.setID(rs.getLong("E_ID"));
                employee.setName(rs.getString("NAME"));
                employee.setSurname(rs.getString("SURNAME"));
                employee.setGender(rs.getString("GENDER"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setContact(rs.getString("CONTACT"));
                Positions positions = new Positions();
                positions.setID(rs.getLong("P_ID"));
                positions.setPositionName(rs.getString("POSITION_NAME"));
                employee.setPosition(positions);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return employee;
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        
        String sql = "UPDATE EMPLOYEE SET ACTIVE = 0 "
                + " WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, employeeId);
            ps.execute();
            c.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws Exception {
        
         String sql = "UPDATE EMPLOYEE SET NAME = ?, SURNAME = ?,GENDER = ?,EMAIL = ?, CONTACT = ?, POSITION_ID = ?\n"
                + "WHERE ID = ?";
        try (Connection c = DBHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            System.out.println("1 " + employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getGender());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getContact());
            ps.setLong(6, employee.getPosition().getID());
            ps.setLong(7, employee.getID());
            ps.execute();
            c.commit();

        }
        
    }

}
