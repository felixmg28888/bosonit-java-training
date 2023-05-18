package com.bosonit.formacion.block0701crudbasico.application;

import com.bosonit.formacion.block0701crudbasico.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block0701crudbasico.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block0701crudbasico.domain.Persona;
import com.bosonit.formacion.block0701crudbasico.repository.PersonaRepository;
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
        persona.setName(personaInputDto.getNombre());
        persona.setEdad(personaInputDto.getEdad());
        persona.setPoblacion(personaInputDto.getPoblacion());
        personaRepository.save(persona);
        return new PersonaOutputDto(persona);

    }

    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) {
        //Creo una persona que será igual a la que ya hay en el repositorio.
        Persona persona = personaRepository.findById(id).orElseThrow();

        //----------------------------------------------------------------------------
        //Setteo los diferentes campos de esa persona con los datos obtenidos del input.
        //Los que no se setean sergirían igual...
        //----------------------------------------------------------------------------
        if (personaInputDto.getNombre() != null) persona.setName(personaInputDto.getNombre());
        if (personaInputDto.getEdad() != null) persona.setEdad(personaInputDto.getEdad());
        if (personaInputDto.getPoblacion() != null) persona.setPoblacion(personaInputDto.getPoblacion());
        //----------------------------------------------------------------------------

        //Guardo los cambios
        personaRepository.save(persona);

        return new PersonaOutputDto(persona);
    }


    @Override
    public String deletePersona(Integer id) {
        Persona persona = personaRepository.findById(id).orElseThrow();
        personaRepository.delete(persona);
        return "La persona con id " + id + " ha sido borrad@.";
    }

    @Override
    public PersonaOutputDto getPersonaById(Integer id) {
        Persona persona = personaRepository.findById(id).orElseThrow();
        return new PersonaOutputDto(persona);
    }

    @Override
    public List<PersonaOutputDto> getPersonasByName(String nombre) {
        List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();
        for (Persona p : personaRepository.findByName(nombre).orElseThrow()) {
            personaOutputDtoList.add(new PersonaOutputDto(p));
        }
        return personaOutputDtoList;
    }

    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();
        for (Persona p : personaRepository.findAll()) {
            personaOutputDtoList.add(new PersonaOutputDto(p));
        }
        return personaOutputDtoList;

//-------------------------------------Opción 2----------------------------------------
//    @Override
//    public List<PersonaOutputDto> getAllPersonas() {
//        List<PersonaOutputDto> personaOutputDtoList= new ArrayList<>();
//
////        personaRepository.findAll().forEach(persona -> {
////            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
////            personaOutputDtoList.add(personaOutputDto);


//-------------------------------------Opción 3----------------------------------------
//    List<Persona> personas = personaRepository.findAll();
//        return personas.stream().map(PersonaOutputDto::new).toList();
//-------------------------------------------------------------------------------------    }
    }
}
