package com.bosonit.formacion.block13testingcrud.student.controller;

import com.bosonit.formacion.block13testingcrud.persona.domain.Persona;
import com.bosonit.formacion.block13testingcrud.persona.repository.PersonaRepository;
import com.bosonit.formacion.block13testingcrud.student.application.StudentServiceImpl;
import com.bosonit.formacion.block13testingcrud.student.controller.dto.studentinputodto.StudentInputDto;
import com.bosonit.formacion.block13testingcrud.student.controller.dto.studentoutputdto.StudentOutputDtoSimple;
import com.bosonit.formacion.block13testingcrud.student.domain.Student;
import com.bosonit.formacion.block13testingcrud.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentControllerTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    PersonaRepository personaRepository;
    @Mock
    StudentServiceImpl studentServiceImpl;

    @InjectMocks
    StudentController studentController;

    private StudentInputDto studentInputDto;
    private Student student;
    private StudentOutputDtoSimple studentOutputDtoSimple;

    private final List<Student> studentList = new ArrayList<>();
    private final List<StudentOutputDtoSimple> studentOutputDtoSimpleList = new ArrayList<>();



    @BeforeEach
    void setUp() {
        Persona persona = new Persona();
        persona.setIdPersona(1);
        persona.setUsuario("Persona1");
        persona.setPassword("Persona1 Password");
        persona.setName("Persona1");
        persona.setSurname("Persona1");
        persona.setCompanyEmail("Persona1@companyEmail.com");
        persona.setPersonalEmail("Perona1@personalEmail.com");
        persona.setCity("Persona1 City");
        persona.setActive(true);
        persona.setImagenUrl("Persona1.imagenUrl");
        persona.setCreatedDate(new Date());

        personaRepository.save(persona);

        student = new Student();
        student.setIdStudent(1);
        student.setNumHourWeek(10);
        student.setComments("Student1 Commments");
        student.setBranch("Student1 Branch");
        student.setPersona(persona);

        studentRepository.save(student);

        studentOutputDtoSimple= new StudentOutputDtoSimple(student);


        studentOutputDtoSimpleList.add(studentOutputDtoSimple);
        studentList.add(student);


    }

    @Test
    void addStudent() {
        //Given -
        when(studentRepository.save(student)).thenReturn(student);
        when(studentServiceImpl.addStudent(studentInputDto)).thenReturn(studentOutputDtoSimple);

        //When -
        ResponseEntity<StudentOutputDtoSimple> studentOutputDtoSimpleResponseEntity = studentController.addStudent(studentInputDto);

        //Then -
        assertEquals(HttpStatus.CREATED, studentOutputDtoSimpleResponseEntity.getStatusCode());
    }

    @Test
    void addStudentToAsignatura() {
        //Given -
        when(studentServiceImpl.assignAsignaturaToStudent(1, Collections.singletonList(1))).thenReturn("Asignatura añadida con éxito");

        //When -
        ResponseEntity<String> response = studentController.addStudentToAsignatura(1, Collections.singletonList(1));

        //Then -
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Asignatura añadida con éxito", response.getBody());
    }

    @Test
    void removeAsignaturasFromStudent() {
        //Given -
        when(studentServiceImpl.removeAsignaturasFromStudent(1, Collections.singletonList(1))).thenReturn("Asignatura borrada con éxito");

        //When -
        ResponseEntity<String> response = studentController.removeAsignaturasFromStudent(1, Collections.singletonList(1));

        //Then -
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Asignatura borrada con éxito", response.getBody());
    }

    @Test
    void getStudentById() {
        //Given -
        when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));
        when(studentServiceImpl.getStudentById(1, "studentOutputDtoSimple")).thenReturn(studentOutputDtoSimple);

        //When -
        ResponseEntity<StudentOutputDtoSimple> studentOutputDtoResponseEntity = studentController.getStudentById(1, "studentOutputDtoSimple");

        //Then -
        assertEquals(HttpStatus.OK, studentOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void getAllStudents() {
        //Given
        when(studentServiceImpl.getAllStudents()).thenReturn(studentOutputDtoSimpleList);

        //When -
        ResponseEntity<?> studentOutputDtoResponseEntity = studentController.getAllStudents();

        //Then -
        assertEquals(HttpStatus.OK, studentOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void updateStudent() {
        //Given -
        student.setComments("Comments Actualizado");
        when(studentServiceImpl.getAllStudents()).thenReturn(studentOutputDtoSimpleList);

        //When -
        ResponseEntity<StudentOutputDtoSimple> studentOutputDtoResponseEntity = studentController.updateStudent(1, studentInputDto);

        //Then -
        assertEquals(HttpStatus.OK, studentOutputDtoResponseEntity.getStatusCode());
    }

    @Test
    void deleteStudentById() {
        //Given -
        when(studentServiceImpl.deleteStudentById(1)).thenReturn("Student borrado con éxito");

        //When -
        ResponseEntity<String> studentOutputDtoResponseEntity = studentController.deleteStudentById(1);

        //Then -
        assertEquals(HttpStatus.OK, studentOutputDtoResponseEntity.getStatusCode());
        assertEquals("Student borrado con éxito", studentOutputDtoResponseEntity.getBody());
    }
}