package com.evecode.springboot.form.app.models.domain;

import com.evecode.springboot.form.app.validation.IdentificadorRegex;
import com.evecode.springboot.form.app.validation.Requerido;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

public class Usuario {

    //@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")//12.234.567-K
    @IdentificadorRegex //Esta anotacion es personalizada
    @Getter @Setter private String identificador;

    //@NotEmpty(message = "El nombre no puede ser vacio")
    @Getter @Setter private String nombre;

//    @NotEmpty
    @Requerido
    @Getter @Setter private String apellido;

    @NotBlank // se pone @NotEmpty or @NotBlank pero no ambos, por que se valida los ambos
    @Size(min = 3, max = 8)
    @Getter @Setter private String username;

//    @NotEmpty
    @Requerido
    @Getter @Setter private String password;

    @NotEmpty(message = "El email con formato incorrecto")
    @Email
    @Getter @Setter private String email;

    @NotNull //Validacion para objetos
    @Min(5)
    @Max(5000)
    @Getter @Setter private Integer cuenta;

}
