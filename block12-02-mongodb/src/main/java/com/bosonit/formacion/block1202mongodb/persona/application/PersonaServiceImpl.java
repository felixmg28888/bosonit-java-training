package com.bosonit.formacion.block1202mongodb.persona.application;

import com.bosonit.formacion.block1202mongodb.exception.EntityNotFoundException;
import com.bosonit.formacion.block1202mongodb.exception.UnprocessableEntityException;
import com.bosonit.formacion.block1202mongodb.persona.controller.dto.personaInputDto.PersonaInputDto;
import com.bosonit.formacion.block1202mongodb.persona.controller.dto.personaOutputDto.PersonaOutputDto;
import com.bosonit.formacion.block1202mongodb.persona.domain.Persona;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    public void borrarRegistros() {
        mongoTemplate.dropCollection(Persona.class);
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) {
        //Instancio persona con el constructor vacío.
        Persona persona = new Persona();
        //Seteo los campos con los datos del inputDto
        persona.setUsuario(personaInputDto.getUsuario());
        persona.setPassword(personaInputDto.getPassword());
        persona.setName(personaInputDto.getName());
        persona.setSurname(personaInputDto.getSurname());
        persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        persona.setCity(personaInputDto.getCity());
        persona.setActive(personaInputDto.getActive());
        persona.setImagenUrl(personaInputDto.getImagenUrl());
        persona.setTerminationDate(personaInputDto.getTerminationDate());


        //Creo una lista de personas con todas las que hay en el repository
        //Con el bucle for valido si ya esxisten los campos unique.
        for (Persona p : mongoTemplate.findAll(Persona.class)) {
            if (p.getUsuario().equals(persona.getUsuario())) {
                throw new UnprocessableEntityException("Usuario ya registrado");
            }
            if (p.getCompanyEmail().equals(persona.getCompanyEmail())) {
                throw new UnprocessableEntityException("CompanyEmail ya registrado");
            }
            if (p.getPersonalEmail().equals(persona.getPersonalEmail())) {
                throw new UnprocessableEntityException("PersonalEmail ya registrado");
            }
            if (p.getImagenUrl().equals(persona.getImagenUrl())) {
                throw new UnprocessableEntityException("ImagenUrl ya registrada");
            }
        }

        //Control de excepciones del json del inputDto
        if (personaInputDto.getUsuario() == null) {
            throw new UnprocessableEntityException(" Usuario no puede ser nulo. ");
        }
        if (personaInputDto.getUsuario().length() < 3) {
            throw new UnprocessableEntityException(" Mínimo 3 caracteres.");
        }
        if (personaInputDto.getUsuario().length() > 10) {
            throw new UnprocessableEntityException(" Máximo 10 caracteres.");
        }
        if (personaInputDto.getPassword() == null) {
            throw new UnprocessableEntityException(" Password no puede ser nulo. ");
        }
        if (personaInputDto.getName() == null) {
            throw new UnprocessableEntityException(" Name no puede ser nulo. ");
        }
        if (personaInputDto.getCompanyEmail() == null) {
            throw new UnprocessableEntityException(" Company_email no puede ser nulo. ");
        }
        if (personaInputDto.getPersonalEmail() == null) {
            throw new UnprocessableEntityException(" Personal_email no puede ser nulo. ");
        }
        if (personaInputDto.getCity() == null) {
            throw new UnprocessableEntityException(" City no puede ser nulo. ");
        }
        if (personaInputDto.getActive() == null) {
            throw new UnprocessableEntityException(" Active no puede ser nulo. ");
        }

        mongoTemplate.save(persona);

        return new PersonaOutputDto(persona);
    }


    @Override
    public PersonaOutputDto getPersonaById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Persona persona = mongoTemplate.findById(id, Persona.class);
            if (persona == null) {
                throw new EntityNotFoundException("Usuario no encontrado.");
            }
            return new PersonaOutputDto(persona);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Usuario no encontrado.");
        }
    }

    @Override
    public List<PersonaOutputDto> getAllPersonas(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);

        //La query es necesaria debido al uso de MongoTemplate, no acepta pageable por parámetro.
        Query query = new Query().with(pageable);
        //Pageable por parámetro si la aceptaria personaRepository que hubiera heredado de MongoRepository

        List<Persona> personas = mongoTemplate.find(query, Persona.class);

        List<PersonaOutputDto> personaOutputDtoList = personas.stream()
                .map(persona -> new PersonaOutputDto(persona))
                .collect(Collectors.toList());
        return personaOutputDtoList;
    }

    @Override
    public PersonaOutputDto updatePersona(String id, PersonaInputDto personaInputDto) {

        //Creo una persona que será igual a la que ya hay en el repositorio.

        try {
            ObjectId objectId = new ObjectId(id);
            Persona persona = mongoTemplate.findById(id, Persona.class);
            if (persona == null) {
                throw new EntityNotFoundException("Usuario no encontrado.");
            }
            //----------------------------------------------------------------------------

            //Setteo los diferentes campos de esa persona desde el input.
            //Los que no se setean siguen igual... con lo que el id y fechaCreación seguirían igual.
            //Si pasamos un json con menos campos esos campos, en el input van a nulo, y saltaría la excepción.
            //Con estos if si van a nulos no settean, dejando los valores anteriores que ya están controlados en el add.
            //----------------------------------------------------------------------------
            if (personaInputDto.getUsuario() != null) persona.setUsuario(personaInputDto.getUsuario());
            if (personaInputDto.getPassword() != null) persona.setPassword(personaInputDto.getPassword());
            if (personaInputDto.getName() != null) persona.setName(personaInputDto.getName());
            if (personaInputDto.getSurname() != null) persona.setSurname(personaInputDto.getSurname());
            if (personaInputDto.getCompanyEmail() != null)
                persona.setCompanyEmail(personaInputDto.getCompanyEmail());
            if (personaInputDto.getPersonalEmail() != null)
                persona.setPersonalEmail(personaInputDto.getPersonalEmail());
            if (personaInputDto.getCity() != null) persona.setCity(personaInputDto.getCity());
            if (personaInputDto.getActive() != null) persona.setActive(personaInputDto.getActive());
            if (personaInputDto.getImagenUrl() != null) persona.setImagenUrl(personaInputDto.getImagenUrl());
            if (personaInputDto.getTerminationDate() != null)
                persona.setTerminationDate(personaInputDto.getTerminationDate());
            //----------------------------------------------------------------------------

            //Guardo los cambios
            mongoTemplate.save(persona);

            return new PersonaOutputDto(persona);
        } catch (Exception ex) {
            throw new EntityNotFoundException("Usuario no encontrado.");
        }
    }

    @Override
    public String removePersonaById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            Persona persona = mongoTemplate.findById(id, Persona.class);
            if (persona == null) {
                throw new EntityNotFoundException("Usuario no encontrado.");
            }
            mongoTemplate.remove(persona);
            return "La persona con id " + id + " ha sido borrad@.";
        } catch (Exception ex) {
            throw new EntityNotFoundException("Usuario no encontrado.");
        }
    }
}
