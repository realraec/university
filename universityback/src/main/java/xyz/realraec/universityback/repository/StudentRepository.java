package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Student;

import java.util.ArrayList;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByCode(String code);

    @Query("SELECT s FROM Student s JOIN s.majorDegree d1 JOIN s.minorDegree d2 WHERE d1.id = ?1 OR d2.id = ?1")
    ArrayList<Student> findByDegreeId(Long degreeId);

    @Query("SELECT s.email FROM Student s JOIN s.majorDegree d1 JOIN s.minorDegree d2 WHERE d1.id = ?1 OR d2.id = ?1")
    ArrayList<String> getEmailByDegreeId(Long degreeId);

    @Query("SELECT COUNT(s) FROM Student s")
    Integer queryNumberEntries();

    @Query("SELECT COUNT(s) FROM Student s WHERE s.level = ?1")
    Integer queryNumberEntriesPerLevel(Integer level);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.gender = ?1")
    Integer queryNumberEntriesPerGender(Gender gender);

    @Query("SELECT s.courses.size FROM Student s WHERE s.id = ?1")
    Integer queryNumberCoursesTaken(Long id);

}
