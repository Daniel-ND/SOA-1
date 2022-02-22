package mapper;

import dto.PersonDTO;
import entity.Person;

public class PersonMapper {
    public static PersonDTO toDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setHeight(person.getHeight());
        personDTO.setName(person.getName());
        personDTO.setLocation(LocationMapper.toDTO(person.getLocation()));
        return personDTO;
    }

    public static Person toEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setHeight(personDTO.getHeight());
        person.setLocation(LocationMapper.toEntity(personDTO.getLocation()));
        person.setName(personDTO.getName());
        return person;
    }
}
