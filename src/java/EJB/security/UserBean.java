/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.security;

import EJB.AbstractBean;
import Model.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Manon
 */
@Stateless
public class UserBean extends AbstractBean<User> {

    /**
     * Empty Constructor.
     */
    public UserBean() {
        super(User.class);
    }

    /**
     * Find all users
     *
     * @return a list of user
     */
    @Override
    public List<User> findAll() {
        return getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
    }

}
