/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Producto;
import java.util.List;

/**
 *
 * @author DanGG
 */
public interface ProductoService {
    
    public List<Producto> getProductos (boolean activo);//este metodo recibe un parametro booleano y lo que hace es recuperar las productos que esten activas
        
     // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);
    
    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);
    
    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);
    
    public List<Producto> consultaQuery (double precioInf, double precioSup);
    
}
