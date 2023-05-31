package kz.bitlab.Adilzhan.Offline.SpringBoot.services;

import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.CoursesRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.OperatorsRepository;
import kz.bitlab.Adilzhan.Offline.SpringBoot.repositories.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {
    private final RequestRepository requestRepository;
    private final CoursesRepository coursesRepository;
    private final OperatorsRepository operatorsRepository;

    public List<Courses> getAllCourses() {
        List<Courses> courses = coursesRepository.findAll();
        return courses;
    }
}
