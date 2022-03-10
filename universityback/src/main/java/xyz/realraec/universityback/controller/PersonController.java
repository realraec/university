package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.service.implementation.PersonServiceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/persons")
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class PersonController {

    private final PersonServiceImplementation personService;


    @PutMapping("/promote")
    public ResponseEntity<Response> promotePersons(
            @RequestParam Long[] personsIdList,
            @RequestParam String personType
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("levels", personService.promote(personsIdList, personType)))
                        .message("Persons with id " + Arrays.toString(personsIdList) + " promoted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/demote")
    public ResponseEntity<Response> demotePersons(
            @RequestParam Long[] personsIdList,
            @RequestParam String personType
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("levels", personService.demote(personsIdList, personType)))
                        .message("Persons with id " + Arrays.toString(personsIdList) + " demoted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/giveWarning")
    public ResponseEntity<Response> giveWarningToPersons(
            @RequestParam Long[] personsIdList,
            @RequestParam String personType
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("warnings", personService.giveWarning(personsIdList, personType)))
                        .message("Persons with id " + Arrays.toString(personsIdList) + " were given a warning")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/kickOut")
    public ResponseEntity<Response> kickOutPersons(
            @RequestParam Long[] personsIdList,
            @RequestParam String personType
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("maxWarning", personService.kickOut(personsIdList, personType)))
                        .message("Persons with id " + Arrays.toString(personsIdList) + " were kicked out")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/edit")
    public ResponseEntity<Response> editPerson(
            @RequestParam Long personId,
            @RequestParam String personType,
            @RequestParam String personLastName,
            @RequestParam String personFirstName,
            @RequestParam Gender personGender,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate personBirthdate,
            @RequestParam String personEmail,
            @RequestParam String personPhone
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("person", personService.edit(personId, personType, personLastName, personFirstName, personGender, personBirthdate,
                                personEmail, personPhone)))
                        .message("Person with id " + personId + " was edited")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
