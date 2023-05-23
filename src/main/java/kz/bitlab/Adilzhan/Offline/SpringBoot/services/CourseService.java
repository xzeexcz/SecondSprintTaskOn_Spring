package kz.bitlab.Adilzhan.Offline.SpringBoot.services;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;

import java.util.List;

public interface CourseService {
    List<Courses> getCourses();

    Courses getCourseById(Long id);
}
