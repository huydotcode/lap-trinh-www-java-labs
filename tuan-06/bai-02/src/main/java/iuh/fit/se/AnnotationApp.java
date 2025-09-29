package iuh.fit.se;

import iuh.fit.se.annotation.AddressComponent;
import iuh.fit.se.annotation.EmployeeComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApp {
    
    public static void main(String[] args) {
        // Sử dụng Annotation-Based Configuration với AnnotationConfigApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext("iuh.fit.se.annotation");
        
        System.out.println("=== Annotation-Based Configuration Demo ===");
        System.out.println();
        
        // Lấy bean được tự động tạo bởi Spring container
        EmployeeComponent employee = context.getBean(EmployeeComponent.class);
        System.out.println("Employee Component: " + employee);
        System.out.println("Address của Employee: " + employee.getAddress());
        System.out.println();
        
        // Lấy bean Address trực tiếp
        AddressComponent address = context.getBean(AddressComponent.class);
        System.out.println("Address Component: " + address);
        System.out.println();
        
        // Kiểm tra cùng một instance
        System.out.println("Có phải cùng một instance? " + (employee.getAddress() == address));
        
        System.out.println();
        System.out.println("✅ Annotation-Based Configuration thực thi thành công!");
        System.out.println("Các annotation đã sử dụng:");
        System.out.println("- @Component: để đánh dấu class là Spring bean");
        System.out.println("- @Value: để inject giá trị literal");
        System.out.println("- @Autowired: để inject dependency");
        System.out.println("- @Configuration: để đánh dấu configuration class");
        System.out.println("- @ComponentScan: để scan và tự động đăng ký beans");
    }
}
