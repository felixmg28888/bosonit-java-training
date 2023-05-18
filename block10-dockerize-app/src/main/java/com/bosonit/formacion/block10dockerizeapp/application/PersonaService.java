package com.bosonit.formacion.block10dockerizeapp.application;

import com.bosonit.formacion.block10dockerizeapp.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block10dockerizeapp.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto personaInputDto);
    PersonaOutputDto getPersonaById(Integer id) throws Exception;
    List<PersonaOutputDto> getAllPersonas();
    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception;
    String deletePersonaById(Integer id) throws Exception;
}
