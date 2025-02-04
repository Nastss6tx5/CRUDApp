package crudapp.crudApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    public User() {
    }

    @Override
    public String toString() {
        return "User: Name: " + getFirstName()
                + " " + getLastName()
                + ". Age: " + getAge()
                + ". Email: " + getEmail();
    }
}
