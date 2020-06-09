package com.sadss.csa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.apache.xmlbeans.impl.regex.REUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sadss.csa.controller.beans.CalculosImssDTO;
//import com.sadss.csa.controller.beans.CalculoIsnForm;
import com.sadss.csa.controller.beans.CalculosImssForm;
import com.sadss.csa.controller.beans.TotalAPagar;
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.modelo.entidad.Calendario;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.service.CalculoIMSSService;
import com.sadss.csa.service.CalculoIsnService;
import com.sadss.csa.service.CalendarioService;
import com.sadss.csa.service.DatosCargaService;
import com.sadss.csa.service.TasaService;
import com.sadss.csa.service.VariablesService;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.util.enums.TipoNominaIMSS;
import com.sadss.csa.util.enums.TipoPeriodo;
import com.sadss.csa.util.enums.TipoVariableTasa;

@Controller
@RequestMapping("calculos")
public class CalculosController {

	private static final Logger LOG = LoggerFactory.getLogger(CalculosController.class);

	@Autowired
	private CalculoIMSSService imssService;

	@Autowired
	private CalendarioService calendarioService;

	@Autowired
	private DatosCargaService datosService;

	@Autowired
	private VariablesService variablesService;
	
	@Autowired
	private TasaService tasaService;
	
	@Autowired
	private CalculoIsnService isnService;

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
		feedDetallesIMSS(model);

