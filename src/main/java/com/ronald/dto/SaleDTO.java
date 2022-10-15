package com.ronald.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {
    private Integer idSale;
    @JsonIncludeProperties(value = {"idClient"})
    private ClientDTO client;
    @JsonIncludeProperties(value = {"idUser","userName"})
    private UserDTO user;
    private LocalDateTime dateTime;
    private double total;
    private double tax;
    private boolean enabled;
    @JsonManagedReference
    private List<SaleDetailDTO> details;
}
