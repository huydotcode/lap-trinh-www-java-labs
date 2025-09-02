package iuh.fit.se.bai03.dao;

import iuh.fit.se.bai03.Contact;
import iuh.fit.se.bai03.service.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDAO {
    private static final String INSERT_SQL = "INSERT INTO contacts (first_name, last_name, photo) VALUES (?, ?, ?)";
    private static final String QUERY_SQL = "SELECT * FROM contacts WHERE first_name = ? AND last_name = ?";

    public Contact findByFirstNameAndLastName(String firstName, String lastName) throws SQLException {
        Contact contact = null;

        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(QUERY_SQL);
                ) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contact = new Contact();
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contact;
    }

    public boolean insertContact(Contact contact) {
        try (
                Connection connection = Database.getConnection();
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
        ) {
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setBlob(3, contact.getPhoto());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
