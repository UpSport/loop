package upsport.loop.rest.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

public class User {

    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @SafeHtml
    @NotBlank
    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @SafeHtml
    @NotBlank
    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @SafeHtml
    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

}
