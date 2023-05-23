package kz.bitlab.Adilzhan.Offline.SpringBoot.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.Adilzhan.Offline.SpringBoot.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    
}
