package com.bosonit.formacion.block1201criteriabuilder.student.application;

import com.bosonit.formacion.block1201criteriabuilder.student.controller.dto.studentInputDto.StudentInputDto;
import com.bosonit.formacion.block1201criteriabuilder.student.controller.dto.studentOutputDto.StudentOutputDtoSimple;

import java.util.List;

public interface StudentService {
    StudentOutputDtoSimple addStudent(StudentInputDto StudentInputDto);

    String assignAsignaturaToStudent(Integer idStudent, List<Integer> idsAsignaturas);

    String removeAsignaturasFromStudent(Integer idStudent, List<Integer> idsAsignaturas);

    StudentOutputDtoSimple getStudentById(Integer id, String outputType);

    List<StudentOutputDtoSimple> getAllStudents();

    StudentOutputDtoSimple updateStudent(Integer id, StudentInputDto studentInputDto);

    String deleteStudentById(Integer id);
}
