package br.com.attornatus.model;

import br.com.attornatus.model.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

    @Id
    private Long id;
    private String street;
    private String zipcode;
    private Integer number;
    private String city;

    private String state;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;

    public Address() {
    }

    public Address(Long id, String street, String zipcode, Integer number, String city, String state, Person person) {
        this.id = id;
        this.street = street;
        this.zipcode = zipcode;
        this.number = number;
        this.city = city;
        this.state = state;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
