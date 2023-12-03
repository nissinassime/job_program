package xyz.moyyn.program.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

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
                logger.warn("Person not found with Id: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                logger.info("Person found: {}", person);
                return new ResponseEntity<>(person.get(), HttpStatus.OK);
            }
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {

        var newPerson = personSvc.savePerson(person);
        if (newPerson.isEmpty()) {
            logger.warn("Person save failed with RequestBody {}", person);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            logger.info("Person saved as {}", newPerson);
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
