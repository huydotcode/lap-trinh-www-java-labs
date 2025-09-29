package iuh.fit.se;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApp {
    
    public static void main(String[] args) {
        // Sử dụng XML-Based Configuration với ClassPathXmlApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        System.out.println("=== XML-Based Configuration Demo ===");
        System.out.println();
        
        // Lấy bean sử dụng Constructor Injection
        Employee employee1 = (Employee) context.getBean("employee1");
        System.out.println("Employee 1 (Constructor Injection): " + employee1);
        System.out.println("Address của Employee 1: " + employee1.getAddress());
        System.out.println();
        
        // Lấy bean sử dụng Setter Injection
        Employee employee2 = (Employee) context.getBean("employee2");
        System.out.println("Employee 2 (Setter Injection): " + employee2);
        System.out.println("Address của Employee 2: " + employee2.getAddress());
        System.out.println();
        
        // Lấy bean Address trực tiếp
        Address address1 = (Address) context.getBean("address1");
        Address address2 = (Address) context.getBean("address2");
        
        System.out.println("Address 1 (dùng trong Employee 1): " + address1);
        System.out.println("Address 2 (dùng trong Employee 2): " + address2);
        
        System.out.println();
        System.out.println("✅ XML-Based Configuration thực thi thành công!");
    }
}
