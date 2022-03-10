package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Diploma;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public interface StudentService {

    Collection<Student> list(int limit);

    Student create(Student student);

    Student get(Long id);

    Student update(Long id, String code, String lastName, String firstName, Gender gender, LocalDate birthdate, Integer level, Degree majorDegree, Degree minorDegree, String email, String phone, Double tuition, Double balance, Integer credits, Diploma diploma, Integer warnings) throws Exception;

    Boolean replace(Long id, Student student) throws Exception;

    Boolean delete(Long id);

    ArrayList<Set> getCourses(Long[] studentsIdList) throws Exception;

    Degree setNewMinorDegree(Long[] studentsIdList, String degreeCode) throws Exception;

    Degree setNewMajorDegree(Long[] studentsIdList, String degreeCode) throws Exception;

    ArrayList<Integer> giveCredits(Long[] studentsIdList, Integer credits) throws Exception;

    Diploma giveDiploma(Long[] studentsIdList, Diploma diploma) throws Exception;

}

