package xyz.moyyn.program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.moyyn.program.data.PersonDataRepository;
import xyz.moyyn.program.model.Person;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonDataRepository personRepo;

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepo.findById(id);
    }

    public Optional<Person> savePerson(Person person) {
        return Optional.ofNullable(personRepo.save(person));
    }

    public void deletePersonById(Long id) {
        personRepo.deleteById(id);
    }
}
