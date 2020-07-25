package com.evecode.springboot.form.app.interceptores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    //Add a logger
    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //aplicar el handler solo para el get del /form
        if(request.getMethod().equalsIgnoreCase("post")){
            return true;
        }

        if(handler instanceof HandlerMethod){
            HandlerMethod metodo = (HandlerMethod)handler;
            logger.info("Es un metodo del controlador: " + metodo.getMethod().getName());
        }

        logger.info("TiempoTranscurridoInterceptor: PreHandle() entrando...");
        //logger para saber que handler se esta inteceptando
        logger.info("Inteceptando: " + handler);
        long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);

        //simular una demora
        Random random = new Random();
        Integer demora = random.nextInt(500);
        Thread.sleep(demora);

        //        podemos redirigir de la siguiente manera
//        response.sendRedirect(request.getContextPath().concat("/login"));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //aplicar el handler solo para el get del /form
        if(!request.getMethod().equalsIgnoreCase("post")) {

            long tiempoFin = System.currentTimeMillis();
            long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
            long tiempoTranscurrido = tiempoFin - tiempoInicio;

            //validar si no saca error, porque no todos los modelAndView son metodos del controlador
            if (modelAndView != null && handler instanceof HandlerMethod) {
                //Es como se va a pasar a la vista tiempoTranscurrido
                modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
            }

            logger.info("TiempoTranscurrido: " + tiempoTranscurrido + " milisegundos");
            logger.info("TiempoTranscurridoInterceptor: PostHandle() saliendo...");
        }
    }
}
