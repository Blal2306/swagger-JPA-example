package com.app.experiment.prototype_app.service;

import com.app.experiment.prototype_app.domain.Student;
import com.app.experiment.prototype_app.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepository;

    @Transactional
    public String createStudent(Student student){
        try {
            if (!studentRepository.existsByEmail(student.getEmail())){
                // Ensure that the maximum ID is cast to Long and we handle the case when it's null
                Long maxId = studentRepository.findMaxId();
                student.setId(maxId == null ? 1L : maxId + 1); // Defaulting to 1L if maxId is null
                studentRepository.save(student);
                return "Student record created successfully.";
            } else {
                return "Student already exists in the database.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Student> readStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public String updateStudent(Student student){
        if (studentRepository.existsByEmail(student.getEmail())){
            try {
                List<Student> students = studentRepository.findByEmail(student.getEmail());
                students.stream().forEach(s -> {
                    Student studentToBeUpdate = studentRepository.findById(s.getId()).get();
                    studentToBeUpdate.setName(student.getName());
                    studentToBeUpdate.setEmail(student.getEmail());
                    studentRepository.save(studentToBeUpdate);
                });
                return "Student record updated.";
            }catch (Exception e){
                throw e;
            }
        }else {
            return "Student does not exists in the database.";
        }
    }

    @Transactional
    public String deleteStudent(Student student){
        if (studentRepository.existsByEmail(student.getEmail())){
            try {
                List<Student> students = studentRepository.findByEmail(student.getEmail());
                students.stream().forEach(s -> {
                    studentRepository.delete(s);
                });
                return "Student record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Student does not exist";
        }
    }

}
