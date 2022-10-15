package com.ronald.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Provider {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvider;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean enabled;
}
