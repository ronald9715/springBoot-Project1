package com.ronald.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(IngressDetailPK.class)
public class IngressDetail {
    @Id
    private Ingress ingress;
    @Id
    private Product product;
    @Column(nullable = false)
    private short quantity;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double  cost;
}
