package iuh.fit.se.bai01.java_based_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServices {

    @Bean
    public Group groupService() {
        return new Group("Group 1");
    }

    @Bean
    public User userService() {
        return new User("huy", "123", groupService());
    }

}
