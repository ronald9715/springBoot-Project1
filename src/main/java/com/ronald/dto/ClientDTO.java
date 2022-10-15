package com.ronald.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {
    private Integer idClient;
    private String firstName;
    private String lastName;
    private String cardId;
    private String phoneNumber;
    private String email;
    private String address;
}
