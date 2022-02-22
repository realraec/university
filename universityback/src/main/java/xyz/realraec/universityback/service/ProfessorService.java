package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;

import java.time.LocalDate;
import java.util.Collection;

@Service
public interface ProfessorService {

    Collection<Professor> list(int limit);

    Professor create(Professor professor);

    Professor get(Long id);

    Boolean update(Long id, String code, String lastName, String firstName, Gender gender, LocalDate birthdate, Integer level, String email, String phone, Double salary, Double balance, Integer warnings);

    Boolean replace(Long id, Professor professor);

    Boolean delete(Long id);

}

