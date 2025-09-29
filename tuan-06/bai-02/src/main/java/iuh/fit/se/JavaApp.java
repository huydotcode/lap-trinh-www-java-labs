package iuh.fit.se;

import iuh.fit.se.java.AddressBean;
import iuh.fit.se.java.EmployeeBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaApp {
    
    public static void main(String[] args) {
        // Sử dụng Java-Based Configuration với AnnotationConfigApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(iuh.fit.se.java.JavaConfiguration.class);
        
        System.out.println("=== Java-Based Configuration Demo ===");
        System.out.println();
        
        // Lấy bean sử dụng setter injection
        EmployeeBean employeeWithSetter = context.getBean("employeeWithSetter", EmployeeBean.class);
        System.out.println("Employee với Setter Injection: " + employeeWithSetter);
        System.out.println();
        
        // Lấy bean sử dụng constructor injection
        EmployeeBean employeeConstructor = context.getBean("employeeConstructor", EmployeeBean.class);
        System.out.println("Employee với Constructor Injection: " + employeeConstructor);
        System.out.println();
        
        // Lấy bean với auto injection
        EmployeeBean employeeAuto = context.getBean("employeeAutoInjection", EmployeeBean.class);
        System.out.println("Employee với Auto Injection: " + employeeAuto);
        System.out.println();
        
        // Lấy address beans
        AddressBean addressBean = context.getBean("addressBean", AddressBean.class);
        AddressBean addressHaNoi = context.getBean("addressHaNoi", AddressBean.class);
        
        System.out.println("Address Bean (default): " + addressBean);
        System.out.println("Address Hanoi: " + addressHaNoi);
        System.out.println();
        
        // Kiểm tra singleton pattern
        System.out.println("Kiểm tra Singleton Pattern:");
        System.out.println("employeeWithSetter và employeeAuto có cùng Address? " + 
                          (employeeWithSetter.getAddress() == employeeAuto.getAddress()));
        System.out.println("Address bean cùng instance? " + 
                          (addressBean == employeeWithSetter.getAddress()));
        System.out.println();
        
        System.out.println("✅ Java-Based Configuration thực thi thành công!");
        System.out.println("Ưu điểm của Java-Based Configuration:");
        System.out.println("- Type-safe: compile-time checking");
        System.out.println("- Refactoring-friendly: IDE hỗ trợ tốt");
        System.out.println("- Flexible: có thể sử dụng logic Java phức tạp");
        System.out.println("- Dependency injection tự động giữa các @Bean methods");
    }
}
