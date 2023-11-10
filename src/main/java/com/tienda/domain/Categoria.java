/*se crea un paquete llamado categoria y despues una java class llamada categorias*/
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
@Table (name = "categoria") // se la anotacion Table para crear la realacion entre la clase Categoria y la tabla categoria, con esto la clase va a tener mapada la tabla Categoria
//se debe de implementar el Serializable, lo hace es salvar la informacion optenida de la clase hacia la base de datos de serial por medio de la red
public class Categoria implements Serializable{
    /* id_categoria INT NOT NULL AUTO_INCREMENT, 
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024), 
  activo bool,*/
    
    private static final long serialVersionUID = 1L;//linea para generar la numeracion de los idCategoria
    
    //---------------------------------------------------------DEFINICION DE LA VARIABLE Y SU RELACION CON LA TABLA
    @Id // con esta anotacion se especifica que idCategoria es una llaverPrimaria
    @GeneratedValue (strategy = GenerationType.IDENTITY)/*anotacion para que genere valores incrementales , dentro de strategy se escoge la opcion GenerationType.IDENTITY
     y con esto logramos hacer que los valores asiganados en idCategoria sean IDENTITY*/
    @Column(name = "id_categoria")//anotacion para decir como se llama el atributo en la base de dato y su relacion con el varible en la clase de java
    private Long idCategoria;
    
    private String descripcion;
    private String rutaImagen;
    private  Boolean activo;
    
    @OneToMany
    @JoinColumn(name = "id_categoria", updatable = false)
    private List<Producto> productos;
    
    
    
}
