/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.service;

import jakarta.mail.Quota;

import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author DanGG
 */
public interface ReporteService {
    //Se difine la firma del metodo que genera los reportes, con los siguientes parametros
    //1. reporteÑ es el nombre del archivo del reporte (.jasper)
    //2. Parámetros: un Map que contiene los parametros del reporte...si los ocupa
    //3. tipo. es el tipo de reporte: vPdf, Pdf, Xls, Csv
    
    public ResponseEntity <Resource> generaReporte (
    String reporte,
    Map<String, Object> parametros,
    String tipo) throws IOException;
    
    
}