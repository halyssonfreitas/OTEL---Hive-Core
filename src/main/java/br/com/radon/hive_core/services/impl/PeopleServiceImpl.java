package br.com.radon.hive_core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import br.com.radon.hive_core.models.Person;
import br.com.radon.hive_core.services.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService {

  private final List<Person> listOfPeople = new ArrayList<Person>();
  private final Faker faker = new Faker();

  @Override
  public List<Person> getPeople() {
    return listOfPeople;
  }

  @Override
  public Optional<Person> getPersonById(UUID id) {
    return listOfPeople.stream().filter(person -> person.getId().equals(id)).findFirst();
  }

  @Override
  public Person createPerson(Person person) {
    person.setId(UUID.randomUUID());
    listOfPeople.add(person);
    return person;
  }

  @Override
  public Person updatePerson(UUID id, Person person) {
    Person personToUpdate = getPersonById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
    personToUpdate.setName(person.getName());
    personToUpdate.setEndname(person.getEndname());
    personToUpdate.setEmail(person.getEmail());
    personToUpdate.setBirthDate(person.getBirthDate());
    return personToUpdate;
  }

  @Override
  public void deletePerson(UUID id) {
    Person person = getPersonById(id).orElseThrow(() -> new NoSuchElementException("Person not found"));
    listOfPeople.remove(person);
  }

  @Override
  public List<Person> getPersonByName(String name) {
    return listOfPeople.stream().filter(person -> person.getName().toLowerCase().equals(name.toLowerCase())).toList();
  }

  @Override
  public void generatePeople(Integer quantity) {
    if (quantity<=0) return;
    listOfPeople.clear();
    for (int i = 0; i < quantity; i++) {
      Person person = new Person();
      person.setId(UUID.randomUUID());
      person.setName(faker.name().firstName());
      person.setEndname(faker.name().lastName());
      person.setEmail(faker.internet().emailAddress());
      person.setBirthDate(faker.date().birthday());
      listOfPeople.add(person);
    };
  }
  
}
