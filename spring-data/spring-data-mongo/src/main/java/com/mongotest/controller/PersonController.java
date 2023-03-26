package com.mongotest.controller;

import com.mongotest.model.Person;
import com.mongotest.repo.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonRepo personRepo;
    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){

        try {
            Person savedPerson = personRepo.save(person);
            return new ResponseEntity<>(savedPerson,HttpStatus.CREATED);
        }catch (Exception ex){
        log.error("PersonController: addPerson() error occurred",ex);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id){

        Optional<Person> optionalPerson = personRepo.findById(id);
        if(optionalPerson.isPresent()){
            return new ResponseEntity<>(optionalPerson.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons(){
        try {
            List<Person> personList = personRepo.findAll();
            return new ResponseEntity<>(personList,HttpStatus.OK);
        }catch(Exception ex){
            log.error("PersonController: getPersons() error occurred",ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        try{
            Optional<Person> personOptional = personRepo.findById(person.getId());
            if(personOptional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            Person updatedPerson= personRepo.save(person);
            return  new ResponseEntity<>(updatedPerson,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
