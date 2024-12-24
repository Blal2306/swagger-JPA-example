package com.app.experiment.prototype_app.controller;

import com.app.experiment.prototype_app.domain.Student;
import com.app.experiment.prototype_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/javainuse")
    public String sayHello() {
        return "Pakistan Zindabad!";
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The application is up...";
    }

    @RequestMapping(value = "createstudent", method = RequestMethod.POST)
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @RequestMapping(value = "readstudents", method = RequestMethod.GET)
    public List<Student> readStudents(){
        return studentService.readStudents();
    }

    @RequestMapping(value = "updatestudent", method = RequestMethod.PUT)
    public String updateStudet(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @RequestMapping(value = "deletestudent", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestBody Student student){
        return studentService.deleteStudent(student);
    }
}
