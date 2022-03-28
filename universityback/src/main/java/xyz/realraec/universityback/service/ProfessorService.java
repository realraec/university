package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
public interface ProfessorService {

    Collection<Professor> list(int limit);

    Professor create(Professor professor);

    Professor get(Long id);

    Boolean update(Long id, String code, String lastName, String firstName, Gender gender, LocalDate birthdate, Integer level, String email, String phone, Double salary, Double balance, Integer warnings);

    Boolean replace(Long id, Professor professor);

    Boolean delete(Long id);

    ArrayList<Set<Course>> getCoursesTaughtByProfessor(Long[] professorsIdList) throws Exception;

    Boolean deleteProfessors(Long[] entitiesIdList) throws Exception;

    Integer getNumberProfessors();

    ArrayList<Integer> getNumberProfessorsPerLevel();

    Map<Gender, Integer> getNumberProfessorsPerGender();

    Map<String, Object> getMostOrLeastCoursesTaught(boolean mostOrLeast);

}

