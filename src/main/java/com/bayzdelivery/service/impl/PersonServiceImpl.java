package com.bayzdelivery.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bayzdelivery.repositories.PersonRepository;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Person Service class that hold the person class business methods
 * @author Raed
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  PersonRepository personRepository;

  @Override
  public List<Person> getAll() {
    List<Person> personList = new ArrayList<>();
    personRepository.findAll().forEach(personList::add);
    return personList;
  }

  public Person save(Person person) {
    return personRepository.save(person);
  }

  @Override
  public Person findById(Long personId) {
    Optional<Person> dbPerson = personRepository.findById(personId);
    return dbPerson.orElse(null);
  }

}
