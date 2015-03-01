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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Manon
 */
@Entity
@Table(name = "secGroup")
@NamedQueries({
    @NamedQuery(name = "Group.findAll", query = "select g from Group g"),
    @NamedQuery(name = "Group.findByName", query = "select g from Group g where g.groupName=:name")
})

public class Group implements Serializable {

    @Id
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "groupDesc")
    private String groupDesc;

    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    /**
     * Empty Constructor.
     */
    public Group() {
    }

    /**
     * Full Constructor for Group
     *
     * @param groupName name of the group
     * @param groupDesc description of the group
     */
    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    /**
     * Add a user in the group
     *
     * @param u the user
     */
    public void addUser(User u) {
        if (!this.users.contains(u)) {
            this.users.add(u);
        }
        if (!u.getGroups().contains(this)) {
            u.getGroups().add(this);
        }
    }

    /**
     * Get the value of groupName
     *
     * @return the value of groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param groupName new value of groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Get the value of groupDesc
     *
     * @return the value of groupDesc
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * Set the value of groupDesc
     *
     * @param groupDesc new value of groupDesc
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    /**
     * Get the list of user
     *
     * @return users the list of user
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set the list of users
     *
     * @param users the list of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
