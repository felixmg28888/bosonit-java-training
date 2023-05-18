package com.bosonit.formacion.block13testingcrud.persona.application;


import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(Integer id);

    List<PersonaOutputDto> getPersonasByName(String name);

    List<PersonaOutputDto> getAllPersonas();

    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto);

    String deletePersona(Integer id);
}
