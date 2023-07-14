package com.bayzdelivery.dto;

/**
 * A response class that consists of the delivery response object returned to client.
 * @author Raed
 *
 */
public class PersonResponse {
    Long id;
    String name;
    String email;
    String registrationNumber;
    private String type;

    public PersonResponse (){}
    public PersonResponse(Long id, String name, String email, String registrationNumber, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
