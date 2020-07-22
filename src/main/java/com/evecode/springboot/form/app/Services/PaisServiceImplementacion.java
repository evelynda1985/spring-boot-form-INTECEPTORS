package com.evecode.springboot.form.app.Services;

import com.evecode.springboot.form.app.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImplementacion implements PaisService {

    private List<Pais> lista;

    public PaisServiceImplementacion() {
        this.lista = Arrays.asList(
                new Pais(1, "CL",  "Colombia"),
                new Pais(2, "PR", "peru"),
                new Pais(3, "ME", "Mexico"),
                new Pais(4, "US", "USA"));
    }

    @Override
    public List<Pais> listar() {
        return lista;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado = null;
        for(Pais pais : lista){
            if(id == pais.getId()){
                resultado = pais;
                break;
            }
        }
        return resultado;
    }
}
