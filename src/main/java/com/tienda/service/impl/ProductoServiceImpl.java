/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service.impl;

import com.tienda.dao.ProductoDao;
import com.tienda.domain.Producto;
import com.tienda.service.ProductoService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DanGG
 */

 @Service // anotacion para decir que la clase ProductoServiceImpl es un servicio
//Se crea la clase ProductoServiceImpl y se le implementa un ProductoService que es nuestra interfaz
public class ProductoServiceImpl implements ProductoService{
    
    
    @Autowired // esta anotacion se usa para enlasar un objeto    
    private ProductoDao productoDao;

    //Se crea un metodo Override y se le elimana el throw 
    @Override
    public List<Producto> getProductos(boolean activo) {
       var productos = productoDao.findAll(); //En categroiasDao se depliega opciones se debe de escoger u  findAll porque retorna producto
        if (activo) {
            productos.removeIf(e -> !e.getActivo());
        }
    return productos;}
    
     @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
}
