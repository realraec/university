package xyz.realraec.universityback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.realraec.universityback.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByCode(String code);

}
