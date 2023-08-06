package exercise.controller;

import exercise.model.Person;
import exercise.dto.PersonDto;
import exercise.repository.PersonRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    // Привязываем параметр метода к телу запроса
    public void createCompany(@RequestBody Person person) {
        // добавляем новую сущность в базу
        this.personRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    // Привязываем параметр метода к значению плейсхолдера
    public void deletePerson(@PathVariable long id) {
        // удаляем сущность из базы по её id
        this.personRepository.deleteById(id);
    }

    @PatchMapping(path = "/{id}")
    // Привязываем параметр метода к телу запроса
    public void updatePerson(@RequestBody Person person, @PathVariable long id)  {
        person.setId(id);
        this.personRepository.save(person);
    }


    // END
}
