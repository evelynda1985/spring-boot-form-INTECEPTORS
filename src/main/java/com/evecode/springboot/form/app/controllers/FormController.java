package com.evecode.springboot.form.app.controllers;

import com.evecode.springboot.form.app.models.domain.Usuario;
import com.evecode.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("user")//Se pasar el nombre del objecto
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    //Ventajas es que desacopla el validador del metodo handler
    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.addValidators(validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //fechaNacimiento abajo indica que este es especificamente para ese campo del pojo
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Eve");
        usuario.setApellido("Sanchez");
        usuario.setIdentificador("12.234.567-K");
        model.addAttribute("user", usuario);
        model.addAttribute("titulo", "Resultado del formulario");
        return "form";
    }

    @PostMapping("/form")
    //BindingResult: contiene los mensajes de error y tiene que ponerse justo despues del objeto que se valida
    public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model, SessionStatus status){

//        Se puede evitar este codigo usando initBinder metodo implemente al inicio del controlador
//        validador.validate(usuario, result); //result del BindingResult

        model.addAttribute("titulo", "Resultado del formulario");
        //Manera largar de mandar errores a la vista
//        if(result.hasErrors()){
//            Map<String, String> errores = new HashMap<>();
//            result.getFieldErrors().forEach(err ->{
//                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//            });
//            model.addAttribute("error", errores);
//            return "form";
//        }
        if(result.hasErrors()){
            return "form";
        }
        model.addAttribute("usuario", usuario);
        status.setComplete();//Se eliminar el objecto Usuario(user) de la session
        return "resultado";
    }

}
