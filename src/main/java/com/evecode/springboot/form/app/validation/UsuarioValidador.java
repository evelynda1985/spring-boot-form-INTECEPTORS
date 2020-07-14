package com.evecode.springboot.form.app.validation;

import com.evecode.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usuario usuario = (Usuario)o; // cast el objecto Usuario a o
        //paramentros: error que entro por paramentro, campo a validar del pojo y como esta en el archivo messages.properties
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.user.nombre");

//        Otra manera de hacer lo mismo de arriba
//        if(usuario.getNombre().isEmpty()){
//            errors.rejectValue("nombre", "NotEmpty.user.nombre");
//        }

//        Cuando no usamos la anotacoin personalizada en el pojo, para este ejemplo la     @IdentificadorRegex //Esta anotacion es personalizada
//        if(!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")){
//            errors.rejectValue("identificador", "pattern.usuario.identificador");
//        }

    }
}
