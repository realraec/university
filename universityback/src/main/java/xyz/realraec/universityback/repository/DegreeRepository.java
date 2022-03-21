package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;

import java.util.ArrayList;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Degree findByCode(String code);

    @Query("SELECT d FROM Degree d JOIN d.courses c WHERE c.id = ?1")
    Degree findByCourseId(Long courseId);

}
