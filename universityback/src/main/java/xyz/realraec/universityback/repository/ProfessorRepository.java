package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Professor;

import java.util.ArrayList;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findByCode(String code);

    @Query("SELECT p FROM Degree d JOIN d.courses c JOIN c.professor p WHERE d.id = ?1")
    ArrayList<Professor> findByDegreeId(Long degreeId);

    @Query("SELECT p.email FROM Degree d JOIN d.courses c JOIN c.professor p WHERE d.id = ?1")
    ArrayList<String> getEmailByDegreeId(Long degreeId);

    /*@Query("SELECT p.email FROM Course c JOIN c.professor p WHERE c.id = ?1")
    String getProfessorEmailByCourseId(Long courseId);*/

}
