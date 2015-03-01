/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.EmployeeBean;
import EJB.security.UserBean;
import Model.Employee;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Manon
 */
@Named
@RequestScoped
public class EmployeeController extends AbstractController {

    @EJB
    private UserBean userBean;
    @EJB
    private EmployeeBean employeeBean;

    private Employee employee;

    /**
     * Empty constructor that instanciate a new employee.
     */
    public EmployeeController() {
        employee = new Employee();
    }

    /**
     * Get the employee
     *
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the employee
     *
     * @param employee new value of employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Watch the passed parameter in the url
     *
     * @return the passed parameter in the url with name custLogin
     */
    public String getPassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("custLogin");
    }

    /**
     * Edit info of an employee
     *
     * @return the page to see the info of the employee
     */
    public String editInfo() {
        Employee e = employeeBean.find(employee.getEmployeeID());
        e.setUser(userBean.find(facesContext.getExternalContext().getRemoteUser()));
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setEmail(employee.getEmail());
        e.setPhoneNo(employee.getPhoneNo());
        employeeBean.update(e);
        return "infosEmp.xhtml";
    }

}
