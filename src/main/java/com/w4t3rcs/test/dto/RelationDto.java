package com.w4t3rcs.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.w4t3rcs.test.entity.Relation;
import jakarta.validation.Valid;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelationDto {
    private Long id;
    private UserDto first;
    private UserDto second;

    public static RelationDto fromRelation(@Valid Relation relation) {
        return new RelationDto(relation.getId(), UserDto.fromUser(relation.getFirst()), UserDto.fromUser(relation.getSecond()));
    }

    @Valid
    public Relation toRelation() {
        return new Relation(this.getId(), this.getFirst().toUser(), this.getSecond().toUser());
    }
}
