package com.w4t3rcs.test.service;

import com.w4t3rcs.test.dto.RelationDto;

import java.util.List;

public interface RelationService {
    RelationDto saveRelation(RelationDto relationDto);

    List<RelationDto> getRelations();

    RelationDto getRelation(Long id);
}
