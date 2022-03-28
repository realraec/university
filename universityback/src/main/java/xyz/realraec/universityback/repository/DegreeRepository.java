package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Degree;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Degree findByCode(String code);

    @Query("SELECT d FROM Degree d JOIN d.courses c WHERE c.id = ?1")
    Degree findByCourseId(Long courseId);

    @Query("SELECT COUNT(d) FROM Degree d")
    Integer queryNumberDegrees();

    @Query("SELECT COUNT(d) FROM Degree d WHERE d.department = ?1")
    Integer queryNumberDegreesPerDepartment(Department department);

    @Query("SELECT d.courses.size FROM Degree d WHERE d.id = ?1")
    Integer queryNumberCoursesAssociated(Long id);

    @Query("SELECT COUNT (s) FROM Student s JOIN s.majorDegree d1 JOIN s.minorDegree d2 WHERE d1.id = ?1 OR d2.id = ?1")
    Integer queryNumberStudentsEnrolled(Long id);

}
