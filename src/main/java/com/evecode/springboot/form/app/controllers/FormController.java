package com.evecode.springboot.form.app.controllers;

import com.evecode.springboot.form.app.Services.PaisService;
import com.evecode.springboot.form.app.Services.RoleService;
import com.evecode.springboot.form.app.editors.NombreMayusculaEditor;
import com.evecode.springboot.form.app.editors.PaisPropertyEditor;
import com.evecode.springboot.form.app.editors.RolesEditor;
import com.evecode.springboot.form.app.models.domain.Pais;
import com.evecode.springboot.form.app.models.domain.Role;
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
import java.util.*;

@Controller
@SessionAttributes("user")//Se pasar el nombre del objecto
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @Autowired
    private PaisService paisService;

    @Autowired
    private PaisPropertyEditor paisPropertyEditor;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolesEditor rolesEditor;

    //Ventajas es que desacopla el validador del metodo handler
    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.addValidators(validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        //fechaNacimiento abajo indica que este es especificamente para ese campo del pojo
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
        // es lo mismo de arriba pero con una clase propia de nosotros, y se vuelve mayuscula todos los strings
//        binder.registerCustomEditor(String.class, new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());

        binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);

        binder.registerCustomEditor(Role.class, "roles", rolesEditor);
    }

//    @ModelAttribute("listaPaises")
//    public List<Pais> listaPaises(){
//        return Arrays.asList(
//                new Pais(1, "CL",  "Colombia"),
//                new Pais(2, "PR", "peru"),
//                new Pais(3, "ME", "Mexico"),
//                new Pais(4, "US", "USA"));
//    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises(){
        return paisService.listar();
    }

    @ModelAttribute("paises")
    public List<String> paises(){
        return Arrays.asList("Colombia", "peru", "Mexico", "USA");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap(){
        Map<String, String> paises = new HashMap<>();
        paises.put("ES","Espana");
        paises.put("ME","Mexico");
        paises.put("CL","Colombia");
        paises.put("US","Estados Unidos");
        return paises;
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_MODERATOR");
        roles.add("ROLE_USER");
        return roles;
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap(){
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN","Administrador");
        roles.put("ROLE_MODERATOR","Moderador");
        roles.put("ROLE_USER","Usuario");
        return roles;
    }

    @ModelAttribute("listaRolesObjecto")
    public List<Role> listaRolesObjecto(){
        return this.roleService.listar();
    }

    @ModelAttribute("genero")
    public List<String> genero(){
        return Arrays.asList("Hombre", "Mujer");
    }


    @GetMapping("/form")
    public String form(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Eve");
        usuario.setApellido("Sanchez");
        usuario.setIdentificador("12.234.567-K");
        usuario.setHabilitar(true);
        usuario.setValorSecreto("Algun valor secreto");
        usuario.setPais(new Pais(3, "ME", "Mexico"));
        usuario.setRoles(Arrays.asList(new Role(3, "Usuario", "ROLE_USER")));
        model.addAttribute("user", usuario); //sobrenombre de usuario para la vista
        model.addAttribute("titulo", "Resultado del formulario");
        return "form";
    }

    @PostMapping("/form")
    //BindingResult: contiene los mensajes de error y tiene que ponerse justo despues del objeto que se valida
    public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model){

//        Se puede evitar este codigo usando initBinder metodo implemente al inicio del controlador
//        validador.validate(usuario, result); //result del BindingResult

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
            model.addAttribute("titulo", "Resultado del formulario");
            return "form";
        }

//        return "resultdo"; Si lo dejamos asi se ve a reenviar el formulario cada vez que se haga refrescar
        return "redirect:/ver";
    }

    @GetMapping("/ver")//aqui se puede pasar el id del usuario, en este caso se esta pasando el usuario que esta guardado en la session
    public String ver(@SessionAttribute(name="user", required = false) Usuario usuario, Model model, SessionStatus status){

        if(usuario == null){
            return "redirect:/form";
        }

        model.addAttribute("titulo", "Resultado del formulario");
        model.addAttribute("usuario", usuario);
        status.setComplete();//Se eliminar el objecto Usuario(user) de la session
        return "resultado";
    }

}
