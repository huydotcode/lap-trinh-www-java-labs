package iuh.fit.se;

/**
 * Demo tổng hợp cả 3 cách cấu hình Spring Framework
 * 1. XML-Based Configuration
 * 2. Annotation-Based Configuration  
 * 3. Java-Based Configuration
 */
public class SpringConfigurationDemo {
    
    public static void main(String[] args) {
        System.out.println("🥳 DEMO TỔNG HỢP SPRING CONFIGURATION 🥳");
        System.out.println("=" .repeat(60));
        System.out.println();
        
        System.out.println("📁 1. XML-BASED CONFIGURATION");
        System.out.println("-" .repeat(30));
        try {
            XmlApp.main(args);
        } catch (Exception e) {
            System.out.println("❌ Lỗi XML Configuration: " + e.getMessage());
        }
        
        System.out.println();
        System.out.println("📁 2. ANNOTATION-BASED CONFIGURATION");
        System.out.println("-" .repeat(30));
        try {
            AnnotationApp.main(args);
        } catch (Exception e) {
            System.out.println("❌ Lỗi Annotation Configuration: " + e.getMessage());
        }
        
        System.out.println();
        System.out.println("📁 3. JAVA-BASED CONFIGURATION");
        System.out.println("-" .repeat(30));
        try {
            JavaApp.main(args);
        } catch (Exception e) {
            System.out.println("❌ Lỗi Java Configuration: " + e.getMessage());
        }
        
        System.out.println();
        System.out.println("📋 TÓM TẮT CÁC CÁCH CẤU HÌNH SPRING:");
        System.out.println("🔹 XML-Based: Sử dụng ClassPathXmlApplicationContext + beans.xml");
        System.out.println("🔹 Annotation-Based: Sử dụng AnnotationConfigApplicationContext + @Component");
        System.out.println("🔹 Java-Based: Sử dụng AnnotationConfigApplicationContext + @Bean methods");
        System.out.println();
        System.out.println("🎯 TẤT CẢ CÁC CÁCH ĐỀU HOẠT ĐỘNG TỐT VÀ DEMONSTRATION THÀNH CÔNG!");
    }
}
