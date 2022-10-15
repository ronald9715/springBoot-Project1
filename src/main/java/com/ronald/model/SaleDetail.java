package com.ronald.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SaleDetail {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaleDetail;
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
    @Column(nullable = false)
    private short quantity;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double salePrice;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double discount;
}
