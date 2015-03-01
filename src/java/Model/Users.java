/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Manon
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class Users {

    @NotNull(message = "Please enter your first name")
    @Column(name = "firstName")
    private String firstName;

    @NotNull(message = "Please enter your last name")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "You must provide us an email address")
    @Email
    @Column(name = "email")
    private String email;

    @Size(min = 0, max = 10, message = "The phone number should be 10 digits")
    @Column(name = "phoneNo")
    private String phoneNo;

    /**
     * Empty constructor for User.
     *
     */
    public Users() {
    }

    /**
     * Full Constructor for User
     *
     * @param firstName user first name
     * @param lastName user last name
     * @param email user email
     * @param phoneNo user phone number
     */
    public Users(String firstName, String lastName, String email, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    /**
     * Get the value of first name
     *
     * @return the value of first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of first name
     *
     * @param firstName new value of first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of last name
     *
     * @return the value of last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of last name
     *
     * @param lastName new value of last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of phone number
     *
     * @return the value of phone number
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Set the value of phone number
     *
     * @param phoneNo new value of phone number
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("User");
        sb.append(", first name='").append(firstName).append('\'');
        sb.append(", last name='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone number='").append(phoneNo).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
