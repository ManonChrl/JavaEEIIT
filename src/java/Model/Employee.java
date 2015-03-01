/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manon
 */
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByLogin", query = "SELECT e FROM Employee e WHERE e.user.login = :login"),
    @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName"),
    @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByPhoneNo", query = "SELECT e FROM Employee e WHERE e.phoneNo = :phoneNo"),
    @NamedQuery(name = "Employee.findByEmployeeID", query = "SELECT e FROM Employee e WHERE e.employeeID = :employeeID")})

@Entity
@Table(name = "employee")
public class Employee extends Users implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "employeeID", unique = true)
    private String employeeID;

    @OneToOne
    @JoinColumn(name = "login")
    private User user;

    @ManyToMany
    private List<Customer> customers = new ArrayList<Customer>();

    /**
     * Empty constructor for employee.
     *
     */
    public Employee() {

    }

    /**
     * Full constructor for employee
     *
     * @param employeeID employee ID
     * @param firstName employee first name
     * @param lastName employee last name
     * @param email employee email
     * @param phoneNo employee phone number
     */
    public Employee(String employeeID, String firstName, String lastName, String email, String phoneNo) {
        super(firstName, lastName, email, phoneNo);
        this.employeeID = employeeID;
    }

    /**
     * Get the value of employeeID
     *
     * @return the value of employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Set the value of employeeID
     *
     * @param employeeID new value of employeeID
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Get the list of customers
     *
     * @return the list of customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Set the list of customers
     *
     * @param customers new list of customers
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Get the user
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Employee");
        sb.append("{Employee ID='").append(employeeID).append('\'');
//        sb.append(", login='").append(this.getLogin()).append('\'');
//        sb.append(", password='").append(this.getPassword()).append('\'');
        sb.append(", first name='").append(this.getFirstName()).append('\'');
        sb.append(", last name='").append(this.getLastName()).append('\'');
        sb.append(", email='").append(this.getEmail()).append('\'');
        sb.append(", phone number='").append(this.getPhoneNo()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
