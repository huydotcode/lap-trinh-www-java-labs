package iuh.fit.se.bai01.setter_injection;

public class Student {
    private long id;
    private String name;
    private iuh.fit.se.bai01.setter_injection.Class clazz;

    public Student() {
    }

    public Student(long id, String name, iuh.fit.se.bai01.setter_injection.Class clazz) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public iuh.fit.se.bai01.setter_injection.Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
