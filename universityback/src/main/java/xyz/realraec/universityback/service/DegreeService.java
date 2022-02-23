package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;

import java.util.Collection;
import java.util.Set;

@Service
public interface DegreeService {

    Collection<Degree> list(int limit);

    Degree create(Degree degree);

    Degree get(Long id);

    Boolean update(Long id, String code, String heading, Department department, Set<Course> courses);

    Boolean replace(Long id, Degree degree);

    Boolean delete(Long id);

    //

    Course addCourse(Long[] degreesIdList, String courseCode) throws Exception;

    Course removeCourse(Long[] degreesIdList, String courseCode) throws Exception;

}

