package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
public interface CourseService {

    Collection<Course> list(int limit);

    Course create(Course course);

    Course get(Long id);

    Boolean update(Long id, String code, String heading, Department department, Boolean examMade, Boolean examTaken, Professor professor, Set<Student> students) throws Exception;

    Boolean replace(Long id, Course course) throws Exception;

    Boolean delete(Long id);

    //Professor setNewProfessor(Long id, String professorCode) throws Exception;

    Professor setNewProfessorMultiple(Long[] coursesIdList, String professorCode) throws Exception;

    ArrayList<Set> getStudents(Long[] coursesIdList) throws Exception;

    Student addStudent(Long[] coursesIdList, String studentCode) throws Exception;

    Student removeStudent(Long[] coursesIdList, String studentCode) throws Exception;

    Boolean setIsExamMadeByProfessor(Long[] coursesIdList, Boolean isExamMadeByProfessor) throws Exception;

    Boolean setIsExamTakenByStudents(Long[] coursesIdList, Boolean isExamTakenByStudents) throws Exception;


}

