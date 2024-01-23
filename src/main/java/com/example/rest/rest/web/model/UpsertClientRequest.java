package com.example.rest.rest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertClientRequest {

    @NotBlank(message = "Client name must be filled in")
    @Size(min = 3,max = 30,message = "Client name can't be less {min} and more {max}")
    private String name;
}
