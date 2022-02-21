package az.hotel.ms.dao;

import az.hotel.ms.model.Employee;
import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeeList() throws Exception;

    void addEmployee(Employee employee) throws Exception;

    List<Employee> searchEmployeeData(String keyword) throws Exception;

    Employee getEmployeeById(Long employeeId) throws Exception;

    void deleteEmployee(Long employeeId) throws Exception;

    void updateEmployee(Employee employee) throws Exception;
}
