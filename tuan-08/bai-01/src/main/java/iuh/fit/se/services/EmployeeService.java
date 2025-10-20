package iuh.fit.se.services;

import iuh.fit.se.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(int id);
    public Employee saveEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(int id);
}
