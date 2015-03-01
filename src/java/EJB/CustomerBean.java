/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manon
 */
@Named
@Stateless
public class CustomerBean extends AbstractBean<Customer> {

    /**
     * Empty Constructor.
     */
    public CustomerBean() {
        super(Customer.class);
    }

    /**
     * Find all customers from the database
     *
     * @return a list of customers
     */
    @Override
    public List<Customer> findAll() {
        return getEntityManager().createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    /**
     * Find a customer with his login
     *
     * @param login login of customer
     * @return a customer
     */
    public Customer findByLogin(String login) {
        TypedQuery<Customer> query = getEntityManager().createNamedQuery("Customer.findByLogin", Customer.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

}
