/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manon
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable {

    @NotNull(message = "Please enter your street")
    @Column(name = "street")
    private String street;

    @NotNull(message = "Please enter your city")
    @Size(max = 50, message = "The length max of the city is 50")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Please enter your state")
    @Size(max = 3, message = "The length max of the state is 3")
    @Column(name = "state")
    private String state;

    @NotNull(message = "Please enter your zip code")
    @Size(min = 5, max = 5, message = "Zip code should be 5 digits")
    @Column(name = "zipCode")
    private String zipCode;

    @NotNull(message = "Please enter your country")
    @Column(name = "country")
    private String country;

    /**
     * Empty constructor for Address.
     *
     */
    public Address() {

    }

    /**
     * Full constructor for Address
     *
     * @param street street of address
     * @param city city of address
     * @param state state of address
     * @param zipCode zipcode of address
     * @param country country of address
     */
    public Address(String street, String city, String state, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public String getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get the value of zipCode
     *
     * @return the value of zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Set the value of zipcode
     *
     * @param zipCode new value of zipcode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Address");
        sb.append("{Address street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zipcode='").append(zipCode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
