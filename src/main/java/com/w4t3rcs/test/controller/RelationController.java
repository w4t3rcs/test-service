package com.w4t3rcs.test.controller;

import com.w4t3rcs.test.dto.RelationDto;
import com.w4t3rcs.test.service.RelationService;
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

    @GetMapping
    public List<RelationDto> getAllRelations() {
        return relationService.getRelations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelationDto> getRelationById(@PathVariable Long id) {
        return ResponseEntity.ok(relationService.getRelation(id));
    }

    @PostMapping
    public ResponseEntity<RelationDto> postUser(@Valid @RequestBody RelationDto relationDto) {
        return ResponseEntity.ok(relationService.saveRelation(relationDto));
    }
}
