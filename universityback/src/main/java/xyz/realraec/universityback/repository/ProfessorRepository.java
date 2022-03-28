package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Professor;

import java.util.ArrayList;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findByCode(String code);

    @Query("SELECT p FROM Degree d JOIN d.courses c JOIN c.professor p WHERE d.id = ?1")
    ArrayList<Professor> findByDegreeId(Long degreeId);

    @Query("SELECT p.email FROM Degree d JOIN d.courses c JOIN c.professor p WHERE d.id = ?1")
    ArrayList<String> getEmailByDegreeId(Long degreeId);

    @Query("SELECT COUNT(p) FROM Professor p")
    Integer queryNumberEntries();

    @Query("SELECT COUNT(p) FROM Professor p WHERE p.level = ?1")
    Integer queryNumberEntriesPerLevel(Integer level);

    @Query("SELECT COUNT(p) FROM Professor p WHERE p.gender = ?1")
    Integer queryNumberEntriesPerGender(Gender gender);

    @Query("SELECT COUNT (c) FROM Course c WHERE c.professor.id = ?1")
    Integer queryNumberCoursesTaught(Long id);

}
