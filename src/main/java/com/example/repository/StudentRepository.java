package com.example.repository;

import com.example.model.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Students,String> {
    Students getByIdAndName(String id, String name);
    @Transactional
    @Modifying
    @Query("DELETE FROM Students s WHERE s.id = :id AND s.name = :name")
    int deleteByIdAndName(String id, String name);

    Students findByIdAndName(String id, String name);
}
