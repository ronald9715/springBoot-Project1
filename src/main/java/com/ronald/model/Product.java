package com.ronald.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProduct;
    @ManyToOne //Muchos Productos pueden pertenecer a Una Categoria
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "FK_Product_Category"))
    private Category category;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(columnDefinition = "decimal(6,2)",nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean enabled;
}
