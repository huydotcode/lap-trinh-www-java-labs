package iuh.fit.se.services;

import iuh.fit.se.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee insert(Employee employee);
    public List<Employee> findAll();
    public Employee findById(int id);
    public boolean update(Employee employee);
    public boolean delete(int employeeId);
}
