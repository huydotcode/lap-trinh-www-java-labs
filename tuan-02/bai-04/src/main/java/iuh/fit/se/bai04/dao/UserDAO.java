package iuh.fit.se.bai04.dao;

import iuh.fit.se.bai04.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserDAO {

    public User getUser() {
        return new User("USER01", "Ngo Nhut Huy", 22, "ngohuy@gmail.com");
    }
}
