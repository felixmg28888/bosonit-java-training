package com.bosonit.formacion.block1201criteriabuilder.persona.application;


import com.bosonit.formacion.block1201criteriabuilder.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block1201criteriabuilder.persona.controller.dto.PersonaOutputDto;

import java.util.Date;
import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(Integer id);

    List<PersonaOutputDto> getPersonasByName(String name);

    List<PersonaOutputDto> getAllPersonas();

    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto);

    String deletePersona(Integer id);

    //----------------------------CRITERIA BUILDER-----------------------------------------------------------
    List<PersonaOutputDto> getPersonas(String usuario, String name, String surname, Date createdDate, String dateCondition, String orderBy, Integer page, Integer size);
}
