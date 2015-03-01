/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Employee;
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
public class EmployeeBean extends AbstractBean<Employee> {

    /**
     * Empty constructor.
     */
    public EmployeeBean() {
        super(Employee.class);
    }

    /**
     * Find all employees from the database
     *
     * @return a list of employees
     */
    @Override
    public List<Employee> findAll() {
        return getEntityManager().createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    /**
     * Find an employee by his login
     *
     * @param login login of employee
     * @return an employee
     */
    public Employee findByLogin(String login) {
        TypedQuery<Employee> query = getEntityManager().createNamedQuery("Employee.findByLogin", Employee.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

}
