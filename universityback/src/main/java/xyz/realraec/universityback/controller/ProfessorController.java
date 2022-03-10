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
import java.util.Map;

@RestController
@RequestMapping("/professors")
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorServiceImplementation professorService;

    @GetMapping("/list")
    public ResponseEntity<Response> getProfessors() {
        return ResponseEntity.ok(
                Response.builder()
                        // All the information to be passed
                        .timestamp(LocalDateTime.now())
                        // Where the service (implementation) method is to be found
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
    // A body is needed since you can't save an empty object as a professor
    public ResponseEntity<Response> createProfessor(@RequestBody @Valid Professor professor) throws Exception {
        professor = new Professor(professor.getLastName(), professor.getFirstName(), professor.getGender(),
                professor.getBirthdate(), professor.getEmail(), professor.getPhone());
        // The .created() method also exists but returns a URI, and we want the message as well
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

    // Not included
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


}
