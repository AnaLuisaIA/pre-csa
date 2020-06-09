package com.sadss.csa.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sadss.csa.controller.beans.CalculosImssForm;
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.dao.CalculoIMSSDao;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.CalculoIMSSService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.generic.AbstractService;
import com.sadss.csa.util.FillManager;
import com.sadss.csa.util.Layouter;
import com.sadss.csa.util.Writer;
import com.sadss.csa.util.enums.TipoPeriodo;
import com.sadss.csa.util.enums.TipoVariable;

@Service
public class CalculoIMSSServiceImpl extends AbstractService<CalculoIMSS> implements CalculoIMSSService {

	@Autowired
	private CalculoIMSSDao dao;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void validateBeforeCreate(CalculoIMSS entity, BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateBeforeUpdate(CalculoIMSS entity, BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateBeforeDelete(CalculoIMSS entity, BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean periodoExiste(Date fechaInicio, Date fechaFin, TipoPeriodo periodo) {
		return this.dao.periodoExiste(fechaInicio, fechaFin, periodo);
	}

	@Override
	protected IOperations<CalculoIMSS> getDao() {
		return this.dao;
	}

	@Override
	public void descargarXLSX(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("DATOS");

		int inicioRowIndex = 0;
		int inicioColIndex = 0;

		Layouter.buildPlantillaCarga(sheet, inicioRowIndex, inicioColIndex);

		FillManager.fillPlantillaCarga(sheet, inicioRowIndex, inicioColIndex);

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
		cal.setTime(new Date());

		String fileName = "DatosCalculo_" + String.valueOf(cal.get(Calendar.YEAR))
				+ String.valueOf(cal.get(Calendar.MONTH) + 1) + String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
				+ ".xlsx";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);

		response.setContentType("application/vnd.ms-excel");

		Writer.write(response, sheet);

	}

	@Override
	public void setValoresComplemento(DatosCarga datos, int col, Cell celda) {
		switch (col) {
		case 6:
			datos.setSueldo((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 7:
			datos.setAguinaldo((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 8:
			datos.setVacaciones((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 9:
			datos.setPrimaVacacional((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 10:
			datos.setRepUtil((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 11:
			datos.setIndemnizacion((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 12:
			datos.setVeinteDias((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 13:
			datos.setPrimaAnti((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 14:
			datos.setCompensacionV((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 15:
			datos.setPremios((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		default:
			break;
		}

	}

	@Override
	public void setValoresBonos(DatosCarga datos, int col, Cell celda) {
		switch (col) {
		case 16:
			datos.setBono((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 17:
			datos.setBonoLealtad((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 18:
			datos.setBonoDigital((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 19:
			datos.setBonoTraslado((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 20:
			datos.setOtroBono1((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 21:
			datos.setOtroBono2((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 22:
			datos.setOtroBono3((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 23:
			datos.setOtroBono4((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 24:
			datos.setOtroBono5((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 25:
			datos.setOtroBono6((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 26:
			datos.setOtroBono7((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 27:
			datos.setOtroBono8((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 28:
			datos.setOtroBono9((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		case 29:
			datos.setOtroBono10((celda == null) ? new BigDecimal("0.00")
					: new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
			break;
		default:
			break;
		}

	}

	@Override
	public LinkedHashMap<String, BigDecimal> extraerVariables(List<VariablesDTO> variables) {
		LinkedHashMap<String, BigDecimal> var = new LinkedHashMap<String, BigDecimal>();

		for (VariablesDTO v : variables) {

			if (StringUtils.containsIgnoreCase(v.getNombre(), "UMA")) {
				var.put("UMA", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "cuota")) {
				var.put("CTA", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "exceso")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "excedente"))
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("E3P", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "préstamo")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "prestaciones"))
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("PDP", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "gastos")
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("GMP", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "RT")) {
				var.put("RT", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "guard")
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("GUP", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "inv")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "vida"))
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("IVP", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "exceso")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "excedente"))
					&& v.getTipo().equals(TipoVariable.TRABAJADOR)) {
				var.put("E3T", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "préstamo")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "prestaciones"))
					&& v.getTipo().equals(TipoVariable.TRABAJADOR)) {
				var.put("PDT", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "inv")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "vida"))
					&& v.getTipo().equals(TipoVariable.TRABAJADOR)) {
				var.put("IVT", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "c.")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "C y V")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "C. y V.")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "CyV"))
					&& v.getTipo().equals(TipoVariable.TRABAJADOR)) {
				var.put("CVT", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "gastos")
					&& v.getTipo().equals(TipoVariable.TRABAJADOR)) {
				var.put("GMT", v.getValor());
			} else if ((StringUtils.containsIgnoreCase(v.getNombre(), "c.")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "C y V")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "C. y V.")
					|| StringUtils.containsIgnoreCase(v.getNombre(), "CyV"))
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("CVP", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "SAR")
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("SAR", v.getValor());
			} else if (StringUtils.containsIgnoreCase(v.getNombre(), "INFONAVIT")
					&& v.getTipo().equals(TipoVariable.PATRON)) {
				var.put("INF", v.getValor());
			}

		}

		return var;

	}

	@Override
	public CalculoIMSS realizarCalculos(CalculosImssForm cif, DatosCarga datos, LinkedHashMap<String, BigDecimal> var,
			String colaborador, BigDecimal dias, BigDecimal salario_diario, BigDecimal sdb, int clave) {

		// IMSS PATRONAL
		BigDecimal baseCotizacion = sdb.multiply(dias);
		BigDecimal minimo_integrado = salario_diario.multiply(new BigDecimal(1.0452));

		// Cuota Fija
		BigDecimal cuota = dias.multiply((var.get("UMA").multiply(var.get("CTA")))).setScale(2, RoundingMode.HALF_UP);

		// Excedente 3UMA
		BigDecimal excedente;
		if (sdb.compareTo(var.get("UMA").multiply(new BigDecimal(3))) > 0) {
			excedente = dias.multiply(var.get("E3P"))
					.multiply(sdb.subtract(var.get("UMA").multiply(new BigDecimal(3))));
		} else {
			excedente = new BigDecimal(0);
		}

		// Prestaciones en Dinero
		BigDecimal prestaciones = baseCotizacion.multiply(var.get("PDP"));

		// Gastos Médicos
		BigDecimal gastos = baseCotizacion.multiply(var.get("GMP"));

		// RT
		BigDecimal rt = baseCotizacion.multiply(var.get("RT"));

		// Guardería
		BigDecimal guarderia = baseCotizacion.multiply(var.get("GUP"));

		// Invalidez y Vida
		BigDecimal iyv = baseCotizacion.multiply(var.get("IVP"));

		// Total Patrón
		BigDecimal totalP = cuota.add(excedente).add(prestaciones).add(gastos).add(rt).add(guarderia).add(iyv);

		// IMSS TRABAJADOR
		// Excedente 3UMA
		BigDecimal excedenteT;
		if (sdb.compareTo(var.get("UMA").multiply(new BigDecimal(3))) > 0) {
			excedenteT = dias.multiply(var.get("E3T"))
					.multiply(sdb.subtract(var.get("UMA").multiply(new BigDecimal(3))));
		} else {
			excedenteT = new BigDecimal(0);
		}

		// Prestaciones
		BigDecimal prestacionesT = baseCotizacion.multiply(var.get("PDT"));

		// Gastos
		BigDecimal gastosT = baseCotizacion.multiply(var.get("GMT"));

		// Invalidez y Vida
		BigDecimal iyvT = baseCotizacion.multiply(var.get("IVT"));

		// Total Trabajador
		BigDecimal totalT;
		if (sdb.compareTo(minimo_integrado) <= 0) {
			totalT = new BigDecimal(0);
			totalP = totalP.add(excedenteT.add(prestacionesT).add(gastosT).add(iyvT));
		} else {
			totalT = excedenteT.add(prestacionesT).add(gastosT).add(iyvT);
		}

		// INFONAVIT PATRONAL
		// C y V
		BigDecimal cyv = baseCotizacion.multiply(var.get("CVP"));

		/// SAR
		BigDecimal sar = baseCotizacion.multiply(var.get("SAR"));

		// INFONAVIT
		BigDecimal info = baseCotizacion.multiply(var.get("INF"));

		// INFONAVIT TRABAJADOR
		// C y V
		BigDecimal cyvT = baseCotizacion.multiply(var.get("CVT"));

		// PATRÓN / TRABAJADOR TOTAL IMSS
		BigDecimal imssP;
		BigDecimal imssT = new BigDecimal(0);
		BigDecimal infonavitP;
		BigDecimal infonavitT = new BigDecimal(0);
		BigDecimal imssTotal;

		if (sdb.compareTo(minimo_integrado) <= 0) {
			infonavitP = cyv.add(sar).add(info).add(cyvT);
			imssP = totalP.add(infonavitP);
			imssTotal = imssP;

		} else {
			infonavitP = cyv.add(sar).add(info);
			infonavitT = cyvT;
			imssP = totalP.add(infonavitP);
			imssT = totalT.add(infonavitT);
			imssTotal = imssP.add(imssT);
		}

		CalculoIMSS calculo = new CalculoIMSS();
		calculo.setClaveAgente(clave);
		calculo.setTipoPeriodo(cif.getTipoPeriodo());
		calculo.setTipoNomina(cif.getTipoNomina());

		calculo.setCuotaFijaP(cuota);
		calculo.setExcedenteP(excedente);
		calculo.setPrestacionesP(prestaciones);
		calculo.setGastosMedP(gastos);
		calculo.setRTP(rt);
		calculo.setGuarderiaP(guarderia);
		calculo.setInvVidaP(iyv);
		calculo.setTotalPatron(totalP);

		calculo.setExcedenteT(excedenteT);
		calculo.setPrestacionesT(prestacionesT);
		calculo.setInvVidaT(iyvT);
		calculo.setGastosMedicosT(gastosT);
		calculo.setTotalTrabajador(totalT);

		calculo.setTotalIMSS(imssTotal);
		calculo.setInfonavitPatron(infonavitP);
		calculo.setInfonavitTrabajador(infonavitT);
		calculo.setTotalInfonavit(infonavitP.add(infonavitT));

		calculo.setFechaCalculo(new Date());
		calculo.setPeriodoInicio(cif.getFechaInicio());
		calculo.setPeriodoFin(cif.getFechaFin());
		calculo.setUsuarioCalculo(usuarioService.findByUsername(colaborador));
		calculo.setDatos(datos);

		return calculo;

	}

	@SuppressWarnings("resource")
	@Override
	public void generarArchivoCalculos(HttpServletRequest request, HttpServletResponse response,
			List<CalculoIMSS> calculos, Date fechaInicio, Date fechaFin, String colaborador) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("CALCULOS IMSS");

		int inicioRowIndex = 0;
		int inicioColIndex = 0;

		Layouter.buildArchivoSalida(sheet, inicioRowIndex, inicioColIndex, fechaInicio, fechaFin, colaborador);
		FillManager.fillCalculosIMSS(sheet, inicioRowIndex, inicioColIndex, calculos);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
		calendar.setTime(new Date());

		String fileName = "CALCULOS_IMSS_" + String.valueOf(calendar.get(Calendar.YEAR)) + ""
				+ String.valueOf(calendar.get(Calendar.MONTH) + 1) + ""
				+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + ".xlsx";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);

		response.setContentType("application/vnd.ms-excel");

		Writer.write(response, sheet);

	}

	@Override
	public List<CalculoIMSS> getRecalcular(Date fechaInicio, Date fechaFin, TipoPeriodo periodo) {
		return this.dao.getRecalcular(fechaInicio, fechaFin, periodo);
	}

	@Override
	public List<CalculoIMSS> getAllCalculo() {
		return dao.getAllCalculo();
	}

	@Override
	public List<CalculoIMSS> getCalculoIMSSPorBusqueda(CalculoIMSS ci) {
		return dao.getCalculoIMSSPorBusqueda(ci);
	}

	@Override
	public List<CalculoIMSS> getUsuarios() {
		return dao.getUsuarios();
	}

	@Override
	public List<CalculoIMSS> getFechaCalculo() {
		return dao.getFechaCalculo();
	}

}
