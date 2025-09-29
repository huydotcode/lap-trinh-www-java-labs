package iuh.fit.se.bai01;

import iuh.fit.se.bai01.collection_injection.Person;
import iuh.fit.se.bai01.setter_injection.Class;
import iuh.fit.se.bai01.setter_injection.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // Setter Injection – Object Injection
        applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Student student1 = applicationContext.getBean("student1", Student.class);

        System.out.println("Student: " + student1);
        System.out.println("Class" + student1.getClazz());

        Student student2 = applicationContext.getBean("student2", Student.class);

        System.out.println("Student: " + student2);
        System.out.println("Class" + student2.getClazz());

        //  Collection Injection
        Person person1 = applicationContext.getBean("person1", Person.class);
        System.out.println("Person: " + person1);
        System.out.println("Address");
        person1.getAddresses().forEach(System.out::println);
        System.out.println("Emails");
        person1.getEmails().forEach(System.out::println);
        System.out.println("Age: " + person1.getAge());

        // Literal Values Injection
        iuh.fit.se.bai01.setter_injection.Class classDHKTPM = applicationContext.getBean("class2", Class.class);
        System.out.println("Class: " + classDHKTPM);
    }
}
