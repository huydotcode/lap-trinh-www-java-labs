package iuh.fit.se.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfiguration {
    
    // Bean định nghĩa sử dụng Java method với @Bean annotation
    @Bean
    public AddressBean addressBean() {
        AddressBean address = new AddressBean();
        address.setCity("Can Tho");
        address.setState("Can Tho");
        address.setCountry("Vietnam");
        return address;
    }
    
    // Bean address khác sử dụng constructor injection
    @Bean(name = "addressHaNoi")
    public AddressBean addressHaNoi() {
        return new AddressBean("Ha Noi", "Ha Noi", "Vietnam");
    }
    
    // Bean employee sử dụng setter injection
    @Bean
    public EmployeeBean employeeWithSetter() {
        EmployeeBean employee = new EmployeeBean();
        employee.setId(201);
        employee.setName("Tran Van JavaSetter");
        employee.setAddress(addressBean()); // Inject dependency
        return employee;
    }
    
    // Bean employee sử dụng constructor injection
    @Bean(name = "employeeConstructor")
    public EmployeeBean employeeWithConstructor() {
        return new EmployeeBean(202, "Le Thi JavaConstructor", addressHaNoi());
    }
    
    // Bean employee với dependency injection tự động
    @Bean(name = "employeeAutoInjection")
    public EmployeeBean employeeAutoInjection() {
        EmployeeBean employee = new EmployeeBean();
        employee.setId(203);
        employee.setName("Pham Van AutoInjection");
        employee.setAddress(addressBean()); // Sẽ sử dụng cùng instance như addressBean()
        return employee;
    }
}
