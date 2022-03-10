package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCode(String code);
}
