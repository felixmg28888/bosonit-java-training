package com.bosonit.formacion.block1202mongodb.persona.application;

import com.bosonit.formacion.block1202mongodb.persona.controller.dto.personaInputDto.PersonaInputDto;
import com.bosonit.formacion.block1202mongodb.persona.controller.dto.personaOutputDto.PersonaOutputDto;
import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(String id);

    List<PersonaOutputDto> getAllPersonas(Integer page, Integer size);

    PersonaOutputDto updatePersona(String id, PersonaInputDto personaInputDto);

    String removePersonaById(String id);

}
