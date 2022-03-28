package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;

import java.util.*;

@Service
public interface CourseService {

    Collection<Course> list(int limit);

    Course create(Course course);

    Course get(Long id);

    Boolean update(Long id, String code, String heading, Department department, Boolean examMade, Boolean examTaken, Professor professor, Set<Student> students) throws Exception;

    Boolean replace(Long id, Course course) throws Exception;

    Boolean delete(Long id);

    Professor setNewProfessor(Long[] coursesIdList, String professorCode) throws Exception;

    Degree setNewDegree(Long[] coursesIdList, String degreeCode) throws Exception;

    Student addStudent(Long[] coursesIdList, String studentCode) throws Exception;

    Student removeStudent(Long[] coursesIdList, String studentCode) throws Exception;

    Boolean setIsExamMadeByProfessor(Long[] coursesIdList, Boolean isExamMadeByProfessor) throws Exception;

    Boolean setIsExamTakenByStudents(Long[] coursesIdList, Boolean isExamTakenByStudents) throws Exception;

    ArrayList<Degree> getDegreeTheCourseIsPartOf (Long[] coursesIdList) throws Exception;

    Boolean deleteCourses(Long[] entitiesIdList) throws Exception;

    Integer getNumberEntries();

    Map<Department, Integer> getNumberEntriesPerDepartment();

    Integer getNumberEntriesExamMade();

    Integer getNumberEntriesExamTaken();

    Map<String, Object> getMostOrLeastStudentsEnrolled(boolean mostOrLeast);

}

