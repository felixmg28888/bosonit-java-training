package com.bosonit.formacion.block070201crudvalidation.persona.application;

import com.bosonit.formacion.block070201crudvalidation.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block070201crudvalidation.persona.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;

    PersonaOutputDto getPersonaById(Integer id) throws Exception;

    List<PersonaOutputDto> getPersonasByName(String name) throws Exception;

    List<PersonaOutputDto> getAllPersonas();

    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception;

    String deletePersona(Integer id) throws Exception;
}
