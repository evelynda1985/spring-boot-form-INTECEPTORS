package com.evecode.springboot.form.app.Services;

import com.evecode.springboot.form.app.models.domain.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    @Getter @Setter
    private List<Role> roles;

    public RoleServiceImplementation() {
        this.roles = new ArrayList<>();
        this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
        this.roles.add(new Role(2, "Moderador", "ROLE_MODERATOR"));
        this.roles.add(new Role(3, "Usuario", "ROLE_USER"));
    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {
        Role resultado = null;
        for(Role role: roles ){
            if(id == role.getId()){
                resultado = role;
                break;
            }
        }
        return resultado;
    }
}
