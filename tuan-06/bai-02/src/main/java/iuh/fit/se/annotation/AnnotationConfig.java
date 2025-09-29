package iuh.fit.se.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "iuh.fit.se.annotation")
public class AnnotationConfig {
    // Configuration class để scan các annotation components trong package annotation
}
