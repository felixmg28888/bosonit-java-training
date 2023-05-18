//package com.bosonit.formacion.block13testingcrud.profesor.controller;
//
//import com.bosonit.formacion.block13testingcrud.persona.domain.Persona;
//import com.bosonit.formacion.block13testingcrud.persona.repository.PersonaRepository;
//import com.bosonit.formacion.block13testingcrud.profesor.domain.Profesor;
//import com.bosonit.formacion.block13testingcrud.profesor.repository.ProfesorRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ProfesorControllerTest_ {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    PersonaRepository personaRepository;
//    @Autowired
//    private ProfesorRepository profesorRepository;
//
//
//    Profesor profesor;
//    ObjectMapper mapper = new ObjectMapper();
//
//    @BeforeAll
//    void setUp() throws JsonProcessingException {
//        Persona persona = new Persona();
//        persona.setIdPersona(1);
//        persona.setUsuario("Persona1");
//        persona.setPassword("PassPersona1");
//        persona.setName("Persona1");
//        persona.setSurname("Persona1");
//        persona.setCompanyEmail("Persona1@companyEmail.com");
//        persona.setPersonalEmail("Persona1@personalEmaial");
//        persona.setCity("CitiyPersona1");
//        persona.setActive(true);
//        persona.setImagenUrl("imagenPesona1");
//
//        personaRepository.save(persona);
//
//        profesor = new Profesor();
//        profesor.setIdProfesor(1);
//        profesor.setComments("Profesor1 Comments");
//        profesor.setBranch("Profesor1 Branch");
//        profesor.setPersona(persona);
//
//        profesorRepository.save(profesor);
//
//
//
//
//
//
//
//
//
//    }
//
////    @Test
////    @Order(1)
////    void addProfesor() throws Exception {
////        String profesorString = mapper.writeValueAsString(profesor);
////        this.mockMvc.perform(post("/profesor")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(profesorString))
////                .andDo(print())
////                .andExpect(status().isCreated());
////
////    }
//
//    @Test
//    @Order(1)
//    void assignStudentToProfesor() throws Exception {
//
//    }
//
//    @Test
//    @Order(2)
//    void getProfesorById() throws Exception {
//        this.mockMvc.perform(get("/profesor/1")).andDo(print()).andExpect(status().isOk());
//    }
//
//    @Test
//    @Order(3)
//    void getAllProfesores() throws Exception {
//        this.mockMvc.perform(get("/profesor")).andDo(print()).andExpect(status().isOk());
//    }
//
//    @Test
//    @Order(4)
//    void updateProfesor() throws Exception {
//        profesor.setComments("Profesor Actualizada");
//        String asignaturaString = mapper.writeValueAsString(profesor);
//        this.mockMvc.perform(put("/profesor/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asignaturaString))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @Order(5)
//    void deleteProfesorById() throws Exception {
//        this.mockMvc.perform(delete("/profesor/1")).andDo(print()).andExpect(status().isOk());
//    }
//}