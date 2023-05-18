package com.bosonit.formacion.block13testingcrud.student.application;

import com.bosonit.formacion.block13testingcrud.asignatura.repository.AsignaturaRepository;
import com.bosonit.formacion.block13testingcrud.persona.application.PersonaServiceImpl;
import com.bosonit.formacion.block13testingcrud.persona.controller.dto.PersonaOutputDto;
import com.bosonit.formacion.block13testingcrud.persona.domain.Persona;
import com.bosonit.formacion.block13testingcrud.persona.repository.PersonaRepository;
import com.bosonit.formacion.block13testingcrud.student.controller.dto.studentinputodto.StudentInputDto;
import com.bosonit.formacion.block13testingcrud.student.controller.dto.studentoutputdto.StudentOutputDtoSimple;
import com.bosonit.formacion.block13testingcrud.student.domain.Student;
import com.bosonit.formacion.block13testingcrud.student.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceImplTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;
    @Mock
    private AsignaturaRepository asignaturaRepository;

    private StudentInputDto studentInputDto;
    private Student student;
    private StudentOutputDtoSimple studentOutputDtoSimple;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private PersonaServiceImpl personaServiceImpl;
    private Persona persona;
    private PersonaOutputDto personaOutputDto;

    @BeforeEach
    void setUp() {
        persona = new Persona();
        persona.setIdPersona(1);
        persona.setUsuario("Persona1");
        persona.setPassword("PassPersona1");
        persona.setName("Persona1");
        persona.setSurname("Persona1");
        persona.setCompanyEmail("Persona1@companyEmail.com");
        persona.setPersonalEmail("Persona1@personalEmaial");
        persona.setCity("CitiyPersona1");
        persona.setActive(true);
        persona.setImagenUrl("imagenPesona1");
        personaRepository.save(persona);

        studentInputDto = new StudentInputDto();
        studentInputDto.setIdPersona(1);
        studentInputDto.setNumHourWeek(40);
        studentInputDto.setComments("Student1 Comments");
        studentInputDto.setBranch("Student1 Branch");

        student = new Student();
        student.setPersona(persona);
        student.setIdStudent(1);
        student.setNumHourWeek(studentInputDto.getNumHourWeek());
        student.setComments(studentInputDto.getComments());
        student.setBranch(studentInputDto.getBranch());

        persona.setStudent(student);

        personaRepository.save(persona);
        studentRepository.save(student);

        personaOutputDto = new PersonaOutputDto(persona);
    }

    @Test
    @Order(1)
    void addStudent() {
        //Given
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));


        //When
        studentOutputDtoSimple = studentServiceImpl.addStudent(studentInputDto);

        //Then
        assertEquals(studentOutputDtoSimple.getComments(), student.getComments());
        assertEquals(studentOutputDtoSimple.getBranch(), studentInputDto.getBranch());
    }


    @Test
    @Order(2)
    void getStudentById() {
        //Given
        when(studentRepository.findById(student.getIdStudent())).thenReturn(Optional.ofNullable(student));

        //When
        studentOutputDtoSimple = studentServiceImpl.getStudentById(1, "studentOutputDtoSimple");

        //Then
        assertEquals(studentOutputDtoSimple.getComments(), student.getComments());
        assertEquals(studentOutputDtoSimple.getBranch(), studentInputDto.getBranch());
    }


    @Test
    @Order(3)
    void getAllStudents() {
        //Given
        when(studentRepository.findAll()).thenReturn(List.of(student));

        //When
        List<StudentOutputDtoSimple> studentOutputDtoSimpleList = studentServiceImpl.getAllStudents();

        //Then
        assertNotNull(studentOutputDtoSimpleList);
        assertEquals(1, studentOutputDtoSimpleList.size());
    }

    @Test
    @Order(4)
    void updateStudent() {
        //Given
        when(studentRepository.findById(student.getIdStudent())).thenReturn(Optional.ofNullable(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        //When
        Student studentActualizado = studentRepository.findById(1).orElseThrow();
        studentActualizado.setBranch("Student Actualizado");
        studentRepository.save(studentActualizado);

        //Then
        assertEquals("Student Actualizado", studentActualizado.getBranch());
    }

    @Test
    @Order(5)
    void assignAsignaturaToStudent(){
        //Given
        when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));

        // When
        studentServiceImpl.assignAsignaturaToStudent(1, Collections.singletonList(1));

        //Then
        assertNotNull(student.getAsignaturas());

    }

    @Test
    @Order(6)
    void removeAsignaturasFromStudent(){
        //Given
        when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));

        // When
        studentServiceImpl.removeAsignaturasFromStudent(1, Collections.singletonList(1));

        //Then
        assertNotNull(student.getAsignaturas());

    }

    @Test
    @Order(7)
    void deleteStudentById() {
        //Given
        when(studentRepository.findById(student.getIdStudent())).thenReturn(Optional.ofNullable(student));

        // When
        studentServiceImpl.deleteStudentById(student.getIdStudent());

        //Then
        assertTrue(studentRepository.findAll().isEmpty());

    }


}