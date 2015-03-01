/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Manon
 */
@Entity
@Table(name = "secUser")
@NamedQuery(name = "User.findAll", query = "select u from User u")

public class User implements Serializable {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "secUserGroup",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "groupname"))
    private List<Group> groups = new ArrayList<>();

    /**
     * Empty Constructor.
     */
    public User() {
    }

    /**
     * Full Constructor for User
     *
     * @param login user login
     * @param password user password
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Add a group to the user
     *
     * @param g the group
     */
    public void addGroup(Group g) {
        if (!this.groups.contains(g)) {
            this.groups.add(g);
        }
        if (!g.getUsers().contains(this)) {
            g.getUsers().add(this);
        }
    }

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the list of groups
     *
     * @return the list of groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the list of groups
     *
     * @param groups the list of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @PrePersist
    @PreUpdate
    private void hashPassword() {
        String digestPassword = DigestUtils.sha1Hex(this.password);
        this.password = digestPassword;
    }

}
