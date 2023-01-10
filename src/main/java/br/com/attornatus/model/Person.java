package br.com.attornatus.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Person {

    @Id
    private Long id;
    private String name;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "person")
    @JsonManagedReference
    private List<Address> addresses;

    public Person() {
    }

    public Person(Long id, String name, LocalDate dateOfBirth, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.addresses = addresses;
    }

    public void addAddress(Address newAddress) {
        this.addresses.add(newAddress);
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
