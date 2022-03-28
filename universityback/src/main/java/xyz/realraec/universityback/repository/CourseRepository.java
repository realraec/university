package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;

import java.util.ArrayList;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCode(String code);

    @Query("SELECT c FROM Course c WHERE c.professor.id = ?1")
    Set<Course> findByProfessorId(Long professorId);

    @Query("SELECT COUNT(c) FROM Course c")
    Integer queryNumberCourses();

    @Query("SELECT COUNT(c) FROM Course c WHERE c.department = ?1")
    Integer queryNumberCoursesPerDepartment(Department department);

    @Query("SELECT COUNT(c) FROM Course c WHERE c.isExamMadeByProfessor = true")
    Integer queryNumberCoursesExamMade();

    @Query("SELECT COUNT(c) FROM Course c WHERE c.isExamTakenByStudents = true")
    Integer queryNumberCoursesExamTaken();

    @Query("SELECT c.students.size FROM Course c WHERE c.id = ?1")
    Integer queryNumberStudentsEnrolled(Long id);

}
