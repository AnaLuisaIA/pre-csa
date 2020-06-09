package com.sadss.csa.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.controller.beans.CalculoIsnForm;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.CalculoIsnService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.generic.AbstractService;
import com.sadss.csa.util.FillManager;
import com.sadss.csa.util.Layouter;
import com.sadss.csa.util.Writer;

@Service
public class CalculoIsnServiceImpl extends AbstractService<CalculoISN> implements CalculoIsnService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void validateBeforeCreate(CalculoISN entity, BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateBeforeUpdate(CalculoISN entity, BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateBeforeDelete(CalculoISN entity, BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public CalculoISN realizarCalculos(CalculoIsnForm cif, Integer n_semanas, DatosCarga d, String colaborador) {

		BigDecimal total_percepciones = new BigDecimal(0);
		BigDecimal base_gravable = new BigDecimal(0);

		total_percepciones = d.getSueldo().add(d.getAguinaldo()).add(d.getVacaciones()).add(d.getPrimaVacacional())
				.add(d.getRepUtil()).add(d.getBono()).add(d.getBonoLealtad()).add(d.getBonoDigital())
				.add(d.getBonoTraslado()).add(d.getOtroBono1()).add(d.getOtroBono2()).add(d.getOtroBono3())
				.add(d.getOtroBono4()).add(d.getOtroBono5()).add(d.getOtroBono6()).add(d.getOtroBono7())
				.add(d.getOtroBono8()).add(d.getOtroBono9()).add(d.getOtroBono10()).setScale(2, RoundingMode.HALF_UP);

		BigDecimal sumaReparto = d.getRepUtil().add(d.getIndemnizacion()).add(d.getVeinteDias());

		base_gravable = total_percepciones.subtract(sumaReparto);

		// Obtener localidad de agente

		// Obtener tasa de la localidad (si es Tasa o Total a Pagar)

		// Guardar cálculo en lista
		CalculoISN calculo = new CalculoISN();
		calculo.setClaveAgente(d.getClaveAgente());
		calculo.setFechaCalculo(new Date());
		calculo.setUsuarioCalculo(usuarioService.findByUsername(colaborador));
		
		calculo.setBaseGravable(base_gravable.intValue());
		
		return calculo;
	}

	@SuppressWarnings("resource")
	@Override
	public void generarArchivoCalculos(HttpServletRequest request, HttpServletResponse response,
			List<CalculoISN> calculos, Date fechaInicio, Date fechaFin, String colaborador) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("CALCULOS ISN");

		int inicioRowIndex = 0;
		int inicioColIndex = 0;

		Layouter.buildArchivoSalidaIsn(sheet, inicioRowIndex, inicioColIndex, fechaInicio, fechaFin, colaborador);
		FillManager.fillCalculosISN(sheet, inicioRowIndex, inicioColIndex, calculos);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
		calendar.setTime(new Date());

		String fileName = "CALCULOS_ISN_" + String.valueOf(calendar.get(Calendar.YEAR)) + ""
				+ String.valueOf(calendar.get(Calendar.MONTH) + 1) + ""
				+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + ".xlsx";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);

		response.setContentType("application/vnd.ms-excel");

		Writer.write(response, sheet);

	}

	@Override
	protected IOperations<CalculoISN> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
