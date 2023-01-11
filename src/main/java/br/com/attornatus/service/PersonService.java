package br.com.attornatus.service;

import br.com.attornatus.model.Person;
import br.com.attornatus.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No user with the id: " + id));
    }
}
