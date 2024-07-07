package com.w4t3rcs.test.controller;

import com.w4t3rcs.test.dto.RelationDto;
import com.w4t3rcs.test.service.RelationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1.0/relations")
@RequiredArgsConstructor
public class RelationController {
    private final RelationService relationService;

    @Operation(summary = "Getting all relations from DB")
    @GetMapping
    public List<RelationDto> getAllRelations() {
        return relationService.getRelations();
    }

    @Operation(summary = "Getting a relation from DB")
    @GetMapping("/{id}")
    public ResponseEntity<RelationDto> getRelationById(@PathVariable Long id) {
        return ResponseEntity.ok(relationService.getRelation(id));
    }

    @Operation(summary = "Creating and saving a relation to DB")
    @PostMapping
    public ResponseEntity<RelationDto> postUser(@Valid @RequestBody RelationDto relationDto) {
        return ResponseEntity.ok(relationService.saveRelation(relationDto));
    }
}
