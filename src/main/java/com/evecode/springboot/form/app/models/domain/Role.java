package com.evecode.springboot.form.app.models.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Role {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String role;

    public Role() {
    }

    public Role(Integer id, String nombre, String role) {
        this.id = id;
        this.nombre = nombre;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }

        if(!(o instanceof Role)){
            return false;
        }

        Role role = (Role) o;
        return this.id != null && this.id.equals(role.getId());
    }

}
