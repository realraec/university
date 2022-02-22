package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.service.implementation.MiscServiceImplementation;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/misc")
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class MiscController {

    private final MiscServiceImplementation miscService;

    @GetMapping("/getDepartments")
    public ResponseEntity<Response> getDepartments() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("departments", miscService.getDepartments()))
                        .message("All departments retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/getGenders")
    public ResponseEntity<Response> getGenders() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("genders", miscService.getGenders()))
                        .message("All genders retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/getDiplomas")
    public ResponseEntity<Response> getDiplomas() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("diplomas", miscService.getDiplomas()))
                        .message("All diplomas retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
