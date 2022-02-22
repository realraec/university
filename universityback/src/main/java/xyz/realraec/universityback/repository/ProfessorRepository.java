package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findByCode(String code);

}
