package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.service.implementation.StudyServiceImplementation;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/studies")
@CrossOrigin
@RequiredArgsConstructor

public class StudyController {

    private final StudyServiceImplementation studyService;


    @PutMapping("/edit")
    public ResponseEntity<Response> editStudy(
            @RequestParam Long studyId,
            @RequestParam String studyType,
            @RequestParam String studyHeading,
            @RequestParam Department studyDepartment
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("study", studyService.edit(studyId, studyType, studyHeading, studyDepartment)))
                        .message("Object of study with id " + studyId + " was edited")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
