package com.evecode.springboot.form.app.Services;

import com.evecode.springboot.form.app.models.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> listar();
    public Role obtenerPorId(Integer id);

}
