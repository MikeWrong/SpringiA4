package me.caiyuan.spring.web.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * YUAN
 * 7/13/16.
 */
public class Spitter {

    @NotNull
    @Size(min = 5, max = 12, message = "First Name 值的长度在 5 到 12 之间")
    private String firstName;
    @NotNull
    @Size(min = 5, max = 12, message = "{spitter.lastName}")
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
