package br.com.attornatus.service;

import br.com.attornatus.model.Address;
import br.com.attornatus.model.Person;
import br.com.attornatus.model.enums.AddressType;
import br.com.attornatus.repository.AddressRepository;
import br.com.attornatus.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
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


        //  --- Atualiza a pessoa
        updatedPerson.setName(person.getName());
        updatedPerson.setDateOfBirth(person.getDateOfBirth());
        personRepository.save(updatedPerson);

        //  --- Atualiza os endereços
        // Caso existam endereços para a pessoa e o endereço principal
        // tenha sido excluido um deles automaticamente se torna o principal.
        boolean hasMainAddress = person.getAddresses().stream()
                .anyMatch(address -> address.getType() == AddressType.MAIN_ADDRESS);
        if (person.getAddresses().size() > 0 && !hasMainAddress) {
            Address address = person.getAddresses().get(0);
            address.setType(AddressType.MAIN_ADDRESS);
            addressRepository.save(address);
        }

        // Exclui os endereços antigos e salva os novos (para caso alguma alterção tenha sido feita)
        updatedPerson.getAddresses().forEach(address -> addressRepository.deleteById(address.getId()));
        person.getAddresses().forEach(addressRepository::save);

    }

    public void addAddress(Long personId, Address address) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("No person with the id: " + personId));

        // Testa se o novo endereço é o endereço principal e caso
        // seja atualiza os outros endereços
        if (address.getType() == AddressType.MAIN_ADDRESS) {
            person.getAddresses()
                    .forEach(currentAddress -> {
                        currentAddress.setType(AddressType.SECONDARY_ADDRESS);
                        addressRepository.save(currentAddress);
                    });
        }

        address.setPerson(person);
        addressRepository.save(address);
    }

    public List<Address> addressesByPerson(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("No person with the id: " + personId));

        return person.getAddresses();
    }

    public void updateAddress(Long personId, Address address) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("No person with the id: " + personId));

        if (!addressRepository.existsById(address.getId())) {
           throw  new IllegalArgumentException("No address with the id: " + address.getId());
        }

        if (person.getAddresses().size() == 1) {

            // Se existe apenas um endereço ele deve ser o principal
            address.setType(AddressType.MAIN_ADDRESS);

        } else if (address.getType() == AddressType.MAIN_ADDRESS) {

            // Se o endereço atualizado é o novo endereço principal
            // todos os outros se tornam secundários

            person.getAddresses().forEach(currentAddress -> {
                currentAddress.setType(AddressType.SECONDARY_ADDRESS);
                addressRepository.save(currentAddress);
            });

        } else {

            // Se o endereço atualizado era o principal e agora não é
            // mais principal eu escolho um novo principal
            List<Address> personAddresses = person.getAddresses().stream()
                    .filter(personAddress -> !Objects.equals(personAddress.getId(), address.getId()))
                    .toList();

            boolean hasMainAddress = personAddresses.stream()
                    .anyMatch(personAddress -> personAddress.getType() == AddressType.MAIN_ADDRESS);

            if (address.getType() != AddressType.MAIN_ADDRESS && !hasMainAddress) {
                Address a = personAddresses.get(0);
                a.setType(AddressType.MAIN_ADDRESS);
                addressRepository.save(a);
            }
        }

        address.setPerson(person);
        addressRepository.save(address);
    }
}
