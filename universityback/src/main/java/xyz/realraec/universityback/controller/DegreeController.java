package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.service.implementation.DegreeServiceImplementation;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/degrees")
@CrossOrigin
@RequiredArgsConstructor
public class DegreeController {

    private final DegreeServiceImplementation degreeService;


    @GetMapping("/list")
    public ResponseEntity<Response> getDegrees() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degrees", degreeService.list(30)))
                        .message("Degrees retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getDegree(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", degreeService.get(id)))
                        .message("Degree with id " + id + " retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createDegree(@RequestBody @Valid Degree degree) {
        degree = new Degree(degree.getHeading(), degree.getDepartment());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("study", degreeService.create(degree)))
                        .message("Degree created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateDegree(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String heading,
            @RequestParam(required = false) Department department,
            @RequestParam(required = false) Set<Course> courses
    ) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", degreeService.update(id, code, heading, department, courses)))
                        .message("Degree with id " + id + " updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/replace/{id}")
    public ResponseEntity<?> replaceDegree(
            @PathVariable("id") Long id,
            @RequestBody Degree degree
    ) {
        degree = new Degree(degree.getHeading());
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", degreeService.replace(id, degree)))
                        .message("Degree with id " + id + " replaced")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteDegree(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", degreeService.delete(id)))
                        .message("Degree deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/addCourse")
    public ResponseEntity<Response> addCourseForDegrees(
            @RequestParam Long[] degreesIdList,
            @RequestParam String courseCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("course", degreeService.addCourse(degreesIdList, courseCode)))
                        .message("Degrees with id " + Arrays.toString(degreesIdList) + " had course with code " + courseCode + " added")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/removeCourse")
    public ResponseEntity<Response> removeCourseForDegrees(
            @RequestParam Long[] degreesIdList,
            @RequestParam String courseCode
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("course", degreeService.removeCourse(degreesIdList, courseCode)))
                        .message("Degrees with id " + Arrays.toString(degreesIdList) + " had course with code " + courseCode + " removed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/getStudents")
    public ResponseEntity<Response> getStudentsEnrolled(
            @RequestParam Long[] degreesIdList
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("students", degreeService.getStudentsEnrolledInDegree(degreesIdList)))
                        .message("Students retrieved for degrees with id " + Arrays.toString(degreesIdList))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @PutMapping("/getProfessors")
    public ResponseEntity<Response> getProfessorsTeaching(
            @RequestParam Long[] degreesIdList
    ) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("professors", degreeService.getProfessorsTeachingInDegree(degreesIdList)))
                        .message("Professors retrieved for degrees with id " + Arrays.toString(degreesIdList))
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteDegree(@RequestParam Long[] entitiesIdList) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", degreeService.deleteDegrees(entitiesIdList)))
                        .message("Entities (degrees) with id " + Arrays.toString(entitiesIdList) + " deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/number")
    public ResponseEntity<Response> getNumberDegrees() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("number", degreeService.getNumberEntries()))
                        .message("Number of degrees retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/numberPerDepartment")
    public ResponseEntity<Response> getNumberDegreesPerDepartment() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(degreeService.getNumberEntriesPerDepartment())
                        .message("Number of degrees per department retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/mostCourses")
    public ResponseEntity<Response> getMostCoursesAssociated() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(degreeService.getMostOrLeastCoursesAssociated(true))
                        .message("Maximum number of courses associated retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/leastCourses")
    public ResponseEntity<Response> getLeastCoursesAssociated() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(degreeService.getMostOrLeastCoursesAssociated(false))
                        .message("Minimum number of courses associated retrieved")
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
                        .data(degreeService.getMostOrLeastStudentsEnrolled(true))
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
                        .data(degreeService.getMostOrLeastStudentsEnrolled(false))
                        .message("Minimum number of students enrolled retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

}
