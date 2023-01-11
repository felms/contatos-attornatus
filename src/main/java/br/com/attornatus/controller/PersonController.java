package br.com.attornatus.controller;

import br.com.attornatus.model.Address;
import br.com.attornatus.model.Person;
import br.com.attornatus.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> fetchAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Person fetchById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable Long id, @RequestBody Person person) {
        personService.updatePerson(id, person);
    }

    @PostMapping("/{id}/address")
    public void addAddress(@PathVariable Long id, @RequestBody Address address) {
        personService.addAddress(id, address);
    }
}
