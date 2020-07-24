package com.evecode.springboot.form.app.models.domain;

import com.evecode.springboot.form.app.validation.IdentificadorRegex;
import com.evecode.springboot.form.app.validation.Requerido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

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

    @NotNull
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
//    @Future
    @Getter @Setter private Date fechaNacimiento;

//    @Valid
    @NotNull
    @Getter @Setter private Pais pais;

    @NotEmpty
//    @Getter @Setter private List<String> roles;
    @Getter @Setter private List<Role> roles;

    @Getter @Setter private Boolean habilitar;

    @NotEmpty
    @Getter @Setter private String genero;

    @Getter @Setter private String valorSecreto;



}
