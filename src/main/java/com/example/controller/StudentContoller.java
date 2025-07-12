package com.example.controller;

import com.example.model.Students;
import com.example.services.StudentService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentContoller {
    private final StudentService studentService;

    public StudentContoller(StudentService studentService){
        this.studentService=studentService;
    }
    @GetMapping("/")
    public List<Students> getData(){
        return studentService.getData();

    }

    @GetMapping("/data/{id}/{name}")
    public Students getByIDAndName(@PathVariable String id, @PathVariable String name){
        return studentService.getByIdAndName(id,name);
    }

    @DeleteMapping("/delete/{id}/{name}")
    public String deleteByIdName(@PathVariable String id, @PathVariable String name){
        return studentService.deleteByIDName(id,name);
    }
    @PostMapping("/insert/")
    public String insertData(@RequestBody List<Students> students){
        return  studentService.insertData(students);
    }
    @PostMapping("/insert/data/")
    public String insertData(@RequestBody Students students){
        return  studentService.Data(students);
    }

    @PutMapping("/update/{id}/{name}")
    public String updateDate(@PathVariable String id, @PathVariable String name,@RequestBody Students students){
        return studentService.updateDate(id,name,students);
    }
}
