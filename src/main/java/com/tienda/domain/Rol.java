/*se crea un paquete llamado rol y despues una java class llamada rols*/
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;


@Data // se hace la anotacio de data para asi espificar que la clase va a tener datos, se debe de importar la lombok.Data
@Entity // se hace una anotacion Entity, esto dice que la clase va a ser un entidad de una tabla de base de datos, se de importar jakarta.persistence.*
@Table (name = "rol") // se la anotacion Table para crear la realacion entre la clase Rol y la tabla rol, con esto la clase va a tener mapada la tabla Rol
//se debe de implementar el Serializable, lo hace es salvar la informacion optenida de la clase hacia la base de datos de serial por medio de la red
public class Rol implements Serializable{
    /* id_rol INT NOT NULL AUTO_INCREMENT, 
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024), 
  activo bool,*/
    
    private static final long serialVersionUID = 1L;//linea para generar la numeracion de los idRol
    
    //---------------------------------------------------------DEFINICION DE LA VARIABLE Y SU RELACION CON LA TABLA
    @Id // con esta anotacion se especifica que idRol es una llaverPrimaria
    @GeneratedValue (strategy = GenerationType.IDENTITY)/*anotacion para que genere valores incrementales , dentro de strategy se escoge la opcion GenerationType.IDENTITY
     y con esto logramos hacer que los valores asiganados en idRol sean IDENTITY*/   
    private Long idRol;    
    private String nombre;    
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    
    
}
