package com.simplecar.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.simplecar.models.Customer;
import com.simplecar.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@RequiredArgsConstructor
public class ReportServices {
	private final CustomerRepository customerRepository;
	
	public byte[] customerReport(String reportFormat) throws FileNotFoundException, JRException {
		List<Customer> customers = customerRepository.findAll();
		File file = ResourceUtils.getFile("classpath:reports\\customers.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("new", "bye");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
