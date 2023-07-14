package com.bayzdelivery.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.bayzdelivery.dto.DtoMapper;
import com.bayzdelivery.dto.PersonRequest;
import com.bayzdelivery.dto.PersonResponse;
import com.bayzdelivery.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bayzdelivery.service.PersonService;

/**
 * Person Controller class which will be exposed.
 * @author Raed
 *
 */
@RestController
@RequestMapping("/people")
public class PersonController {

  @Autowired
  PersonService personService;

  @PostMapping
  public ResponseEntity<PersonResponse> register(@RequestBody PersonRequest personRequest) {
      Person person = personService.save(DtoMapper.toPerson(personRequest));
      return new ResponseEntity<>(DtoMapper.toPersonResponse(person), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PersonResponse>> getAllPeople() {
    List<Person> personList = personService.getAll();
    List<PersonResponse> personResponseList = personList.stream().map(DtoMapper::toPersonResponse).collect(Collectors.toList());
    return ResponseEntity.ok(personResponseList);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<PersonResponse> getPersonById(@PathVariable(name="id", required=true)Long personId) {
    Person person = personService.findById(personId);
    if (person != null) {
      return ResponseEntity.ok(DtoMapper.toPersonResponse(person));
    }
    return ResponseEntity.notFound().build();
  }

}
