package com.evecode.springboot.form.app.models.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class Pais {

//    @NotNull
    @Getter @Setter private Integer id;

    @Getter @Setter private String codigo;
    @Getter @Setter private String nombre;

    public Pais() {
    }
    
    public Pais(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
