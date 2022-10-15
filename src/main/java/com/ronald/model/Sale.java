package com.ronald.model;

import com.ronald.dto.IProcedureDTO;
import com.ronald.dto.ProcedureDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@SqlResultSetMapping(
        name = "Procedure.ProcedureDTO",
        classes = @ConstructorResult(targetClass = ProcedureDTO.class,
        columns = {@ColumnResult(name ="quantityFn", type = Integer.class),
                @ColumnResult(name="datetime", type = String.class)

        })
)
@NamedNativeQuery(
        name="Sale.fn_sales",
        query="select * from fn_sales()",
        resultSetMapping = "Procedure.ProcedureDTO"
)
////////////////////////////////////// Store Procedure sin Parametros
@NamedStoredProcedureQuery(
        name = "getFnSales",
        procedureName = "fn_sales",
        resultClasses = IProcedureDTO.class
)
////////////////////////////////////Store Procedure con Parametros
@NamedStoredProcedureQuery(
        name = "getFnSales2",
        procedureName = "fn_salesParameter2",
        resultClasses = IProcedureDTO.class,
        parameters = {
                @StoredProcedureParameter(name = "p_id_client", type = Integer.class, mode = ParameterMode.IN)
        }
)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;
    @ManyToOne//Muchas Ventas pertenecen a un Cliente
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_Sale_Client"))
    private Client client;
    @ManyToOne//Muchas Ventas son realizadas por un Usuario
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_Sale_User"))
    private User user;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;
    //Para expresar una relacion Logica Maestro - Detalle
    //Cascade.ALL si se le inserta algo al maestro tambien se le debe insertar al hijo
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)// fetch = FetchType.EAGER)//(sale es el Atributo que esta dentro de SaleDetail)//Una Venta puede tener muchas VentasDetalle
    //To do lo que pasa a la Cabecera le pasa a Detalle//SI hay Cabecera hay Detalle
    private List<SaleDetail> details;
    @Column(nullable = false)
    private boolean enabled;

}
