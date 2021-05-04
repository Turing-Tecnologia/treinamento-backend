package com.turingtecnologia.albatroz.backendalbatroz.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public String exportarRelatorio(String formatoRelatorio, HttpServletResponse response,
                                    @RequestParam(required = false) Map<String, Object> parametros)     
                                    throws JRException, IOException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/RelatorioConsultaRealizada.jasper");

        return setReport(formatoRelatorio, response, parametros, jasperStream);
    }

    public String exportarRelatorioData(String formatoRelatorio, HttpServletResponse response,
                                        @RequestParam Map<String, Object> parametros)
                                        throws JRException, IOException, SQLException, ParseException {
        parametros.put("dataInicial", new SimpleDateFormat("d/MM/yy").parse(parametros.get("dataInicial").toString()));
        parametros.put("dataFinal", new SimpleDateFormat("d/MM/yy").parse(parametros.get("dataFinal").toString()));
        
        InputStream jasperStream = this.getClass().getResourceAsStream("/RelatorioConsultaRealizadaData.jasper");

        return setReport(formatoRelatorio, response, parametros, jasperStream);
    }

    private String setReport(String formatoRelatorio, HttpServletResponse response,
                             @RequestParam Map<String, Object> parametros, InputStream jasperStream)
                             throws JRException, SQLException, IOException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        if(formatoRelatorio.equalsIgnoreCase("pdf")) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=relatorioconsultasrealizadas.pdf");

            final ServletOutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        }

        if(formatoRelatorio.equalsIgnoreCase("xls")) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=relatorioconsultasrealizadas.xls");

            final ServletOutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToXmlStream(jasperPrint, outStream);
        }

        return "Relatório Gerado";
    }
}