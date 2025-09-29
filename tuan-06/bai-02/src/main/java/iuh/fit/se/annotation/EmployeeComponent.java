package iuh.fit.se.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmployeeComponent {
    
    @Value("101")
    private int id;
    
    @Value("Nguyen Van Annotation")
    private String name;
    
    @Autowired
    private AddressComponent address;

    public EmployeeComponent() {
        System.out.println("EmployeeComponent được tạo với Annotation Configuration");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressComponent getAddress() {
        return address;
    }

    public void setAddress(AddressComponent address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployeeComponent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
