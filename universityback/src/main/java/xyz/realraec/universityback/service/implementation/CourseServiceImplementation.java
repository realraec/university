package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.service.CourseService;

import javax.transaction.Transactional;
import java.util.*;


// Creates a constructor using the one attribute as argument
@RequiredArgsConstructor
@Service
@Transactional
// Prints out information to the console as it goes
@Slf4j
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;


    @Override
    public Course create(Course course) {
        // Prints the following
        log.info("Saving new course: {}", course.getCode());
        return courseRepository.save(course);
    }

    @Override
    public Collection<Course> list(int limit) {
        log.info("Fetching all courses");
        // From the first element up to the limit
        return courseRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Course get(Long id) {
        log.info("Fetching course with id: {}", id);
        // The get() method returns the actual server found by id
        return courseRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Boolean update(Long id, String code, String heading, Department department, Boolean examMade, Boolean examTaken, Professor professor, Set<Student> students) throws Exception {
        Course course = get(id);

        if (code != null && code.length() > 0 && !Objects.equals(course.getCode(), code)) {
            course.setCode(code);
        }

        if (heading != null && heading.length() > 0 && !Objects.equals(course.getHeading(), heading)) {
            course.setHeading(heading);
        }

        if (department != null && department.getDepartment().length() > 0 && !Objects.equals(course.getDepartment(), department)) {
            course.setDepartment(department);
        }

        if (examMade != null && course.isIsExamMadeByProfessor() != examMade) {
            course.setIsExamMadeByProfessor(examMade);
        }

        if (examTaken != null && course.isIsExamTakenByStudents() != examTaken) {
            course.setIsExamTakenByStudents(examTaken);
        }

        if (professor != null && professor.getCode().length() > 0 && !Objects.equals(course.getProfessor(), professor)) {
            course.setProfessor(professor);
        }

        if (students != null && course.getStudents().size() > 0 && !Objects.equals(course.getStudents(), students)) {
            course.setStudents(students);
        }

        log.info("Updating course with id: {}", course.getId());
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean replace(Long id, Course course) throws Exception {
        Course oldCourse = get(id);

        String oldCode = oldCourse.getCode();
        String newCode = course.getCode();
        if (oldCode != null && oldCode.length() > 0
                && newCode != null && newCode.length() > 0
                && !Objects.equals(oldCode, newCode)) {
            oldCourse.setCode(newCode);
        }

        String oldHeading = oldCourse.getHeading();
        String newHeading = course.getHeading();
        if (oldHeading != null && oldHeading.length() > 0
                && newHeading != null && newHeading.length() > 0
                && !Objects.equals(oldHeading, newHeading)) {
            oldCourse.setHeading(newHeading);
        }

        Department oldDepartment = oldCourse.getDepartment();
        Department newDepartment = course.getDepartment();
        if (
                oldDepartment != null && oldDepartment.getDepartment().length() > 0
                        && newDepartment != null && newDepartment.getDepartment().length() > 0
                        && !Objects.equals(oldDepartment, newDepartment)) {
            oldCourse.setDepartment(newDepartment);
        }

        boolean oldExamMade = oldCourse.isIsExamMadeByProfessor();
        boolean newExamMade = course.isIsExamMadeByProfessor();
        if (oldExamMade != newExamMade) {
            oldCourse.setIsExamMadeByProfessor(newExamMade);
        }

        boolean oldExamTaken = oldCourse.isIsExamTakenByStudents();
        boolean newExamTaken = course.isIsExamTakenByStudents();
        if (oldExamTaken != newExamTaken) {
            oldCourse.setIsExamTakenByStudents(newExamTaken);
        }

        Professor oldProfessor = oldCourse.getProfessor();
        Professor newProfessor = course.getProfessor();
        if (oldProfessor != null && oldProfessor.getCode().length() > 0
                && newProfessor != null && newProfessor.getCode().length() > 0
                && !Objects.equals(oldProfessor, newProfessor)) {
            oldCourse.setProfessor(newProfessor);
        }

        Set<Student> oldStudents = oldCourse.getStudents();
        Set<Student> newStudents = course.getStudents();
        if (oldStudents != null && oldStudents.size() > 0
                && newStudents != null && newStudents.size() > 0
                && !Objects.equals(oldStudents, newStudents)) {
            oldCourse.setStudents(newStudents);
        }

        log.info("Replacing course with id: {}", id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting course with id: {}", id);
        courseRepository.deleteById(id);
        return Boolean.TRUE;
    }


    /*@Override
    @Transactional
    public Professor setNewProfessor(Long id, String professorCode) throws Exception {
        log.info("Setting new professor for course with id: {}", id);
        Course course = get(id);
        Professor professor = professorRepository.findByCode(professorCode);
        //System.out.println(professor.getId());
        *//*if (professor != null) {
            course.setProfessor(professor);
            return professor;
        } else {
            throw new Exception("The professor could not be found.");
        }*//*
        if (professor == null) {
            throw new Exception("No professor could be found with this code.");
        } else if (professor.getId() == course.getProfessor().getId()) {
            throw new Exception("The code of the new professor is the same as the old one.");
        } else {
            course.setProfessor(professor);
            return professor;
        }
    }*/


    @Override
    @Transactional
    public Professor setNewProfessor(Long[] coursesIdList, String professorCode) throws Exception {
        log.info("Setting new professor for courses with id: {}", Arrays.toString(coursesIdList));

        //Thread.sleep(2000);

        Professor professor = professorRepository.findByCode(professorCode);
        if (professor == null) {
            throw new Exception("No professor could be found with this code.");
        }

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesList.add(course);
            if (course.getProfessor() != null && course.getProfessor().getId() == professor.getId()) {
                throw new Exception("The code of the new professor is the same as the old one" + (coursesIdList.length == 1 ? "" : " for at least one of the courses") + ".");
            }
        }

        for (int i = 0; i < coursesList.size(); i++) {
            coursesList.get(i).setProfessor(professor);
        }
        return professor;
    }

    /*public Set<Student> getStudents(Long courseId) throws Exception {
        log.info("Getting students for course with id: {}", courseId);
        Course course = get(courseId);
        return course.getStudents();
    }*/

    @Override
    public ArrayList<Set> getStudents(Long[] coursesIdList) throws Exception {
        log.info("Getting students for courses with id: {}", Arrays.toString(coursesIdList));

        ArrayList<Set> coursesStudentsList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesStudentsList.add(course.getStudents());
        }

        return coursesStudentsList;
    }


    @Override
    @Transactional
    public Student addStudent(Long[] coursesIdList, String studentCode) throws Exception {
        log.info("Adding student for courses with id: {}", Arrays.toString(coursesIdList));

        Student student = studentRepository.findByCode(studentCode);
        if (student == null) {
            throw new Exception("No student could be found with this code.");
        }

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesList.add(course);

            Set<Student> studentsSet = course.getStudents();
            for (Student value : studentsSet) {
                if (value.getId() == student.getId()) {
                    throw new Exception("The student is already enrolled" + (coursesIdList.length == 1 ? "" : " in at least one of the courses") + ".");
                }
            }
        }

        for (int i = 0; i < coursesList.size(); i++) {
            coursesList.get(i).addStudent(student);
        }
        return student;
    }

    @Override
    @Transactional
    public Student removeStudent(Long[] coursesIdList, String studentCode) throws Exception {
        log.info("Removing student for courses with id: {}", Arrays.toString(coursesIdList));

        Student student = studentRepository.findByCode(studentCode);
        if (student == null) {
            throw new Exception("No student could be found with this code.");
        }

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesList.add(course);

            Set<Student> studentsSet = course.getStudents();
            boolean studentPresent = false;
            for (Student value : studentsSet) {
                if (value.getId() == student.getId()) {
                    studentPresent = true;
                }
            }
            if (!studentPresent) {
                throw new Exception("The student is not enrolled" + (coursesIdList.length == 1 ? "" : " in at least one of the courses") + ".");
            }
        }

        for (int i = 0; i < coursesList.size(); i++) {
            coursesList.get(i).removeStudent(student);
        }
        return student;
    }


    @Override
    @Transactional
    public Boolean setIsExamMadeByProfessor(Long[] coursesIdList, Boolean isExamMadeByProfessor) throws Exception {
        log.info("Setting isExamMadeByProfessor to " + isExamMadeByProfessor + " for courses with id: {}", Arrays.toString(coursesIdList));

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesList.add(course);

            if (isExamMadeByProfessor == course.isIsExamMadeByProfessor()) {
                throw new Exception("The exam has already " + (isExamMadeByProfessor ? "" : "not ") + "been made" + (coursesIdList.length == 1 ? "" : " for at least one of the courses") + ".");
            } else if (course.isIsExamTakenByStudents()) {
                throw new Exception("The exam has already been taken by the students" + (coursesIdList.length == 1 ? "" : " for at least one of the courses") + ", so it can't be unmade.");
            }
        }

        for (int i = 0; i < coursesList.size(); i++) {
            coursesList.get(i).setIsExamMadeByProfessor(isExamMadeByProfessor);
        }

        return isExamMadeByProfessor;
    }

    @Override
    @Transactional
    public Boolean setIsExamTakenByStudents(Long[] coursesIdList, Boolean isExamTakenByStudents) throws Exception {
        log.info("Setting isExamTakenByStudents to " + isExamTakenByStudents + " for courses with id: {}", Arrays.toString(coursesIdList));

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesList.add(course);

            if (isExamTakenByStudents == course.isIsExamTakenByStudents()) {
                throw new Exception("The exam has already " + (isExamTakenByStudents ? "" : "not ") + "been taken" + (coursesIdList.length == 1 ? "" : " for at least one of the courses") + ".");
            } else if (!course.isIsExamMadeByProfessor()) {
                throw new Exception("The exam has not been made yet" + (coursesIdList.length == 1 ? "" : " for at least one of the courses") + ", so it can't be taken.");
            }
        }

        for (int i = 0; i < coursesList.size(); i++) {
            coursesList.get(i).setIsExamTakenByStudents(isExamTakenByStudents);
        }

        return isExamTakenByStudents;
    }


}

