/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;


import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DanGG
 */
@Controller // anotacion para decir que la clase completa es un controlador
@RequestMapping ("/categoria")//anotacion para crear un ruta de consulta
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    
   @GetMapping ("/listado")
   //Model es un tipo de variable que permite pasar la informacion de una base de datos a un archivo o codigo HTML
   public String listado (Model model) {
       var categorias = categoriaService.getCategorias(false);
       model.addAttribute("categorias", categorias); //con esta intruccion se va pasar todo de var categorias hacia un pagina html que va a conocer una variable llamada "categorias"
       model.addAttribute("totalCategorias", categorias.size());//se va a pasar todo lo que esta en categorias a un variable totalCategorias en un pagina HTML
       return "/categoria/listado";}
   
   @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            categoriaService.save(categoria);
            categoria.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "categoria", 
                            categoria.getIdCategoria()));
        }
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }
    
}
