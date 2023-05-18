package com.bosonit.formacion.block13testingcrud.persona.application;

import com.bosonit.formacion.block13testingcrud.exception.EntityNotFoundException;
import com.bosonit.formacion.block13testingcrud.exception.UnprocessableEntityException;
import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaInputDto;
import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaOutputDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonaServiceImplTest {
    @Autowired
    private PersonaServiceImpl personaServiceImpl;
    private PersonaInputDto personaInputDto, personaInputDto2;
    private PersonaOutputDto personaOutputDto;
    private UnprocessableEntityException unprocessableEntityException;


    @BeforeAll
    void setUp() {
        personaInputDto = new PersonaInputDto();

        personaInputDto.setUsuario("Persona1");
        personaInputDto.setPassword("PassPersona1");
        personaInputDto.setName("Persona1");
        personaInputDto.setSurname("Persona1");
        personaInputDto.setCompanyEmail("Persona1@companyEmail.com");
        personaInputDto.setPersonalEmail("Persona1@personalEmaial");
        personaInputDto.setCity("Persona1 City");
        personaInputDto.setActive(true);
        personaInputDto.setImagenUrl("imagenPesona1");
    }


    @Test
    @Order(1)
    void addPersona() {
        personaOutputDto = personaServiceImpl.addPersona(personaInputDto);

        assertEquals("Persona1", personaOutputDto.getName());
        assertEquals("Persona1 City", personaOutputDto.getCity());
    }

    @Test
    @Order(2)
    void testPersonaInputDto() {
        personaInputDto2 = new PersonaInputDto();

        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Usuario no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setUsuario("Pe");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n"
                + " Mínimo 3 caracteres.", unprocessableEntityException.getMessage());
        personaInputDto2.setUsuario("Persona2Persona2Persona2");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Máximo 10 caracteres.", unprocessableEntityException.getMessage());
        personaInputDto2.setUsuario("Persona2");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Password no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setPassword("PassPersona2");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Name no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setName("Persona2");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Company_email no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setCompanyEmail("Persona2@CompanyEmail.com");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Personal_email no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setPersonalEmail("Persona2@personaEmail.com");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " City no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setCity("Persona2 City");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto2));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                " Active no puede ser nulo. ", unprocessableEntityException.getMessage());
        personaInputDto2.setActive(true);
    }

    @Test
    @Order(3)
    void yaRegistradosTest() {
        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                "Usuario ya registrado", unprocessableEntityException.getMessage());
        personaInputDto.setUsuario("Repetido");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                "CompanyEmail ya registrado", unprocessableEntityException.getMessage());
        personaInputDto.setCompanyEmail("Repetido");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                "PersonalEmail ya registrado", unprocessableEntityException.getMessage());
        personaInputDto.setPersonalEmail("Repetido");


        unprocessableEntityException = assertThrows(UnprocessableEntityException.class,
                () -> personaServiceImpl.addPersona(personaInputDto));
        assertEquals("Los campos no cumplen con los requisitos establecidos. \n" +
                "ImagenUrl ya registrada", unprocessableEntityException.getMessage());
        personaInputDto.setImagenUrl("Repetido");
    }

    @Test
    @Order(4)
    void getPersonaById() {
        personaOutputDto = personaServiceImpl.getPersonaById(1);

        assertEquals("Persona1", personaOutputDto.getName());
        assertEquals("Persona1 City", personaOutputDto.getCity());


        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class,
                () -> personaServiceImpl.getPersonaById(2));
        assertEquals("Usuario no encontrado", entityNotFoundException.getMessage());
    }

    @Test
    @Order(5)
    void getPersonasByName() {
        List<PersonaOutputDto> personaOutputDtoList = personaServiceImpl.getPersonasByName("Persona1");

        assertNotNull(personaOutputDtoList);
        assertEquals(1, personaOutputDtoList.size());

    }


    @Test()
    @Order(6)
    void getAllPersonas() {
        List<PersonaOutputDto> personaOutputDtoList = personaServiceImpl.getPersonasByName("Persona1");

        assertNotNull(personaOutputDtoList);
        assertEquals(1, personaOutputDtoList.size());
    }

    @Test
    @Order(7)
    void updatePersona() {
        personaInputDto.setName("Actualizado");
        personaOutputDto= personaServiceImpl.updatePersona(1, personaInputDto);


        assertEquals("Actualizado", personaOutputDto.getName());

    }

    @Test
    @Order(8)
    void deletePersona() {
        personaServiceImpl.deletePersona(1);

        assertTrue(personaServiceImpl.getAllPersonas().isEmpty());
    }
}