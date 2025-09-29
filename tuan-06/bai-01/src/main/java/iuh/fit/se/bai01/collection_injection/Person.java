package iuh.fit.se.bai01.collection_injection;

import java.util.List;

public class Person {
    private String name;
    private List<String> emails;
    private int age;
    private List<Address> addresses;

    public Person() {
    }

    public Person(String name, List<String> emails, int age, List<Address> addresses) {
        this.name = name;
        this.emails = emails;
        this.age = age;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", emails=" + emails +
                ", age=" + age +
                ", addresses=" + addresses +
                '}';
    }
}
