package com.ronald.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

// Esta clase se crea para muchos a muchos
@EqualsAndHashCode
@Embeddable // Notacion para que esta clase pueda se utilizada
public class IngressDetailPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_ingress", nullable = false)
    private Ingress ingress;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

}
