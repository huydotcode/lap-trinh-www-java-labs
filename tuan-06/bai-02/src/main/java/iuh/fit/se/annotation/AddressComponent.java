package iuh.fit.se.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AddressComponent {
    
    @Value("Ho Chi Minh")
    private String city;
    
    @Value("Ho Chi Minh")
    private String state;
    
    @Value("Vietnam")
    private String country;

    public AddressComponent() {
        System.out.println("AddressComponent được tạo với Annotation Configuration");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AddressComponent{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
