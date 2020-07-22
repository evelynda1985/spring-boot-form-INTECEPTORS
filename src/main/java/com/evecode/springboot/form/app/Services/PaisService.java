package com.evecode.springboot.form.app.Services;

import com.evecode.springboot.form.app.models.domain.Pais;

import java.util.List;

public interface PaisService {

    public List<Pais> listar();
    public Pais obtenerPorId(Integer id);
}
