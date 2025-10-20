package iuh.fit.se.controllers;

import iuh.fit.se.entities.Employee;
import iuh.fit.se.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String index(Model model) {
        List<Employee> employees = this.employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "index";
    }

    @GetMapping("/add-employee")
    public ModelAndView addEmployee(ModelAndView modelAndView) {
        Employee employee = new Employee();

        modelAndView.addObject("employee", employee);
        modelAndView.setViewName("add-employee");

        return modelAndView;
    }

    @PostMapping("/save-employee")
    public String save(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Determine which view to return based on whether it's an update or add
            return employee.getId() > 0 ? "update-employee" : "add-employee";
        }

        this.employeeService.saveEmployee(employee);

        return "redirect:";
    }

    @GetMapping("/update-employee")
    public ModelAndView updateEmployee(@RequestParam("employee_id") int id, ModelAndView modelAndView) {
        Employee employee = this.employeeService.getEmployeeById(id);
        
        if (employee == null) {
            modelAndView.setViewName("error");
            return modelAndView;
        }

        modelAndView.addObject("employee", employee);
        modelAndView.setViewName("update-employee");

        return modelAndView;
    }

    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("employee_id") int id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:";
    }
}
