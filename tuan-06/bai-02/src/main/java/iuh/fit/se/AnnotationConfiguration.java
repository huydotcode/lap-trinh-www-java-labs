package iuh.fit.se;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "iuh.fit.se")
public class AnnotationConfiguration {
    // Configuration class để scan các annotation components
    // Không cần định nghĩa beans ở đây vì sẽ sử dụng @Component annotations
}
