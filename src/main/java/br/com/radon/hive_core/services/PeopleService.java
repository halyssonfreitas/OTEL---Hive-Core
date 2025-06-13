package br.com.radon.hive_core.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.radon.hive_core.models.Person;

public interface PeopleService {
  List<Person> getPeople();
  Optional<Person> getPersonById(UUID id);
  Person createPerson(Person person);
  Person updatePerson(UUID id, Person person);
  void deletePerson(UUID id);
  List<Person> getPersonByName(String name);
  void generatePeople(Integer quantity);
}
