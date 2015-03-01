 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manon
 */
@Named
@RequestScoped
public class LoginController extends AbstractController {

    @NotNull(message = "Please enter your username")
    private String username;

    @Size(min = 6, message = "The password should be at least 6 characters")
    @NotNull(message = "Please enter your password")
    private String password;

    /**
     * Empty Constructor.
     */
    public LoginController() {
    }

    @PostConstruct
    private void postConstruct() {
        super.postContruct();
    }

    /**
     * Log in the user
     *
     * @return the welcome page according to the role of the user
     */
    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Login"));
            return "/login.xhtml";
        }
        if (req.isUserInRole("cust")) {
            return "/customer/index.xhtml";
        } else if (req.isUserInRole("emp")) {
            return "/employee/index.xhtml";
        } else {
            return "error404.xhtml";
        }
    }

    /**
     * Log out the user
     *
     * @return the logout page
     */
    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            req.logout();
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            facesContext.addMessage(null, new FacesMessage("Bad Logout", "Bad Logout"));
            return "/error.xhtml";
        }
        return "/logout.xhtml";
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
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
