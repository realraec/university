package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Service
public interface DegreeService {

    Collection<Degree> list(int limit);

    Degree create(Degree degree);

    Degree get(Long id);

    Boolean update(Long id, String code, String heading, Department department, Set<Course> courses);

    Boolean replace(Long id, Degree degree);

    Boolean delete(Long id);

    Course addCourse(Long[] degreesIdList, String courseCode) throws Exception;

    Course removeCourse(Long[] degreesIdList, String courseCode) throws Exception;

    ArrayList<ArrayList<Student>> getStudentsEnrolledInDegree(Long[] degreesIdList) throws Exception;

    ArrayList<ArrayList<Professor>> getProfessorsTeachingInDegree(Long[] degreesIdList) throws Exception;

    Boolean deleteDegrees(Long[] entitiesIdList) throws Exception;

    Integer getNumberEntries();

    Map<Department, Integer> getNumberEntriesPerDepartment();

    Map<String, Object> getMostOrLeastCoursesAssociated(boolean mostOrLeast);

    Map<String, Object> getMostOrLeastStudentsEnrolled(boolean mostOrLeast);

}

