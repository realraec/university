package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.enumeration.Diploma;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.service.implementation.StudentServiceImplementation;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/students")
@CrossOrigin
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImplementation studentService;


    @GetMapping("/list")
    public ResponseEntity<Response> getStudents() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("students", studentService.list(100)))
                        .message("Students retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("student", studentService.get(id)))
                        .message("Student with id " + id + " retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PostMapping("/create")
    public ResponseEntity<Response> createStudent(@RequestBody @Valid Student student) throws Exception {
        student = new Student(student.getLastName(), student.getFirstName(), student.getGender(),
                student.getBirthdate(), student.getEmail(), student.getPhone());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("person", studentService.create(student)))
                        .message("Student created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateStudent(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) LocalDate birthdate,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) Degree majorDegree,
            @RequestParam(required = false) Degree minorDegree,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Double tuition,
            @RequestParam(required = false) Double balance,
            @RequestParam(required = false) Integer credits,
            @RequestParam(required = false) Diploma diploma,
            @RequestParam(required = false) Integer warnings
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("student", studentService.update(id, code, lastName, firstName, gender, birthdate, level, majorDegree, minorDegree, email, phone, tuition, balance, credits, diploma, warnings)))
                        .message("Student with id " + id + " updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/replace/{id}")
    public ResponseEntity<?> replaceStudent(
            @PathVariable("id") Long id,
            @RequestBody Student student
    ) throws Exception {
        student = new Student(student.getLastName(), student.getFirstName(), student.getGender());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("student", studentService.replace(id, student)))
                        .message("Student with id " + id + " replaced")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", studentService.delete(id)))
                        .message("Student deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/setNewMinorDegree")
    public ResponseEntity<Response> setNewMinorDegreeForStudents(
            @RequestParam Long[] studentsIdList,
            @RequestParam String degreeCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", studentService.setNewMinorDegree(studentsIdList, degreeCode)))
                        .message("Students with id " + Arrays.toString(studentsIdList) + " set with new minor degree with code " + degreeCode)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/setNewMajorDegree")
    public ResponseEntity<Response> setNewMajorDegreeForStudents(
            @RequestParam Long[] studentsIdList,
            @RequestParam String degreeCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", studentService.setNewMajorDegree(studentsIdList, degreeCode)))
                        .message("Students with id " + Arrays.toString(studentsIdList) + " set with new major degree with code " + degreeCode)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/giveCredits")
    public ResponseEntity<Response> giveCreditsToStudents(
            @RequestParam Long[] studentsIdList,
            @RequestParam Integer credits
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("credits", studentService.giveCredits(studentsIdList, credits)))
                        .message("Students with id " + Arrays.toString(studentsIdList) + " given " + credits + " credits")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/giveDiploma")
    public ResponseEntity<Response> giveCreditsToStudents(
            @RequestParam Long[] studentsIdList,
            @RequestParam Diploma diploma
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("diploma", studentService.giveDiploma(studentsIdList, diploma)))
                        .message("Students with id " + Arrays.toString(studentsIdList) + " given " + diploma + " diploma")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/getCourses")
    public ResponseEntity<Response> getCoursesEnrolledIn(
            @RequestParam Long[] personsIdList
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("courses", studentService.getCourses(personsIdList)))
                        .message("Courses retrieved for students with id " + Arrays.toString(personsIdList))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteStudents(@RequestParam Long[] entitiesIdList) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", studentService.deleteStudents(entitiesIdList)))
                        .message("Entities (students) with id " + Arrays.toString(entitiesIdList) + " deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/number")
    public ResponseEntity<Response> getNumberStudents() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", studentService.getNumberStudents()))
                        .message("Number of students retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberPerLevel")
    public ResponseEntity<Response> getNumberStudentsPerLevel() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", studentService.getNumberStudentsPerLevel()))
                        .message("Number of students per level retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberPerGender")
    public ResponseEntity<Response> getNumberStudentsPerGender() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(studentService.getNumberStudentsPerGender())
                        .message("Number of students per gender retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/mostCourses")
    public ResponseEntity<Response> getMostCoursesTaken() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(studentService.getMostOrLeastCoursesTaken(true))
                        .message("Maximum number of courses taken retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/leastCourses")
    public ResponseEntity<Response> getLeastCoursesTaken() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(studentService.getMostOrLeastCoursesTaken(false))
                        .message("Minimum number of courses taken retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
