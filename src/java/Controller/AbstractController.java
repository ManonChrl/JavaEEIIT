/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Manon
 */
public class AbstractController {

    /**
     * Define a facesContext for every controller.
     */
    protected FacesContext facesContext;

    /**
     * Define a flash for every controller.
     */
    protected Flash flash;

    /**
     * Empty Constructor.
     */
    public AbstractController() {
    }

    /**
     * Instanciate the facesContext and the flash postConstruct.
     */
    @PostConstruct
    protected void postContruct() {
        facesContext = FacesContext.getCurrentInstance();
        flash = facesContext.getExternalContext().getFlash();
    }
}
