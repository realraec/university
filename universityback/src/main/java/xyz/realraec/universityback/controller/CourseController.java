package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.*;
import xyz.realraec.universityback.service.implementation.CourseServiceImplementation;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/courses")
@CrossOrigin
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImplementation courseService;


    @GetMapping("/list")
    public ResponseEntity<Response> getCourses() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("courses", courseService.list(30)))
                        .message("Courses retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("course", courseService.get(id)))
                        .message("Course with id " + id + " retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PostMapping("/create")
    public ResponseEntity<Response> createCourse(@RequestBody @Valid Course course) {
        course = new Course(course.getHeading(), course.getDepartment());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("study", courseService.create(course)))
                        .message("Course created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateCourse(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String heading,
            @RequestParam(required = false) Department department,
            @RequestParam(required = false) Boolean examMade,
            @RequestParam(required = false) Boolean examTaken,
            @RequestParam(required = false) Professor professor,
            @RequestParam(required = false) Set<Student> students
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("course", courseService.update(id, code, heading, department, examMade, examTaken, professor, students)))
                        .message("Course with id " + id + " updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/replace/{id}")
    public ResponseEntity<?> replaceCourse(
            @PathVariable("id") Long id,
            @RequestBody Course course
    ) throws Exception {
        course = new Course(course.getHeading());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("course", courseService.replace(id, course)))
                        .message("Course with id " + id + " replaced")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", courseService.delete(id)))
                        .message("Course deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/setNewProfessor")
    public ResponseEntity<Response> setNewProfessorForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam String entityCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professor", courseService.setNewProfessor(coursesIdList, entityCode)))
                        .message("Courses with id " + Arrays.toString(coursesIdList) + " set with new professor with code " + entityCode)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/setNewDegree")
    public ResponseEntity<Response> setNewDegreeForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam String entityCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", courseService.setNewDegree(coursesIdList, entityCode)))
                        .message("Courses with id " + Arrays.toString(coursesIdList) + " set with new degree with code " + entityCode)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/addStudent")
    public ResponseEntity<Response> addStudentForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam String studentCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("student", courseService.addStudent(coursesIdList, studentCode)))
                        .message("Courses with id " + Arrays.toString(coursesIdList) + " had student with code " + studentCode + " added")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/removeStudent")
    public ResponseEntity<Response> removeStudentForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam String studentCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("student", courseService.removeStudent(coursesIdList, studentCode)))
                        .message("Courses with id " + Arrays.toString(coursesIdList) + " had student with code " + studentCode + " removed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/setIsExamMadeByProfessor")
    public ResponseEntity<Response> setIsExamMadeByProfessorForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam Boolean isExamMadeOrTaken
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("boolean", courseService.setIsExamMadeByProfessor(coursesIdList, isExamMadeOrTaken)))
                        .message("Boolean for isExamMadeByProfessor for Courses with id " + Arrays.toString(coursesIdList) + " set to " + isExamMadeOrTaken)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/setIsExamTakenByStudents")
    public ResponseEntity<Response> setIsExamTakenByStudentsForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam Boolean isExamMadeOrTaken
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("boolean", courseService.setIsExamTakenByStudents(coursesIdList, isExamMadeOrTaken)))
                        .message("Boolean for isExamTakenByStudents for Courses with id " + Arrays.toString(coursesIdList) + " set to " + isExamMadeOrTaken)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/getDegree")
    public ResponseEntity<Response> getDegreesTheCoursesAreIn(
            @RequestParam Long[] coursesIdList
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", courseService.getDegreeTheCourseIsPartOf(coursesIdList)))
                        .message("Degree retrieved for courses with id " + Arrays.toString(coursesIdList))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteCourses(@RequestParam Long[] entitiesIdList) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", courseService.deleteCourses(entitiesIdList)))
                        .message("Entities (courses) with id " + Arrays.toString(entitiesIdList) + " deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/number")
    public ResponseEntity<Response> getNumberCourses() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", courseService.getNumberEntries()))
                        .message("Number of courses retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberPerDepartment")
    public ResponseEntity<Response> getNumberCoursesPerDepartment() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(courseService.getNumberEntriesPerDepartment())
                        .message("Number of courses per department retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberExamMade")
    public ResponseEntity<Response> getNumberCoursesExamMade() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", courseService.getNumberEntriesExamMade()))
                        .message("Number of courses for which the exam is made retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberExamTaken")
    public ResponseEntity<Response> getNumberCoursesExamTaken() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", courseService.getNumberEntriesExamTaken()))
                        .message("Number of courses for which the exam is taken retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/mostStudents")
    public ResponseEntity<Response> getMostStudentsEnrolled() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(courseService.getMostOrLeastStudentsEnrolled(true))
                        .message("Maximum number of students enrolled retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/leastStudents")
    public ResponseEntity<Response> getLeastStudentsEnrolled() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(courseService.getMostOrLeastStudentsEnrolled(false))
                        .message("Minimum number of students enrolled retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
