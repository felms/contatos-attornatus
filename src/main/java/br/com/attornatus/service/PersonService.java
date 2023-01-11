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
                .orElseThrow(() -> new IllegalArgumentException("No person with the id: " + id));
    }

    public void addPerson(Person person) {
        boolean exists = personRepository.existsById(person.getId());
        if (!exists) {
            personRepository.save(person);
        }
    }

    public void updatePerson(Long id, Person person) {
        Person updatedPerson = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No person with the id: " + id));

        updatedPerson.setName(person.getName());
        updatedPerson.setDateOfBirth(person.getDateOfBirth());
        updatedPerson.setAddresses(person.getAddresses());  // TODO: Após implementar o PUT dos
                                                            // endereços fazer o update aqui também
        personRepository.save(updatedPerson);
    }
}
