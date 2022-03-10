package xyz.realraec.universityback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.realraec.universityback.model.Response;
import xyz.realraec.universityback.service.implementation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/entities")
// For the dependency injection to be done properly
@CrossOrigin
@RequiredArgsConstructor
public class EntityController {

    private final EntityServiceImplementation entityService;

    @PutMapping("/refresh")
    public ResponseEntity<Response> refreshEntities(
            @RequestParam Long[] entitiesIdList,
            @RequestParam String entityType) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("entities", entityService.refreshEntities(entitiesIdList, entityType)))
                        .message("Entities with id " + Arrays.toString(entitiesIdList) + " refreshed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteEntities(
            @RequestParam Long[] entitiesIdList,
            @RequestParam String entityType) throws Exception {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        // Either true or false key
                        .data(Map.of("deleted", entityService.deleteEntities(entitiesIdList, entityType)))
                        .message("Entities with id " + Arrays.toString(entitiesIdList) + " deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
