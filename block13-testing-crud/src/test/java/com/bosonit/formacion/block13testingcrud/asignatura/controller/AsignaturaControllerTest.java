package com.bosonit.formacion.block13testingcrud.asignatura.controller;

import com.bosonit.formacion.block13testingcrud.asignatura.domain.Asignatura;
import com.bosonit.formacion.block13testingcrud.asignatura.repository.AsignaturaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AsignaturaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    Asignatura asignatura;

    ObjectMapper mapper = new ObjectMapper();


    @BeforeAll
    void setUp() throws JsonProcessingException {
        asignatura=new Asignatura();
        asignatura.setIdAsignatura(1);
        asignatura.setNombreAsignatura("Asignatura");
        asignatura.setComments("Comentario Asignatura1");
        asignatura.setBranch("Branch Asignatura1");
        asignatura.setInitialDate(new Date());
    }

    @Test
    @Order(1)
    void addAsignatura() throws Exception {
        String asignaturaString = mapper.writeValueAsString(asignatura);
        this.mockMvc.perform(post("/asignatura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asignaturaString))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void getAsignaturaById() throws Exception {
        this.mockMvc.perform(get("/asignatura/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void getAllAsignaturas() throws Exception {
        this.mockMvc.perform(get("/asignatura")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @Order(4)
    void updateAsignatura() throws Exception {
        asignatura.setNombreAsignatura("Asignatura Actualizada");
        String asignaturaString = mapper.writeValueAsString(asignatura);
        this.mockMvc.perform(put("/asignatura/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asignaturaString))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void deleteAsignaturaById() throws Exception {
        this.mockMvc.perform(delete("/asignatura/1")).andDo(print()).andExpect(status().isOk());

    }
}