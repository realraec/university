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
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class DegreeController {

    private final DegreeServiceImplementation degreeService;

    @GetMapping("/list")
    public ResponseEntity<Response> getDegrees() {
        return ResponseEntity.ok(
                Response.builder()
                        // All the information to be passed
                        .timestamp(LocalDateTime.now())
                        // Where the service (implementation) method is to be found
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
    // A body is needed since you can't save an empty object as a server
    public ResponseEntity<Response> createDegree(@RequestBody @Valid Degree degree) {
        degree = new Degree(degree.getHeading(), degree.getDepartment());
        // The .created() method also exists but returns a URI, and we want the message as well
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("degree", degreeService.create(degree)))
                        .message("Degree created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    // Not included
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


        /*
        // Several parameters, so "path =" needed
        // By default would return JSON content, now an image of type PNG
        @GetMapping(path = "/images/{fileName}", produces = IMAGE_PNG_VALUE)
        public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
            // No need for a response, just for a byte array of the image
            return Files.readAllBytes(Paths.get("C:/Desktop/Projects/images/" + fileName));
        }*/

}
