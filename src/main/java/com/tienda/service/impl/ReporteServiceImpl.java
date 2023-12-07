/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service.impl;

import com.tienda.service.ReporteService;
import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.WriterExporterOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author DanGG
 */
@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private DataSource dataSource;

    @Override
    public ResponseEntity<Resource> generaReporte(
            String reporte,
            Map<String, Object> parametros,
            String tipo) throws IOException {

        try {
            //Se asigna el tipo de pagina a generar
            String estilo = tipo.equalsIgnoreCase("vPdf")? "inline; ":"attachment; ";

            //Se establece la ruta de los reportes
            String reportePath = "reportes";

            //Se define la salida "temporal" del reporte generado
            ByteArrayOutputStream salida = new ByteArrayOutputStream();

            //se establece la fuente para leer el reporte .jasper
            ClassPathResource fuente = new ClassPathResource(
                    reportePath
                    + File.separator
                    + reporte
                    + ".jasper");

            //se define el objeto que lee el archivo de reporte .jasper
            InputStream elReporte = fuente.getInputStream();

            //Se genra el reporte en memoria
            var reporteJasper = JasperFillManager.fillReport(elReporte, parametros, dataSource.getConnection());

            //a partir de geneo la respuesta al usuario
            //MediaType define que tipo de informacion se va devolver, si es PDF el medio se hace en tipo ApplicationPDF, si es Excel se hace en octetos, si es  CSV es texto plano
            MediaType mediaType = null;
            String archivoSalida = "";
            //se debe de decidir cual tipo de reporte se genera
            switch (tipo) {
                case "Pdf","vPdf" -> {//se genera un reporte pdf
                    JasperExportManager.exportReportToPdfStream(reporteJasper, salida);
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
                case "Xls" -> {//se descargara un excel
                    JRXlsExporter paraExcel = new JRXlsExporter();
                    paraExcel.setExporterInput(new SimpleExporterInput(reporteJasper));
                    paraExcel.setExporterOutput(new SimpleOutputStreamExporterOutput(salida));
                    SimpleXlsReportConfiguration configuracion = new SimpleXlsReportConfiguration();
                    configuracion.setDetectCellType(true);
                    configuracion.setCollapseRowSpan(true);
                    paraExcel.setConfiguration(configuracion);
                    paraExcel.exportReport();
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    archivoSalida = reporte + ".xlsx";
                }
                case "Cvs" -> { //Se descara un texto tipo CSV
                    JRCsvExporter paraCsv = new JRCsvExporter();
                    paraCsv.setExporterInput(new SimpleExporterInput(reporteJasper));
                    paraCsv.setExporterOutput(new SimpleWriterExporterOutput(salida));                        
                    paraCsv.exportReport();
                    mediaType = MediaType.TEXT_PLAIN;
                    archivoSalida = reporte + ".csv";
                }
            }
            
            //a partir de aca se realiza la respuesta al usuario
            byte[]data=salida.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition", estilo+"filename=\""+archivoSalida+"\"");
            return ResponseEntity
                    .ok().headers(headers)
                    .contentLength(data.length)
                    .contentType(mediaType)
                    .body(
                            new InputStreamResource(
                                    new ByteArrayInputStream(data)));
            

        } catch (JRException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
