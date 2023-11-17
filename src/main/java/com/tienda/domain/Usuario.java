/*se crea un paquete llamado usuario y despues una java class llamada usuarios*/
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

/**
 *
 * @author DanGG
 */
@Data // se hace la anotacio de data para asi espificar que la clase va a tener datos, se debe de importar la lombok.Data
@Entity // se hace una anotacion Entity, esto dice que la clase va a ser un entidad de una tabla de base de datos, se de importar jakarta.persistence.*
@Table (name = "usuario") // se la anotacion Table para crear la realacion entre la clase Usuario y la tabla usuario, con esto la clase va a tener mapada la tabla Usuario
//se debe de implementar el Serializable, lo hace es salvar la informacion optenida de la clase hacia la base de datos de serial por medio de la red
public class Usuario implements Serializable{
    /* id_usuario INT NOT NULL AUTO_INCREMENT, 
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024), 
  activo bool,*/
    
    private static final long serialVersionUID = 1L;//linea para generar la numeracion de los idUsuario
    
    //---------------------------------------------------------DEFINICION DE LA VARIABLE Y SU RELACION CON LA TABLA
    @Id // con esta anotacion se especifica que idUsuario es una llaverPrimaria
    @GeneratedValue (strategy = GenerationType.IDENTITY)/*anotacion para que genere valores incrementales , dentro de strategy se escoge la opcion GenerationType.IDENTITY
     y con esto logramos hacer que los valores asiganados en idUsuario sean IDENTITY*/
   
    private Long idUsuario;    
    private String username;        
    private String password;
        private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;    
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles;
    
    
    
}
