/*se crea un nuevo java.class en este se crea todo lo necesario para hacer el lenguaje pueda ser cambiado*/

package com.tienda;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 *
 * @author DanGG
 */
@Configuration //colocar un @ se una para hacer anotaciones despues importa la clases configuration
public class ProjectConfig implements WebMvcConfigurer { //la clase configuracion implementa WebMvcConfigurer y despues de importa la clase

    @Bean //se crea la anotacion Bean, Bean es una seña para manterner el metodo activo mientra se este ejecutando, este bean tambien debe de importarse
    public LocaleResolver localeResolver() {
        /* se crea el metodo LocaleResolver con el nombre localeResolver despues de debe de importar la clase LocaleResolver se debe de
       de importar el segundo el que tiene el nombre web.servlet.LocaleResolver*/
        var slr = new SessionLocaleResolver();//var es una instancia que se asigna el nombre slr, lo que hace es un RESOLUTOR DE SESIONES DE UBICACION, tambien de debe de importar
        slr.setDefaultLocale(Locale.getDefault());//se crea una sesion local por defecto dentro de los parentesis de coloca Locale.getDefault, esta tambien debe de importarse
        slr.setTimeZoneAttributeName("session.current.locale");//esta instruccion indica cual es la ubicacion que reporta el browser
        slr.setTimeZoneAttributeName("session.current.timezone");//Indica cual es la zona horaria que reporta el browser
        return slr;
    }//se retorna la slr

    @Bean//se crea otra anotacion de Bean
    public LocaleChangeInterceptor localcChangeInterceptor() {//se crea un metodo de tipo LocaleChageInterceptor con el nombre y se debe de importra
        var lci = new LocaleChangeInterceptor(); // de instacia otro var pero con el un new LocaleChangeInterceptor 
        lci.setParamName("lang");//de debe de definir un nombre de variable
        return lci;
    }// se retorna lci

    @Override//se hace una anotacion override
    public void addInterceptors(InterceptorRegistry registro) {
        /*se crea un metodo void con el nomobre addInterceptors, dentro de los parametos del metodo se crea un 
       interceptorRegistry (debe de ser importado) con el nombre registro */
        registro.addInterceptor(localcChangeInterceptor()); //La funsion de este metodo es agregar un registro al interceptor y el interceptor que se agrega es el localcChangeInterceptor()
    }

    /* Los siguiente mÃ©todos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**",
                        "/carrito/**", "/pruebas/**", "/reportes/**",
                        "/registro/**", "/js/**", "/webjars/**")
                .permitAll()
                .requestMatchers(
                        "/producto/nuevo", "/producto/guardar",
                        "/producto/modificar/**", "/producto/eliminar/**",
                        "/categoria/nuevo", "/categoria/guardar",
                        "/categoria/modificar/**", "/categoria/eliminar/**",
                        "/usuario/nuevo", "/usuario/guardar",
                        "/usuario/modificar/**", "/usuario/eliminar/**",
                        "/reportes/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    /* El siguiente mÃ©todo se utiliza para completar la clase no es 
    realmente funcional, la prÃ³xima semana se reemplaza con usuarios de BD */
//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("juan")
//                .password("{noop}123")
//                .roles("USER", "VENDEDOR", "ADMIN")
//                .build();
//        UserDetails sales = User.builder()
//                .username("rebeca")
//                .password("{noop}456")
//                .roles("USER", "VENDEDOR")
//                .build();
//        UserDetails user = User.builder()
//                .username("pedro")
//                .password("{noop}789")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, sales, admin);
//    }
    
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
