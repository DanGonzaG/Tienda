/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DanGG
 */
@Controller // anotacion para decir que la clase completa es un controlador
@RequestMapping("/pruebas")//anotacion para crear un ruta de consulta
public class PruebasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    //Model es un tipo de variable que permite pasar la informacion de una base de datos a un archivo o codigo HTML
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos); //con esta intruccion se va pasar todo de var productos hacia un pagina html que va a conocer una variable llamada "productos"
        model.addAttribute("totalProductos", productos.size());//se va a pasar todo lo que esta en productos a un variable totalProductos en un pagina HTML

        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Model model, Categoria categoria) {
        var productos = categoriaService.getCategoria(categoria).getProductos();
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }

}
