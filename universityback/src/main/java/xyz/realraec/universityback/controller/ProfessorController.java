package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.service.implementation.ProfessorServiceImplementation;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/professors")
@CrossOrigin
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorServiceImplementation professorService;


    @GetMapping("/list")
    public ResponseEntity<Response> getProfessors() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professors", professorService.list(50)))
                        .message("Professors retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getProfessor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professor", professorService.get(id)))
                        .message("Professor with id " + id + " retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PostMapping("/create")
    public ResponseEntity<Response> createProfessor(@RequestBody @Valid Professor professor) throws Exception {
        professor = new Professor(professor.getLastName(), professor.getFirstName(), professor.getGender(),
                professor.getBirthdate(), professor.getEmail(), professor.getPhone());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("person", professorService.create(professor)))
                        .message("Professor created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateProfessor(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) LocalDate birthdate,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Double salary,
            @RequestParam(required = false) Double balance,
            @RequestParam(required = false) Integer warnings
    ) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professor", professorService.update(id, code, lastName, firstName, gender, birthdate, level, email, phone, salary, balance, warnings)))
                        .message("Professor with id " + id + " updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/replace/{id}")
    public ResponseEntity<?> replaceProfessor(
            @PathVariable("id") Long id,
            @RequestBody Professor professor
    ) throws Exception {
        professor = new Professor(professor.getLastName(), professor.getFirstName(), professor.getGender());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professor", professorService.replace(id, professor)))
                        .message("Professor with id " + id + " replaced")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProfessor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", professorService.delete(id)))
                        .message("Professor deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/getCourses")
    public ResponseEntity<Response> getCoursesTaught(
            @RequestParam Long[] personsIdList
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("courses", professorService.getCoursesTaughtByProfessor(personsIdList)))
                        .message("Courses retrieved for professors with id " + Arrays.toString(personsIdList))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteProfessors(@RequestParam Long[] entitiesIdList) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", professorService.deleteProfessors(entitiesIdList)))
                        .message("Entities (professors) with id " + Arrays.toString(entitiesIdList) + " deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/number")
    public ResponseEntity<Response> getNumberProfessors() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", professorService.getNumberProfessors()))
                        .message("Number of professors retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberPerLevel")
    public ResponseEntity<Response> getNumberProfessorsPerLevel() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", professorService.getNumberProfessorsPerLevel()))
                        .message("Number of professors per level retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberPerGender")
    public ResponseEntity<Response> getNumberProfessorsPerGender() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(professorService.getNumberProfessorsPerGender())
                        .message("Number of professors per gender retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/mostCourses")
    public ResponseEntity<Response> getMostCoursesTaught() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(professorService.getMostOrLeastCoursesTaught(true))
                        .message("Maximum number of courses taught retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/leastCourses")
    public ResponseEntity<Response> getLeastCoursesTaught() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(professorService.getMostOrLeastCoursesTaught(false))
                        .message("Minimum number of courses taught retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
