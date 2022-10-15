package com.ronald.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ingress {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngress;
    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false)
    private Provider provider;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;
    @Column(length = 10, nullable = false)
    private String serialnumber;
    @Column(nullable = false)
    private boolean enabled;
}
