package com.w4t3rcs.test.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "relations")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "first_id", unique = true)
    private User first;
    @OneToOne
    @JoinColumn(name = "second_id", unique = true)
    private User second;
}
