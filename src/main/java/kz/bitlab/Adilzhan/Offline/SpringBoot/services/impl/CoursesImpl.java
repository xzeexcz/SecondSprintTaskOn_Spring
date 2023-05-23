package kz.bitlab.Adilzhan.Offline.SpringBoot.services.impl;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.CoursesRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CoursesImpl implements CourseService {
    @Autowired
    CoursesRepository coursesRepository;

    @Override
    public List<Courses> getCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Courses getCourseById(Long id) {
        return coursesRepository.getOne(id);
    }
}
