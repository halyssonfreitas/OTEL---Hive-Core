package br.com.radon.hive_core.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.radon.hive_core.models.Person;
import br.com.radon.hive_core.services.PeopleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
  private final PeopleService peopleService;
  
  @Value("${api.version}")
  private String apiVervion;
  
  @GetMapping("/version")
  public String getApiVervion() {
    return apiVervion;
  }

  @GetMapping("/generate/{quantity}")
  public String generatePeople(@PathVariable("quantity") Integer quantity) {
    peopleService.generatePeople(quantity);
    return String.format("Created %d people", quantity);
  }

  @GetMapping("/")
  public ResponseEntity<List<Person>> getPeople() {
    return ResponseEntity.ok(peopleService.getPeople());
  }

  @PostMapping()
  public ResponseEntity<Person> createPerson(@RequestBody  Person person) {
    return ResponseEntity.ok(peopleService.createPerson(person));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable("id") UUID id) {
    peopleService.deletePerson(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable("id") UUID id, @RequestBody Person person) {
    return ResponseEntity.ok(peopleService.updatePerson(id, person));
  }

  @GetMapping("/by-name/{name}")
  public ResponseEntity<List<Person>> getPersonByName(@PathVariable("name") String name) {
    return ResponseEntity.ok(peopleService.getPersonByName(name));
  }
}
