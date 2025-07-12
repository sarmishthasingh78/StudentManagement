package com.example.services;

import com.example.model.Students;
import com.example.repository.StudentRepository;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> getData() {
        return studentRepository.findAll();
    }

    public Students getByIdAndName(@PathVariable String id, @PathVariable String name) {
        return studentRepository.getByIdAndName(id, name);

    }

    @Transactional
    public String deleteByIDName(String id, String name) {
        int rowsDeleted = studentRepository.deleteByIdAndName(id, name);
        if (rowsDeleted > 0) {
            return "Student deleted successfully";
        } else {
            return "Student not found or already deleted";
        }
    }
    public String insertData(@RequestBody List<Students> students){
        List<Students> filtred = new ArrayList<>();
        for(Students std:students){
            if(std.getId()==null){
                std.setId(UUID.randomUUID().toString());
                filtred.add(std);
            }
        }
         studentRepository.saveAll(filtred);
       
                return "Data is insterted succesully";
    }

    public String Data(@RequestBody Students students){
        if(students.getId()==null){
            students.setId(UUID.randomUUID().toString());
            studentRepository.save(students);
        }
        return "Success";

   }

    public String updateDate(String id, String name, Students updatedData) {
        Students existingStudent = studentRepository.findByIdAndName(id, name);

        if (existingStudent != null) {
            existingStudent.setName(updatedData.getName());
            existingStudent.setAddress(updatedData.getAddress());
            existingStudent.setPhone(updatedData.getPhone());
            // Optionally update name or id if you want
            studentRepository.save(existingStudent);
            return "Data is updated successfully";
        } else {
            return "Data not found";
        }
    }
}
