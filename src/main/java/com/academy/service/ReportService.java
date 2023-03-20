package com.academy.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.academy.Dao.AlunoDao;
import com.academy.model.Aluno;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class ReportService {

	@Autowired
	private AlunoDao alunoDao;
	
public void getReportsinPDF(HttpServletResponse response) throws JRException, IOException {
		
		// compile report
		InputStream jasperStream =(InputStream) this.getClass().getResourceAsStream("/studants.jasper");
		// addd params / adicionar parametros.
		Map<String, Object> params = new HashMap<>();
		params.put("id", "id");
		params.put("name","name");
		params.put("curso", "curso");
		params.put("turno", "turno");
		
		// fetching the studante from the database
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(alunoDao.findAll());
		
		JasperReport jasperReport  = (JasperReport) JRLoader.loadObject(jasperStream);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=students.pdf");
		
		final ServletOutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);	
	}

public void getReportPdfById(HttpServletResponse response, Integer id) throws JRException, IOException {

	// compile report
	InputStream jasperStream =(InputStream) this.getClass().getResourceAsStream("/matricula.jasper");

	// addd params / adicionar parametros.
	Map<String, Object> params = new HashMap<>();
	params.put("id", id);
	params.put("name","name");
	params.put("matricula", "matricula");
	params.put("curso", "curso");
	params.put("turno", "turno");
	params.put("estado", "estado");
	
	// fetching the studante from the database
	final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(alunoDao.findAllById(id));
	
			
	JasperReport jasperReport  = (JasperReport) JRLoader.loadObject(jasperStream);
			
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);
	
	//Posibilitar o download
	//
	response.setContentType("application/x-pdf");
	response.setHeader("Content-disposition", "inline; filename=students.pdf");
			
	final ServletOutputStream outputStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);	

}
}