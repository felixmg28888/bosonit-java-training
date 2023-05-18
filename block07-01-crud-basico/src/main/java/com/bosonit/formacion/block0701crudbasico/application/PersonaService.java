package com.bosonit.formacion.block0701crudbasico.application;

import com.bosonit.formacion.block0701crudbasico.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block0701crudbasico.controller.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {

    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto);

    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto);

    public String deletePersona(Integer id);

    public PersonaOutputDto getPersonaById(Integer id);

    public List<PersonaOutputDto> getPersonasByName(String nombre);

    public List<PersonaOutputDto> getAllPersonas();
}
