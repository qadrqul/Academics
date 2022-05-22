package com.example.academics.model.repo;

import com.example.academics.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Long> {
}