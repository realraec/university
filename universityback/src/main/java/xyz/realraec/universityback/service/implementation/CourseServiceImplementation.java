package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
import xyz.realraec.universityback.service.CourseService;

import javax.transaction.Transactional;
import java.util.*;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final DegreeRepository degreeRepository;


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


    @Override
    @Transactional
    public Professor setNewProfessor(Long[] coursesIdList, String professorCode) throws Exception {
        log.info("Setting new professor for courses with id: {}", Arrays.toString(coursesIdList));

        if (coursesIdList.length == 0) {
            throw new Exception("No course was provided to perform this action on.");
        }

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


    @Override
    @Transactional
    public Degree setNewDegree(Long[] coursesIdList, String degreeCode) throws Exception {
        log.info("Setting new professor for courses with id: {}", Arrays.toString(coursesIdList));

        if (coursesIdList.length == 0) {
            throw new Exception("No course was provided to perform this action on.");
        }

        Degree degree = degreeRepository.findByCode(degreeCode);
        if (degree == null) {
            throw new Exception("No degree could be found with this code.");
        }

        ArrayList<Course> coursesList = new ArrayList<>();
        ArrayList<Degree> oldDegreesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            Degree oldDegree = degreeRepository.findByCourseId(coursesIdList[i]);

            if (course == null) {
                throw new Exception("The code is wrong for" + (coursesIdList.length == 1 ? " the course" : " at least one of the courses") + ".");
            } else if (degree.equals(oldDegree)) {
                throw new Exception("The code for the new degree is the same as the old one" + (coursesIdList.length == 1 ? "" : " for at least one of the courses") + ".");
            } else {
                coursesList.add(course);
                oldDegreesList.add(oldDegree);
            }
        }

        for (int i = 0; i < coursesList.size(); i++) {
            if (oldDegreesList.get(i) != null) {
                oldDegreesList.get(i).removeCourse(coursesList.get(i));
            }
            degree.addCourse(coursesList.get(i));
        }

        return degree;
    }


    @Override
    @Transactional
    public Student addStudent(Long[] coursesIdList, String studentCode) throws Exception {
        log.info("Adding student for courses with id: {}", Arrays.toString(coursesIdList));

        if (coursesIdList.length == 0) {
            throw new Exception("No course was provided to perform this action on.");
        }

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

        if (coursesIdList.length == 0) {
            throw new Exception("No course was provided to perform this action on.");
        }

        Student student = studentRepository.findByCode(studentCode);
        if (student == null) {
            throw new Exception("No student could be found with this code.");
        }

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < coursesIdList.length; i++) {
            Course course = get(coursesIdList[i]);
            coursesList.add(course);

            if ((student.getMinorDegree() != null && student.getMinorDegree().getCourses().contains(course))
                    || (student.getMajorDegree() != null && student.getMajorDegree().getCourses().contains(course))) {
                throw new Exception("This student is enrolled in a degree in which"
                        + (coursesIdList.length == 1 ? " this course " : " at least one of theses courses ") + "is mandatory.");
            }

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
            studentRepository.findByCode(studentCode).getCourses().remove(coursesList.get(i));
        }
        return student;
    }


    @Override
    @Transactional
    public Boolean setIsExamMadeByProfessor(Long[] coursesIdList, Boolean isExamMadeByProfessor) throws Exception {
        log.info("Setting isExamMadeByProfessor to " + isExamMadeByProfessor + " for courses with id: {}", Arrays.toString(coursesIdList));

        if (coursesIdList.length == 0) {
            throw new Exception("No course was provided to perform this action on.");
        }

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

        if (coursesIdList.length == 0) {
            throw new Exception("No course was provided to perform this action on.");
        }

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


    @Override
    public ArrayList<Degree> getDegreeTheCourseIsPartOf(Long[] coursesIdList) throws Exception {
        log.info("Getting degree in which are the courses with id: {}", Arrays.toString(coursesIdList));

        if (coursesIdList.length == 0) {
            throw new Exception("No student was provided to perform this action on.");
        }

        ArrayList<Degree> degreesList = new ArrayList<>();
        for (int i = 0; i < coursesIdList.length; i++) {
            degreesList.add(degreeRepository.findByCourseId(coursesIdList[i]));
        }

        return degreesList;
    }


    @Override
    @Transactional
    public Boolean deleteCourses(Long[] entitiesIdList) throws Exception {
        log.info("Deleting entities (courses) with id: {}", Arrays.toString(entitiesIdList));

        if (entitiesIdList.length == 0) {
            throw new Exception("No entity (course) was provided to perform this action on.");
        }

        ArrayList<Course> coursesList = new ArrayList<>();

        for (int i = 0; i < entitiesIdList.length; i++) {

            Course course;
            try {
                course = courseRepository.findById(entitiesIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect" + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }

            coursesList.add(course);
        }

        ArrayList<Degree> degreeTheCourseIsPartOf = getDegreeTheCourseIsPartOf(entitiesIdList);

        for (int i = 0; i < coursesList.size(); i++) {
            Course course = coursesList.get(i);
            course.setProfessor(null);
            /*Set<Student> studentsSet = course.getStudents();
            studentsSet.forEach(student -> student.getCourses().remove(course));*/
            course.setStudents(null);
            degreeTheCourseIsPartOf.get(i).removeCourse(course);
            courseRepository.deleteById(entitiesIdList[i]);
        }

        return Boolean.TRUE;
    }


    @Override
    public Integer getNumberEntries() {
        log.info("Getting the number of courses");
        return courseRepository.queryNumberCourses();
    }


    @Override
    public Map<Department, Integer> getNumberEntriesPerDepartment() {
        log.info("Getting the number of courses per department");

        HashMap<Department, Integer> numberCoursesPerDepartmentMap = new HashMap<>();
        Department[] departments = Department.class.getEnumConstants();
        for (int i = 0; i < departments.length; i++) {
            Department temp = departments[i];
            numberCoursesPerDepartmentMap.put(temp, courseRepository.queryNumberCoursesPerDepartment(temp));
        }

        return numberCoursesPerDepartmentMap;
    }


    @Override
    public Integer getNumberEntriesExamMade() {
        log.info("Getting the number of courses for which the exam is made");
        return courseRepository.queryNumberCoursesExamMade();
    }


    @Override
    public Integer getNumberEntriesExamTaken() {
        log.info("Getting the number of courses for which the exam is made");
        return courseRepository.queryNumberCoursesExamTaken();
    }


    @Override
    public Map<String, Object> getMostOrLeastStudentsEnrolled(boolean mostOrLeast) {
        log.info("Getting the " + (mostOrLeast ? "highest" : "lowest") + " number of students enrolled");

        Map<String, Object> numberStudentsAndCourseMap = new HashMap<>();
        int numberCourses = courseRepository.queryNumberCourses();
        long idCourse = 1L;
        while (courseRepository.findById(idCourse).isEmpty()) {
            idCourse++;
        }
        int numberStudentsEnrolled = courseRepository.queryNumberStudentsEnrolled(idCourse);
        for (long i = idCourse + 1; i <= numberCourses; i++) {
            int temp = courseRepository.queryNumberStudentsEnrolled(i);
            if ((mostOrLeast && temp > numberStudentsEnrolled)
                    || (!mostOrLeast && temp < numberStudentsEnrolled)) {
                numberStudentsEnrolled = temp;
                idCourse = i;
            }
        }

        numberStudentsAndCourseMap.put("study", courseRepository.findById(idCourse).get());
        numberStudentsAndCourseMap.put("number", numberStudentsEnrolled);

        return numberStudentsAndCourseMap;
    }

}

