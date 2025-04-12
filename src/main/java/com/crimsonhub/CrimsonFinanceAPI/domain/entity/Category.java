package com.crimsonhub.CrimsonFinanceAPI.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "color_id", nullable = false)
    private Long color;
}
