package iuh.fit.se.java;

public class EmployeeBean {
    
    private int id;
    private String name;
    private AddressBean address;

    public EmployeeBean() {
        System.out.println("EmployeeBean được tạo với Java-Based Configuration");
    }

    public EmployeeBean(int id, String name, AddressBean address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
