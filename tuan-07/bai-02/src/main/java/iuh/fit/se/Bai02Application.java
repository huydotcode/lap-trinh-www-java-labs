package iuh.fit.se;

import iuh.fit.se.entities.Employee;
import iuh.fit.se.repositories.EmployeeRepository;
import iuh.fit.se.services.EmployeeService;
import iuh.fit.se.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Bai02Application {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Bai02Application.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {

            EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

            // Create and insert a new employee
            Employee emp1 = new Employee(0, "Huy", "huy.do@example.com");
            Employee emp2 = new Employee(1, "Huy", "huy.do@example.com");
            Employee emp3 = new Employee(2, "Huy", "huy.do@example.com");
            Employee emp4 = new Employee(3, "Huy", "huy.do@example.com");
            Employee savedEmployee = employeeService.insert(emp1);
            Employee savedEmployee2 = employeeService.insert(emp2);
            Employee savedEmployee3 = employeeService.insert(emp3);
            Employee savedEmployee4 = employeeService.insert(emp4);
            System.out.println("INSERTED: " + savedEmployee);

            // Find all employees
            System.out.println("\n--- FIND ALL EMPLOYEES ---");
            List<Employee> employees = employeeService.findAll();
            employees.forEach(System.out::println);

            // Find an employee by ID
            System.out.println("\n--- FIND EMPLOYEE BY ID: " + savedEmployee.getEmployeeId() + " ---");
            Employee employee2 = employeeService.findById(savedEmployee.getEmployeeId());
            System.out.println("FIND EMPLOYEE BY ID: " + employee2.getEmployeeId());

            // Update an employee
            System.out.println("\n--- UPDATE EMPLOYEE ID: " + savedEmployee.getEmployeeId() + " ---");
            savedEmployee.setEmployeeRole("new role");
            Employee employee3 = employeeService.findById(savedEmployee.getEmployeeId());
            System.out.println("UPDATE EMPLOYEE ID: " + employee3.getEmployeeId());

            // Find all employees again to see the update
            System.out.println("\n--- FIND ALL EMPLOYEES AFTER UPDATE ---");
            employeeService.findAll().forEach(System.out::println);

            // Delete an employee
            System.out.println("\n--- DELETE EMPLOYEE ID: " + savedEmployee.getEmployeeId() + " ---");
            boolean isDeleted = employeeService.delete(savedEmployee.getEmployeeId());
            System.out.println("Deleted: " + isDeleted);

            // Find all employees again to see the deletion
            System.out.println("\n--- FIND ALL EMPLOYEES AFTER DELETE ---");
            employeeService.findAll().forEach(System.out::println);
        };
    }
}
