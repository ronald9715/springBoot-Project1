package com.ronald.model;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity //JPA para establecer que es una entidad
public class Category {
    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean enabled;
}
