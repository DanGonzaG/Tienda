/*se crea un paquete llamado producto y despues una java class llamada productos*/
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

/**
 *
 * @author DanGG
 */
@Data // se hace la anotacio de data para asi espificar que la clase va a tener datos, se debe de importar la lombok.Data
@Entity // se hace una anotacion Entity, esto dice que la clase va a ser un entidad de una tabla de base de datos, se de importar jakarta.persistence.*
@Table(name = "producto") // se la anotacion Table para crear la realacion entre la clase Producto y la tabla producto, con esto la clase va a tener mapada la tabla Producto
//se debe de implementar el Serializable, lo hace es salvar la informacion optenida de la clase hacia la base de datos de serial por medio de la red
public class Producto implements Serializable {

    /* id_producto INT NOT NULL AUTO_INCREMENT, 
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024), 
  activo bool,*/

    private static final long serialVersionUID = 1L;//linea para generar la numeracion de los idProducto

    //---------------------------------------------------------DEFINICION DE LA VARIABLE Y SU RELACION CON LA TABLA
    @Id // con esta anotacion se especifica que idProducto es una llaverPrimaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*anotacion para que genere valores incrementales , dentro de strategy se escoge la opcion GenerationType.IDENTITY
     y con esto logramos hacer que los valores asiganados en idProducto sean IDENTITY*/
    @Column(name = "id_producto")//anotacion para decir como se llama el atributo en la base de dato y su relacion con el varible en la clase de java

    /*id_producto INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  descripcion VARCHAR(30) NOT NULL,  
  detalle VARCHAR(1600) NOT NULL, 
  precio double,
  existencias int,  
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto),
  foreign key fk_producto_caregoria (id_categoria) references categoria(id_categoria)  
)*/
    private Long idProducto;
    //private int idCategoria;
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;

    @ManyToOne
    @JoinColumn (name="id_categoria")
    Categoria categoria;
    
}
