package com.bosonit.formacion.block10dockerizeapp.application;

import com.bosonit.formacion.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block10dockerizeapp.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block10dockerizeapp.domain.Persona;
import com.bosonit.formacion.block10dockerizeapp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) {
        Persona persona = new Persona();

        persona.setId(personaInputDto.getId());
        persona.setName(personaInputDto.getName());
        persona.setAge(personaInputDto.getAge());
        persona.setCity(personaInputDto.getCity());


        personaRepository.save(persona);

        return new PersonaOutputDto(persona);
    }

    @Override
    public PersonaOutputDto getPersonaById(Integer id) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow
                (() -> new Exception("Usuario no encontrado."));

        return new PersonaOutputDto(persona);
    }


    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();
        for (Persona p : personaRepository.findAll()) {
            personaOutputDtoList.add(new PersonaOutputDto(p));
        }
        return personaOutputDtoList;
    }

    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado."));

        if (personaInputDto.getName() != null) persona.setName(personaInputDto.getName());
        if (personaInputDto.getAge() != null) persona.setAge(personaInputDto.getAge());
        if (personaInputDto.getCity() != null) persona.setCity(personaInputDto.getCity());

        personaRepository.save(persona);

        return new PersonaOutputDto(persona);
    }

    @Override
    public String deletePersonaById(Integer id) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado."));
        personaRepository.delete(persona);
        return "La persona con id " + id + " ha sido borrad@.";
    }
}
