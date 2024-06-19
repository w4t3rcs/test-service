package com.w4t3rcs.test.service.impl;

import com.w4t3rcs.test.dto.RelationDto;
import com.w4t3rcs.test.service.RelationService;
import com.w4t3rcs.test.repository.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationServiceImpl implements RelationService {
    private final RelationRepository relationRepository;

    @Override
    public RelationDto saveRelation(RelationDto relationDto) {
        return RelationDto.fromRelation(relationRepository.save(relationDto.toRelation()));
    }

    @Override
    public List<RelationDto> getRelations() {
        return relationRepository.findAll()
                .stream()
                .map(RelationDto::fromRelation)
                .toList();
    }

    @Override
    public RelationDto getRelation(Long id) {
        return RelationDto.fromRelation(relationRepository.findById(id)
                .orElseThrow());
    }
}
