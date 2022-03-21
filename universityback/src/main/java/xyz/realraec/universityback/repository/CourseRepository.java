package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Course;

import java.util.ArrayList;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCode(String code);

    @Query("SELECT c FROM Course c WHERE c.professor.id = ?1")
    ArrayList<Course> findByProfessorId(Long professorId);

    /*@Query("SELECT c.id, c.code, c.heading FROM Course c WHERE c.professor.id = ?1")
    ArrayList<Object> findCoursesTaughtByProfessorRepo(Long professorId);*/
}
