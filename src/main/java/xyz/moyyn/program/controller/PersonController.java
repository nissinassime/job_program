package xyz.moyyn.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.moyyn.program.data.PersonDataRepository;
import xyz.moyyn.program.model.Person;
import xyz.moyyn.program.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personSvc;

    @Autowired
    private PersonDataRepository personRepo;

    @GetMapping
    public List<Person> findAllPersons() {
        return personSvc.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) {

            var person = personSvc.getPersonById(id);
            if (person.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(person.get(), HttpStatus.OK);
            }
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {

        var newPerson = personSvc.savePerson(person);
        if (newPerson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(newPerson.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> removePersonById(@PathVariable  Long id) {
       // TODO try and catch exceptions related to DB deletion
        try {
            personSvc.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
