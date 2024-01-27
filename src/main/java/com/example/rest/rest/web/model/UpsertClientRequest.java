package com.example.rest.rest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertClientRequest {

    @NotBlank(message = "Client name must be filled in")
    @Size(min = 3,max = 30,message = "Client name can't be less {min} and more {max}")
    private String name;
}
