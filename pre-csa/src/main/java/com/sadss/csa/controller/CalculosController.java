package com.sadss.csa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sadss.csa.controller.beans.CalculosImssForm;
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.service.CalculoIMSSService;
import com.sadss.csa.service.DatosCargaService;
import com.sadss.csa.service.VariablesService;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.util.enums.TipoNominaIMSS;
import com.sadss.csa.util.enums.TipoPeriodo;

@Controller
@RequestMapping("/calculos")
public class CalculosController {

	private static final Logger LOG = LoggerFactory.getLogger(CalculosController.class);

	@Autowired
	private CalculoIMSSService imssService;

	@Autowired
	private DatosCargaService datosService;

	@Autowired
	private VariablesService variablesService;

	/**
	 * Vista principal de cálculos
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String indexCalculos(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = "/imss", method = RequestMethod.GET)
	public String calculoIMSS(ModelMap model) throws ParseException {
		model.addAttribute("calculo", new CalculosImssForm());
		feedDetalles(model);

		return "calculos/calculoIMSS";
	}

	@RequestMapping(value = "/isn", method = RequestMethod.GET)
	public String calculoISN(@RequestParam("nombreArchivo") String nombreA,
			@RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin,
			@RequestParam("periodo") String periodo, ModelMap model) {

		System.out.println(nombreA);
		System.out.println(fechaInicio);
		System.out.println(fechaFin);
		System.out.println(periodo);
		
		return "calculos/calculoISN";
	}

	/**
	 * Vista de Consulta de Cálculos IMSS / INFONAVIT
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultaImss", method = RequestMethod.GET)
	public String consultarCalculos(ModelMap model) {
		return "calculos/consultasIMSS";
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/calcularImss", method = RequestMethod.POST)
	public ModelAndView calcularIMSS(@Valid @ModelAttribute("calculo") CalculosImssForm cif, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra)
			throws IOException, ParseException {

		ModelMap model = new ModelMap();
		BigDecimal dias_labor = null;
		BigDecimal sd_base = null;
		BigDecimal salario_diario = null;
		int clave_agente = 0;

		if (result.hasErrors()) {
			model.addAttribute("calculo", cif);
			feedDetalles(model);
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("calculos/calculoIMSS", model);
		}

		// Verificación de existencia de variables
		List<VariablesDTO> variables = variablesService.getVariablesCalculo(cif.getFechaAplicacion(),
				cif.getFechaTermino());

		LinkedHashMap<String, BigDecimal> var = imssService.extraerVariables(variables);

		if (var.size() != 16) {

			model.addAttribute("calculo", cif);
			model.put("errmsg", "<strong>No se cuentan con todas las variables para realizar el cálculo. "
					+ "Se tiene que verificar si están dadas de alta en la base de datos y si se encuentran habilitadas. </strong>");
			feedDetalles(model);
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("calculos/calculoIMSS", model);

		}

		// Obtener información del archivo de carga y guardar en base de datos
		InputStream file = cif.getArchivo().getInputStream();

		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		List<CalculoIMSS> calculos = new ArrayList<CalculoIMSS>();
		String colaborador = SecurityUtils.getCurrentUser();

		for (int r = 1; r < sheet.getPhysicalNumberOfRows(); r++) {

			Row row = sheet.getRow(r);
			DatosCarga datos = new DatosCarga();

			for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {

				Cell celda = row.getCell(col);

				switch (col) {
				case 0:
					clave_agente = (int) celda.getNumericCellValue();
					datos.setClaveAgente(clave_agente);
					break;
				case 1:
					if (celda != null) {
						datos.setFechaAlta(celda.getDateCellValue());
					}
					break;
				case 2:
					if (celda != null) {
						datos.setFechaBaja(celda.getDateCellValue());
					}
					break;
				case 3:
					dias_labor = new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
					datos.setDiasLaborados(dias_labor);
					break;
				case 4:
					salario_diario = new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
					datos.setSalarioDiario(salario_diario);
					break;
				case 5:
					sd_base = new BigDecimal(celda.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
					datos.setSdBase(sd_base);
					break;
				default:
					if (celda != null) {
						if (col > 5 && col < 16) {
							imssService.setValoresComplemento(datos, col, celda);
						}
						// Tipo Nómina Normal (Bonos WEB)
						else if (cif.getTipoNomina() == TipoNominaIMSS.NOR) {

						}
						// Tipo Nómina Finiquito (Bonos archivo)
						else if (cif.getTipoNomina() == TipoNominaIMSS.FIN) {
							imssService.setValoresBonos(datos, col, celda);
						}
					}
					break;
				}

			}

			datosService.create(datos);

			CalculoIMSS calculo = imssService.realizarCalculos(cif, datos, var, colaborador, dias_labor, salario_diario,
					sd_base, clave_agente);

			imssService.create(calculo);
			calculos.add(calculo);

		}

		// Crear archivo de salida
		imssService.generarArchivoCalculos(request, response, calculos, cif.getFechaInicio(), cif.getFechaFin(),
				colaborador);

		return null;
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/checkFile", method = RequestMethod.POST)
	public @ResponseBody Boolean revisarArchivo(@RequestParam("archivo") MultipartFile archivo) throws IOException {

		InputStream file = archivo.getInputStream();

		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		if (sheet.getSheetName().equalsIgnoreCase("DATOS")) {
			return true;
		} else {
			try {
				CellReference cellRef = new CellReference("A1");
				XSSFRow row = (XSSFRow) sheet.getRow(cellRef.getRow());
				XSSFCell cell = row.getCell(cellRef.getCol());

				if (cell.getStringCellValue().equalsIgnoreCase("Clave Agente")) {
					return true;
				}
			} catch (NullPointerException e) {
				return false;
			}
		}

		return false;
	}

	@RequestMapping(value = "/checkPeriodo", method = RequestMethod.POST)
	public @ResponseBody Boolean revisarPeriodo(@RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("periodo") String periodo) {

		TipoPeriodo periodoImss = null;

		for (TipoPeriodo t : TipoPeriodo.values()) {
			if (t.getValue().equalsIgnoreCase(periodo)) {
				periodoImss = t;
				break;
			}
		}

		return imssService.periodoExiste(fechaInicio, fechaFin, periodoImss);
	}

	@RequestMapping(value = "/recalcularPeriodo", method = RequestMethod.POST)
	public @ResponseBody Boolean recalcularPeriodo(@RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("periodo") String periodo) {

		TipoPeriodo periodoImss = null;

		for (TipoPeriodo t : TipoPeriodo.values()) {
			if (t.getValue().equalsIgnoreCase(periodo)) {
				periodoImss = t;
				break;
			}
		}

		List<CalculoIMSS> calculos = imssService.getRecalcular(fechaInicio, fechaFin, periodoImss);

		for (CalculoIMSS c : calculos) {
			DatosCarga dato = c.getDatos();
			imssService.delete(c);
			datosService.delete(dato);
		}

		return true;
	}

	@RequestMapping(value = "/layout", method = RequestMethod.GET)
	public void descargarLayout(HttpServletRequest request, HttpServletResponse response) {
		imssService.descargarXLSX(request, response);
	}

	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}

	public void feedDetalles(ModelMap model) throws ParseException {
		model.addAttribute("tipoPeriodo", TipoPeriodo.valuesMenu());
		model.addAttribute("tipoNomina", TipoNominaIMSS.valuesMenu());

		List<PeriodoVariable> periodos = variablesService.getPeriodos();
		model.addAttribute("periodos", periodos);
	}

}
