package com.evecode.springboot.form.app.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {

    @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")//12.234.567-K
    @Getter @Setter private String identificador;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Getter @Setter private String nombre;

    @NotEmpty
    @Getter @Setter private String apellido;

    @NotEmpty
    @Size(min = 3, max = 8)
    @Getter @Setter private String username;

    @NotEmpty
    @Getter @Setter private String password;

    @NotEmpty(message = "El email con formato incorrecto")
    @Email
    @Getter @Setter private String email;

}
