/*se crea un nuevo java.class en este se crea todo lo necesario para hacer el lenguaje pueda ser cambiado*/
package com.tienda;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author DanGG
 */
@Configuration //colocar un @ se una para hacer anotaciones despues importa la clases configuration
public class ProjectConfig implements WebMvcConfigurer{ //la clase configuracion implementa WebMvcConfigurer y despues de importa la clase
   @Bean //se crea la anotacion Bean, Bean es una seña para manterner el metodo activo mientra se este ejecutando, este bean tambien debe de importarse
   public LocaleResolver localeResolver (){ /* se crea el metodo LocaleResolver con el nombre localeResolver despues de debe de importar la clase LocaleResolver se debe de
       de importar el segundo el que tiene el nombre web.servlet.LocaleResolver*/
       var slr = new SessionLocaleResolver();//var es una instancia que se asigna el nombre slr, lo que hace es un RESOLUTOR DE SESIONES DE UBICACION, tambien de debe de importar
       slr.setDefaultLocale(Locale.getDefault());//se crea una sesion local por defecto dentro de los parentesis de coloca Locale.getDefault, esta tambien debe de importarse
       slr.setTimeZoneAttributeName("session.current.locale");//esta instruccion indica cual es la ubicacion que reporta el browser
       slr.setTimeZoneAttributeName("session.current.timezone");//Indica cual es la zona horaria que reporta el browser
   return slr;}//se retorna la slr
    
   @Bean//se crea otra anotacion de Bean
   public LocaleChangeInterceptor localcChangeInterceptor(){//se crea un metodo de tipo LocaleChageInterceptor con el nombre y se debe de importra
       var lci = new LocaleChangeInterceptor(); // de instacia otro var pero con el un new LocaleChangeInterceptor 
       lci.setParamName("lang");//de debe de definir un nombre de variable
   return lci;}// se retorna lci
   
   
   @Override//se hace una anotacion override
   public void addInterceptors (InterceptorRegistry registro){ /*se crea un metodo void con el nomobre addInterceptors, dentro de los parametos del metodo se crea un 
       interceptorRegistry (debe de ser importado) con el nombre registro */      
       registro.addInterceptor(localcChangeInterceptor()); //La funsion de este metodo es agregar un registro al interceptor y el interceptor que se agrega es el localcChangeInterceptor()
   } 
}
