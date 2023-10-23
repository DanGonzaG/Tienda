/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DanGG
 */

 @Service // anotacion para decir que la clase CategoriaServiceImpl es un servicio
//Se crea la clase CategoriaServiceImpl y se le implementa un CategoriaService que es nuestra interfaz
public class CategoriaServiceImpl implements CategoriaService{
    
    
    @Autowired // esta anotacion se usa para enlasar un objeto    
    private CategoriaDao categoriaDao;

    //Se crea un metodo Override y se le elimana el throw 
    @Override
    public List<Categoria> getCategorias(boolean activo) {
       var categorias = categoriaDao.findAll(); //En categroiasDao se depliega opciones se debe de escoger u  findAll porque retorna categoria
        if (activo) {
            categorias.removeIf(e -> !e.getActivo());
        }
    return categorias;}
    
     @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }
    
}
