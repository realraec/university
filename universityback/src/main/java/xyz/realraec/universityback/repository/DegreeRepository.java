package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Student;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {

    Degree findByCode(String code);

}
