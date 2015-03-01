/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.security;

import EJB.AbstractBean;
import Model.security.Group;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manon
 */
@Stateless
public class GroupBean extends AbstractBean<Group> {

    /**
     * Empty Constructor.
     */
    public GroupBean() {
        super(Group.class);
    }

    /**
     * Find all groups
     *
     * @return a list of group
     */
    @Override
    public List<Group> findAll() {
        return getEntityManager().createNamedQuery("Group.findAll", Group.class).getResultList();
    }

    /**
     * Find a group by name
     *
     * @param name name of the group
     * @return a group
     */
    public Group findByName(String name) {
        TypedQuery<Group> query = getEntityManager().createNamedQuery("Group.findByName", Group.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

}
