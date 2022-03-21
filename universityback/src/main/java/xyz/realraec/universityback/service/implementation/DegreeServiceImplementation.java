package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.service.DegreeService;

import javax.transaction.Transactional;
import java.util.*;


// Creates a constructor using the one attribute as argument
@RequiredArgsConstructor
@Service
@Transactional
// Prints out information to the console as it goes
@Slf4j
public class DegreeServiceImplementation implements DegreeService {

    private final DegreeRepository degreeRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;


    @Override
    public Degree create(Degree degree) {
        // Prints the following
        log.info("Saving new degree: {}", degree.getHeading());
        return degreeRepository.save(degree);
    }

    @Override
    public Collection<Degree> list(int limit) {
        log.info("Fetching all degrees");
        // From the first element up to the limit
        return degreeRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Degree get(Long id) {
        log.info("Fetching degree with id: {}", id);
        // The get() method returns the actual degree found by id
        return degreeRepository.findById(id).get();
    }


    @Override
    @Transactional
    public Boolean update(Long id, String code, String heading, Department department, Set<Course> courses) {
        Degree degree = get(id);

        if (code != null && code.length() > 0 && !Objects.equals(degree.getCode(), code)) {
            degree.setCode(code);
        }

        if (heading != null && heading.length() > 0 && !Objects.equals(degree.getHeading(), heading)) {
            degree.setHeading(heading);
        }

        if (department != null && department.getDepartment().length() > 0 && !Objects.equals(degree.getDepartment(), department)) {
            degree.setDepartment(department);
        }

        if (courses != null && courses.size() > 0 && !Objects.equals(degree.getCourses(), courses)) {
            degree.setCourses(courses);
        }

        log.info("Updating degree with id: {}", degree.getId());
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean replace(Long id, Degree newDegree) {
        Degree oldDegree = get(id);

        String oldCode = oldDegree.getCode();
        String newCode = newDegree.getCode();
        if (oldCode != null && oldCode.length() > 0
                && newCode != null && newCode.length() > 0
                && !Objects.equals(oldCode, newCode)) {
            oldDegree.setCode(newCode);
        }

        String oldHeading = oldDegree.getHeading();
        String newHeading = newDegree.getHeading();
        if (oldHeading != null && oldHeading.length() > 0
                && newHeading != null && newHeading.length() > 0
                && !Objects.equals(oldHeading, newHeading)) {
            oldDegree.setHeading(newHeading);
        }

        Department oldDepartment = oldDegree.getDepartment();
        Department newDepartment = newDegree.getDepartment();
        if (
                oldDepartment != null && oldDepartment.getDepartment().length() > 0
                        && newDepartment != null && newDepartment.getDepartment().length() > 0
                        && !Objects.equals(oldDepartment, newDepartment)) {
            oldDegree.setDepartment(newDepartment);
        }

        Set<Course> oldCourses = oldDegree.getCourses();
        Set<Course> newCourses = newDegree.getCourses();
        if (oldCourses != null && oldCourses.size() > 0
                && newCourses != null && newCourses.size() > 0
                && !Objects.equals(oldCourses, newCourses)) {
            oldDegree.setCourses(newCourses);
        }

        log.info("Replacing degree with id: {}", id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting degree with id: {}", id);
        degreeRepository.deleteById(id);
        return Boolean.TRUE;
    }


    @Override
    @Transactional
    public Course addCourse(Long[] degreesIdList, String courseCode) throws Exception {
        log.info("Adding course for degrees with id: {}", Arrays.toString(degreesIdList));

        if (degreesIdList.length == 0) {
            throw new Exception("No degree was provided to perform this action on.");
        } else if (degreesIdList.length > 1) {
            throw new Exception("A course can only be included in one degree.");
        }

        Course course = courseRepository.findByCode(courseCode);
        if (course == null) {
            throw new Exception("No course could be found with this code.");
        }

        ArrayList<Degree> degreeList = new ArrayList<>();

        for (int i = 0; i < degreesIdList.length; i++) {
            Degree degree = get(degreesIdList[i]);
            degreeList.add(degree);

            Set<Course> coursesSet = degree.getCourses();
            for (Course value : coursesSet) {
                if (value.getId() == course.getId()) {
                    throw new Exception("The course is already included.");
                }
            }
        }

        for (int i = 0; i < degreeList.size(); i++) {
            degreeList.get(i).addCourse(course);
        }
        return course;
    }

    @Override
    @Transactional
    public Course removeCourse(Long[] degreesIdList, String courseCode) throws Exception {
        log.info("Removing course for degrees with id: {}", Arrays.toString(degreesIdList));

        if (degreesIdList.length == 0) {
            throw new Exception("No degree was provided to perform this action on.");
        } else if (degreesIdList.length > 1) {
            throw new Exception("A course can only be included in one degree.");
        }

        Course course = courseRepository.findByCode(courseCode);
        if (course == null) {
            throw new Exception("No course could be found with this code.");
        }

        ArrayList<Degree> degreeList = new ArrayList<>();

        for (int i = 0; i < degreesIdList.length; i++) {
            Degree degree = get(degreesIdList[i]);
            degreeList.add(degree);

            Set<Course> coursesSet = degree.getCourses();
            boolean coursePresent = false;
            for (Course value : coursesSet) {
                if (value.getId() == course.getId()) {
                    coursePresent = true;
                }
            }
            if (!coursePresent) {
                throw new Exception("The course is already not included.");
            }
        }

        for (int i = 0; i < degreeList.size(); i++) {
            degreeList.get(i).removeCourse(course);
        }
        return course;
    }


    @Override
    public ArrayList<ArrayList<Student>> getStudentsEnrolledInDegree(Long[] degreesIdList) throws Exception {
        log.info("Getting students enrolled in degree with id: {}", Arrays.toString(degreesIdList));

        if (degreesIdList.length == 0) {
            throw new Exception("No degree was provided to perform this action on.");
        }

        ArrayList<ArrayList<Student>> studentsList = new ArrayList<>();
        for (int i = 0; i < degreesIdList.length; i++) {
            studentsList.add(new ArrayList<>(new HashSet<>(studentRepository.findByDegreeId(degreesIdList[i]))));
        }

        return studentsList;
    }


    @Override
    public ArrayList<ArrayList<Professor>> getProfessorsTeachingInDegree(Long[] degreesIdList) throws Exception {
        log.info("Getting professors teaching in degree with id: {}", Arrays.toString(degreesIdList));

        if (degreesIdList.length == 0) {
            throw new Exception("No degree was provided to perform this action on.");
        }

        ArrayList<ArrayList<Professor>> professorsList = new ArrayList<>();
        for (int i = 0; i < degreesIdList.length; i++) {
            professorsList.add(new ArrayList<>(new HashSet<>(professorRepository.findByDegreeId(degreesIdList[i]))));
        }

        return professorsList;
    }


    @Override
    @Transactional
    public Boolean deleteDegrees(Long[] entitiesIdList) throws Exception {
        log.info("Deleting entities (degrees) with id: {}", Arrays.toString(entitiesIdList));

        if (entitiesIdList.length == 0) {
            throw new Exception("No entity (degree) was provided to perform this action on.");
        }

        ArrayList<Degree> degreesList = new ArrayList<>();

        for (int i = 0; i < entitiesIdList.length; i++) {

            Degree degree;
            try {
                degree = degreeRepository.findById(entitiesIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect" + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }

            degreesList.add(degree);
        }

        ArrayList<ArrayList<Student>> studentsEnrolled = getStudentsEnrolledInDegree(entitiesIdList);
        for (int i = 0; i < studentsEnrolled.size(); i++) {
            for (int j = 0; j < studentsEnrolled.get(i).size(); j++) {
                Student student = studentsEnrolled.get(i).get(j);
                if (student.getMinorDegree() != null && student.getMinorDegree().getId() == degreesList.get(i).getId()) {
                    student.setMinorDegree(null);
                } else if (student.getMajorDegree() != null && student.getMajorDegree().getId() == degreesList.get(i).getId()) {
                    student.setMajorDegree(null);
                }

                for (int k = 0; k < degreesList.size(); k++) {
                    studentsEnrolled.get(i).get(j).getCourses().remove(degreesList.get(k).getCourses());
                }
            }
        }

        for (int i = 0; i < degreesList.size(); i++) {
            degreesList.get(i).setCourses(null);
            degreeRepository.deleteById(entitiesIdList[i]);
        }

        return Boolean.TRUE;
    }

}

