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
import java.util.Map;

@RestController
@RequestMapping("/students")
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImplementation studentService;

    @GetMapping("/list")
    public ResponseEntity<Response> getStudents() {
        return ResponseEntity.ok(
                Response.builder()
                        // All the information to be passed
                        .timestamp(LocalDateTime.now())
                        // Where the service (implementation) method is to be found
                        .data(Map.of("students", studentService.list(50)))
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
    // A body is needed since you can't save an empty object as a student
    public ResponseEntity<Response> createStudent(@RequestBody @Valid Student student) throws Exception {
        student = new Student(student.getLastName(), student.getFirstName(), student.getGender());
        // The .created() method also exists but returns a URI, and we want the message as well
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("student", studentService.create(student)))
                        .message("Student created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    // Not included
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
            //Long id, String code, String lastName, String firstName, Gender gender, LocalDate birthdate, Integer level, Degree majorDegree,
            // Degree minorDegree, String email, String phone, Double tuition, Double balance, Integer credits, Diploma diploma, Integer warnings
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

}
