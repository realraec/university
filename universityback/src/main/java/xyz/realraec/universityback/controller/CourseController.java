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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/courses")
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImplementation courseService;

    @GetMapping("/list")
    public ResponseEntity<Response> getCourses() {
        return ResponseEntity.ok(
                Response.builder()
                        // All the information to be passed
                        .timestamp(LocalDateTime.now())
                        // Where the service (implementation) method is to be found
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
    // A body is needed since you can't save an empty object as a server
    public ResponseEntity<Response> createCourse(@RequestBody @Valid Course course) {
        course = new Course(course.getHeading());
        // The .created() method also exists but returns a URI, and we want the message as well
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("course", courseService.create(course)))
                        .message("Course created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    // Not included
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


    /*@PutMapping("/setNewProfessor/{id}")
    public ResponseEntity<Response> setNewProfessorForCourse(
            @PathVariable("id") Long id,
            @RequestParam String professorCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professor", courseService.setNewProfessor(id, professorCode)))
                        .message("Course with id " + id + " set with new professor with code " + professorCode)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }*/


    @PutMapping("/setNewProfessorMultiple")
    public ResponseEntity<Response> setNewProfessorForCourses(
            @RequestParam Long[] coursesIdList,
            @RequestParam String professorCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professor", courseService.setNewProfessorMultiple(coursesIdList, professorCode)))
                        .message("Courses with id " + Arrays.toString(coursesIdList) + " set with new professor with code " + professorCode)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/getStudents/{id}")
    public ResponseEntity<Response> getStudentsForCourses(
            @PathVariable("id") Long[] courseId
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        // All the information to be passed
                        .timestamp(LocalDateTime.now())
                        // Where the service (implementation) method is to be found
                        .data(Map.of("students", courseService.getStudents(courseId)))
                        .message("Students retrieved for course with id " + Arrays.toString(courseId))
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

    @PutMapping("/setIsExamMadeByProfessorMultiple")
    public ResponseEntity<Response> setIsExamMadeByProfessorMultiple(
            @RequestParam Long[] coursesIdList,
            @RequestParam Boolean isExamMadeByProfessor
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("boolean", courseService.setIsExamMadeByProfessor(coursesIdList, isExamMadeByProfessor)))
                        .message("Boolean for isExamMadeByProfessor for Courses with id " + Arrays.toString(coursesIdList) + " set to " + isExamMadeByProfessor)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/setIsExamTakenByStudentsMultiple")
    public ResponseEntity<Response> setIsExamTakenByStudentsMultiple(
            @RequestParam Long[] coursesIdList,
            @RequestParam Boolean isExamTakenByStudents
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("boolean", courseService.setIsExamTakenByStudents(coursesIdList, isExamTakenByStudents)))
                        .message("Boolean for isExamTakenByStudents for Courses with id " + Arrays.toString(coursesIdList) + " set to " + isExamTakenByStudents)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }





}
