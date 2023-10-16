/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;

/**
 *
 * @author DanGG
 */
public interface CategoriaService {
    
    public List<Categoria> getCategorias (boolean activo);//este metodo recibe un parametro booleano y lo que hace es recuperar las categorias que esten activas
        
    
    
}