		return "calculos/calculoIMSS";
	}

	@RequestMapping(value = "/isn", method = RequestMethod.GET)
	public String calculoISN(@RequestParam("nombreArchivo") String nombreA,
			@RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin,
			@RequestParam("periodo") String periodo, ModelMap model) {

		//CalculoIsnForm isnForm = new CalculoIsnForm();

		TipoPeriodo periodoImss = getPeriodoByString(periodo);

		//isnForm.setTipoPeriodo(periodoImss);
		//isnForm.setFechaInicio(fechaInicio);
		//isnForm.setFechaFin(fechaFin);
		isnForm.setNombreArchivo(nombreA);

		feedDetallesISN(model, isnForm);

		model.addAttribute("anios", calendarioService.obtenerAnios());
		
		return "calculos/calculoISN";
	}
	
	
	/*
	 * Método para extraer los Colaboradores que han realizado un proceso de calculo
	 * **/
	private void agregarColaborador(ModelMap model) {
		List<CalculoIMSS> calculo = imssService.getUsuarios();
		model.put("usuario", calculo);
	}
	
	/*
	 * Método para extraer las fecha de calculo 
	 * **/
	private void agregarFechaCalculo(ModelMap model) {
		List<CalculoIMSS> fecha = imssService.getFechaCalculo();
		model.put("fecha", fecha);
	}
	
	/**
	 * Vista de Consulta de Cálculos IMSS / INFONAVIT
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultaImss", method = RequestMethod.GET)
	public String consultarCalculos(ModelMap model, HttpServletRequest request) {
		List<CalculoIMSS> registros = imssService.getAllCalculo();
		model.put("acciones", registros);
		agregarColaborador(model);
		agregarFechaCalculo(model);
		model.put("calculoIMSS", new CalculoIMSS());
		return "calculos/consultasIMSS";
	}
	
	/*
	 * Método de busqueda de registros de acuerdo a los filtros
	 * */
	public void fillLists(ModelMap model, CalculoIMSS ci) {
		if(ci != null) {
			List<CalculoIMSS> acciones = imssService.getCalculoIMSSPorBusqueda(ci);
			model.put("acciones", acciones);
		}
	}
	
	/*
	 * Método de busqueda de acuerdo a los filtros de fechas
	 * */
	@RequestMapping(value = "/buscarCalculoImss", method = RequestMethod.POST)
	public ModelAndView busquedaCalculoImss(@Valid @ModelAttribute("calculoIMSS") CalculoIMSS ci, BindingResult result, HttpServletRequest request, RedirectAttributes ra) {
		ModelMap model = new ModelMap();
		fillLists(model, ci);
		agregarColaborador(model);
		agregarFechaCalculo(model);
		model.put("calculoIMSS", ci);
		return new ModelAndView("calculos/consultasIMSS",model);
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
			feedDetallesIMSS(model);
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
			feedDetallesIMSS(model);
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

	@RequestMapping(value = "/calcularIsn", method = RequestMethod.POST)
	public ModelAndView calcularISN(@Valid @ModelAttribute("isnForm") CalculoIsnForm cif, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) {

		ModelMap model = new ModelMap();

		if (result.hasErrors()) {
			feedDetallesISN(model, cif);
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("calculos/calculoISN", model);
		}

		List<DatosCarga> datos = datosService.findDatosByPeriodo(cif.getFechaInicio(), cif.getFechaFin(),
				cif.getTipoPeriodo());

		BigDecimal total_percepciones = new BigDecimal(0);
		BigDecimal base_gravable = new BigDecimal(0);

		for (DatosCarga d : datos) {
			total_percepciones = d.getSueldo().add(d.getAguinaldo()).add(d.getVacaciones()).add(d.getPrimaVacacional())
					.add(d.getRepUtil()).add(d.getBono()).add(d.getBonoDigital()).add(d.getBonoLealtad())
					.add(d.getBonoLealtad()).add(d.getBonoTraslado()).setScale(2, RoundingMode.HALF_UP);

			BigDecimal sumaReparto = d.getRepUtil().add(d.getIndemnizacion()).add(d.getVeinteDias());

			base_gravable = total_percepciones.subtract(sumaReparto);

			// Obtener localidad de agente
			BigDecimal tasa;

			// Obtener tasa de la localidad
		}

		return new ModelAndView("redirect:/calculos/");
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

		TipoPeriodo periodoImss = getPeriodoByString(periodo);

		return imssService.periodoExiste(fechaInicio, fechaFin, periodoImss);
	}

	@RequestMapping(value = "/recalcularPeriodo", method = RequestMethod.POST)
	public @ResponseBody Boolean recalcularPeriodo(@RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("periodo") String periodo) {

		TipoPeriodo periodoImss = getPeriodoByString(periodo);

		List<CalculoIMSS> calculos = imssService.getRecalcular(fechaInicio, fechaFin, periodoImss);

		for (CalculoIMSS c : calculos) {
			DatosCarga dato = c.getDatos();
			imssService.delete(c);
			datosService.delete(dato);
		}

		return true;
	}

	@RequestMapping(value = "/getSemanas", method = RequestMethod.GET)
	public @ResponseBody List<Calendario> getSemanas(@RequestParam Integer anio) {
		return calendarioService.getCalendarioPorAnio(anio);
	}
	
	@RequestMapping(value = "/getTAP", method = RequestMethod.GET)
	public @ResponseBody List<TasaSobreNomina> getTAP(@RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("periodo") String periodo){
		
		List<TasaSobreNomina> tasasTAP = new ArrayList<TasaSobreNomina>();
		
		TipoPeriodo periodoImss = getPeriodoByString(periodo);
		List<DatosCarga> datos = datosService.findDatosByPeriodo(fechaInicio, fechaFin, periodoImss);
		
		String oficina = "VER";
		TasaSobreNomina tasa = tasaService.getTasaByOficina(oficina);
		
		String oficina2 = "QRO";
		TasaSobreNomina tasa2 = tasaService.getTasaByOficina(oficina2);
		
		//Si la Tasa es Total a Pagar y está habilitada
		if(tasa.getTipoVariable().equals(TipoVariableTasa.TAP) && tasa.getEstatus()) {
			tasasTAP.add(tasa);
		}
		
		if(tasa2.getTipoVariable().equals(TipoVariableTasa.TAP) && tasa2.getEstatus()) {
			tasasTAP.add(tasa2);
		}
		
		/*for(DatosCarga d: datos) {
			//Obtener localidad del agente
			
			//Si la localidad es total a pagar y está habilitada
			
		}*/
		
		return tasasTAP;
	}
	
	@RequestMapping(value = "/registrarTAP", method = RequestMethod.POST)
	public @ResponseBody Boolean registrarTAP(@RequestBody List<TotalAPagar> tap, HttpServletRequest request) {
		
		for(TotalAPagar t: tap) {
			TasaSobreNomina tasa = tasaService.findTasaByEstado(t.getEstado());
			tasa.setTotalAPagar(t.getValor().setScale(2, BigDecimal.ROUND_HALF_UP));
			tasaService.update(tasa);
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

	public void feedDetallesIMSS(ModelMap model) throws ParseException {
		model.addAttribute("tipoPeriodo", TipoPeriodo.valuesMenu());
		model.addAttribute("tipoNomina", TipoNominaIMSS.valuesMenu());

		List<PeriodoVariable> periodos = variablesService.getPeriodos();
		model.addAttribute("periodos", periodos);
	}

	public void feedDetallesISN(ModelMap model, CalculoIsnForm cif) {
		model.addAttribute("isnForm", cif);
		model.addAttribute("anios", calendarioService.obtenerAnios());
	}
	
	public TipoPeriodo getPeriodoByString(String periodo) {
		
		for (TipoPeriodo t : TipoPeriodo.values()) {
			if (t.getValue().equalsIgnoreCase(periodo)) {
				return t;
			}
		}
		
		return null;
	}

}
