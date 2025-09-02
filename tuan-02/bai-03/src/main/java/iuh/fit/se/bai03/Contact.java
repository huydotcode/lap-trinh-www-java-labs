package iuh.fit.se.bai03;

import java.sql.Blob;

public class Contact {
    private int contactId;
    private String firstName;
    private String lastName;
    private Blob photo;

    public Contact(int contactId, String firstName, String lastName, Blob photo) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public Contact() {
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
}
