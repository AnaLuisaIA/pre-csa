package com.sadss.csa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sadss.csa.controller.beans.CalculoIsnDTO;
import com.sadss.csa.controller.beans.CalculoIsnForm;
import com.sadss.csa.controller.beans.CalculosImssDTO;
import com.sadss.csa.controller.beans.CalculosImssForm;
import com.sadss.csa.controller.beans.TotalAPagar;
import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.controller.beans.generic.FechaEditor;
import com.sadss.csa.modelo.entidad.Bitacora;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.modelo.entidad.CalculoISN;
import com.sadss.csa.modelo.entidad.Calendario;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;
import com.sadss.csa.service.BitacoraSistemaService;
import com.sadss.csa.service.CalculoIMSSService;
import com.sadss.csa.service.CalculoIsnService;
import com.sadss.csa.service.CalendarioService;
import com.sadss.csa.service.DatosCargaService;
import com.sadss.csa.service.TasaService;
import com.sadss.csa.service.UsuarioService;
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

	@Autowired
	private BitacoraSistemaService bitacoraService;

	@Autowired
	private UsuarioService usuarioService;

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

	/**
	 * Vista de form wizard para Cálculos IMSS
	 * 
	 * @param model
	 * @return Vista de CalculoIMSS
	 * @throws ParseException
	 */
	@RequestMapping(value = "/imss", method = RequestMethod.GET)
	public String calculoIMSS(ModelMap model) throws ParseException {
		model.addAttribute("calculo", new CalculosImssForm());
		feedDetallesIMSS(model);

		return "calculos/calculoIMSS";
	}

	/**
	 * Vista de form wizard para Cálculos ISN
	 * 
	 * @param nombreA     Nombre del archivo
	 * @param fechaInicio Fecha de inicio de periodo de Datos cálculo
	 * @param fechaFin    Fecha final de periodo de Datos cálculo
	 * @param periodo     Periodo de datos cálculo
	 * @param model
	 * @return Vista de formulario de Cálculo ISN
	 */
	@RequestMapping(value = "/isn", method = RequestMethod.GET)
	public String calculoISN(@RequestParam("nombreArchivo") String nombreA,
			@RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin,
			@RequestParam("periodo") String periodo, ModelMap model) {

		CalculoIsnForm isnForm = new CalculoIsnForm();

		TipoPeriodo periodoImss = getPeriodoByString(periodo);

		isnForm.setTipoPeriodo(periodoImss);
		isnForm.setFechaInicio(fechaInicio);
		isnForm.setFechaFin(fechaFin);
		isnForm.setNombreArchivo(nombreA);

		feedDetallesISN(model, isnForm);

		model.addAttribute("anios", calendarioService.obtenerAnios());

		return "calculos/calculoISN";
	}

	/**
	 * Vista de Consulta de Cálculos IMSS / INFONAVIT
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultaImss", method = RequestMethod.GET)
	public String consultarCalculos(ModelMap model, HttpServletRequest request) {
		
		fillListsImss(model);
		model.put("calculoIMSS", new CalculoIMSS());
		model.put("botonExportar", false);
		
		return "calculos/consultasIMSS";
	}
	
	/**
	 * Filtrado de Cálculos IMSS
	 * @param ci Objeto Calculo IMSS
	 * @param result
	 * @param request
	 * @param ra
	 * @return Lista de Cálculos filtrados
	 */
	@RequestMapping(value = "/buscarCalculoImss", method = RequestMethod.POST)
	public ModelAndView busquedaCalculoImss(@Valid @ModelAttribute("calculoIMSS") CalculoIMSS ci, BindingResult result,
			HttpServletRequest request, RedirectAttributes ra) {
		
		ModelMap model = new ModelMap();
		
		filtrarCalculosImss(ci, model);
		fillListsImss(model);
		model.put("calculoIMSS", ci);
		model.put("botonExportar", true);
		
		return new ModelAndView("calculos/consultasIMSS", model);
	}
	
	/**
	 * Genera documento Excel de Cálculo IMSS
	 * @param id Identificador de Cálculo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportarImss", method = RequestMethod.GET)
	public void exportarCalculoImss(@RequestParam(required = true) Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		
		String username = SecurityUtils.getCurrentUser();
		CalculoIMSS cal = imssService.findOne(id);
		
		List<CalculoIMSS> calculos = new ArrayList<CalculoIMSS>();
		calculos.add(cal);
		
		imssService.generarArchivoCalculos(request, response, calculos, cal.getFechaInicio(), cal.getFechaFin(), username);
	}
	
	/**
	 * Genera documento Excel de Cálculos IMSS
	 * @param calculos_imss Lista de ID's de Cálculos
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportarCalculosImss", method = RequestMethod.GET)
	public void exportarCalculos(@RequestParam List<Integer> calculos_imss, HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<CalculoIMSS> calculosImss = new ArrayList<CalculoIMSS>();
		String colaborador = SecurityUtils.getCurrentUser();
		
		for(Integer calculo: calculos_imss) {
			calculosImss.add(imssService.findOne(calculo));
		}
		
		Date fechaInicio = calculosImss.get(0).getFechaInicio();
		Date fechaFin = calculosImss.get(0).getFechaFin();
		
		imssService.generarArchivoCalculos(request, response, calculosImss, fechaInicio, fechaFin, colaborador);
	}

	/**
	 * Vista de Consulta de Cálculos ISN
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/consultaISN", method = RequestMethod.GET)
	public String consultarCalculosISN(ModelMap model, HttpServletRequest request) {
		fillListsIsn(model);
		model.put("calculoISN", new CalculoISN());
		model.put("botonExportar", false);
		
		return "calculos/consultasISN";
	}

	 /** Filtrado de Cálculos ISN
	 * @param c Objeto Calculo IMSS
	 * @param result
	 * @param request
	 * @param ra
	 * @return Lista de Cálculos filtrados
	 */
	@RequestMapping(value = "/buscarCalculoISN", method = RequestMethod.POST)
	public ModelAndView busquedaCalculoISN(@Valid @ModelAttribute("calculoISN") CalculoISN c, BindingResult result,
			HttpServletRequest request, RedirectAttributes ra) {
		ModelMap model = new ModelMap();
		
		filtrarCalculosIsn(model, c);
		fillListsIsn(model);
		model.put("calculoISN", c);
		model.put("botonExportar", true);
		
		return new ModelAndView("calculos/consultasISN", model);
	}
	
	/**
	 * Genera documento Excel de Cálculo ISN
	 * @param id Identificador de Cálculo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportarIsn", method = RequestMethod.GET)
	public void exportarCalculoIsn(@RequestParam(required = true) Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		
		String username = SecurityUtils.getCurrentUser();
		CalculoISN cal = isnService.findOne(id);
		
		List<CalculoISN> calculos = new ArrayList<CalculoISN>();
		calculos.add(cal);
		
		isnService.generarArchivoCalculos(request, response, calculos, 
				cal.getCalendario().getFechaInicio(), cal.getCalendario().getFechaFin(), username);
	}
	
	/**
	 * Genera documento Excel de Cálculos ISN
	 * @param calculos_isn Lista de Cálculos ISN
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportarCalculosIsn", method = RequestMethod.GET)
	public void exportarCalculosIsn(@RequestParam List<Integer> calculos_isn, HttpServletRequest request, 
			HttpServletResponse response) {
		
		List<CalculoISN> calculosIsn = new ArrayList<CalculoISN>();
		String colaborador = SecurityUtils.getCurrentUser();
		
		for(Integer calculo: calculos_isn) {
			calculosIsn.add(isnService.findOne(calculo));
		}
		
		Date fechaInicio = calculosIsn.get(0).getCalendario().getFechaInicio();
		Date fechaFin = calculosIsn.get(0).getCalendario().getFechaFin();
		
		isnService.generarArchivoCalculos(request, response, calculosIsn, fechaInicio, fechaFin, colaborador);
	}

	/**
	 * Proceso de Cálculo IMSS
	 * 
	 * @param cif      CalculoImssForm
	 * @param result   Resultado del binding
	 * @param request
	 * @param response
	 * @param ra       Redirect Attributes
	 * @return Archivo Excel de Cálculos
	 * @throws IOException
	 * @throws ParseException
	 */
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

			for (int col = 0; col < 30; col++) {

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

		// Registro en bitácora
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Bitacora b = new Bitacora();
		b.setAccion("Cálculos IMSS del periodo " + df.format(cif.getFechaInicio()) + " al " + df.format(cif.getFechaFin()));
		b.setFecha(new Date());
		b.setUsuario(usuarioService.findByUsername(colaborador));
		bitacoraService.create(b);

		return null;
	}

	/**
	 * Proceso de cálculo ISN
	 * @param cif CalculoIsnForm
	 * @param result
	 * @param request
	 * @param response
	 * @param ra
	 * @return Árchivo de salida de cálculos
	 */
	@RequestMapping(value = "/calcularIsn", method = RequestMethod.POST)
	public ModelAndView calcularISN(@Valid @ModelAttribute("isnForm") CalculoIsnForm cif, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) {

		ModelMap model = new ModelMap();
		String colaborador = SecurityUtils.getCurrentUser();
		System.out.println(cif.toString());

		if (result.hasErrors()) {
			feedDetallesISN(model, cif);
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("calculos/calculoISN", model);
		}

		List<DatosCarga> datos = datosService.findDatosByPeriodo(cif.getFechaInicio(), cif.getFechaFin(),
				cif.getTipoPeriodo());

		List<CalculoISN> calculosIsn = new ArrayList<CalculoISN>();
		List<DatosCarga> datosTAP = new ArrayList<DatosCarga>();

		Calendario cal = calendarioService.findOne(cif.getSemanaCalendario());
		Integer n_semanas = calendarioService.getNumeroSemanasByMes(cal.getMes());

		BigDecimal suma_baseG = new BigDecimal(0);
		BigDecimal total_percepciones = new BigDecimal(0);
		BigDecimal base_gravable = new BigDecimal(0);

		for (DatosCarga d : datos) {

			total_percepciones = d.getSueldo().add(d.getAguinaldo()).add(d.getVacaciones()).add(d.getPrimaVacacional())
					.add(d.getRepUtil()).add(d.getBono()).add(d.getBonoLealtad()).add(d.getBonoDigital())
					.add(d.getBonoTraslado()).add(d.getOtroBono1()).add(d.getOtroBono2()).add(d.getOtroBono3())
					.add(d.getOtroBono4()).add(d.getOtroBono5()).add(d.getOtroBono6()).add(d.getOtroBono7())
					.add(d.getOtroBono8()).add(d.getOtroBono9()).add(d.getOtroBono10())
					.setScale(2, RoundingMode.HALF_UP);

			BigDecimal sumaReparto = d.getRepUtil().add(d.getIndemnizacion()).add(d.getVeinteDias());

			base_gravable = total_percepciones.subtract(sumaReparto);

			// Si la localidad del agente es Total A Pagar
			datosTAP.add(d);
			suma_baseG = suma_baseG.add(base_gravable);

			// Si el agente tiene localidad de Tasa
			// CalculoISN calculo = isnService.realizarCalculos(cif, n_semanas, d,
			// colaborador, base_gravable);
			// isnService.create(calculo);
			// calculosIsn.add(calculo);
		}

		// Si hay datos de agentes con localidad Total a Pagar
		if (!datosTAP.isEmpty()) {
			calculosIsn = isnService.realizarCalculosTAP(cif, n_semanas, datosTAP, colaborador, suma_baseG,
					calculosIsn);
		}

		// Generar archivo de salida con lista
		isnService.generarArchivoCalculos(request, response, calculosIsn, cal.getFechaInicio(), cal.getFechaFin(),
				colaborador);
		
		// Registro en bitácora
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Bitacora b = new Bitacora();
		b.setAccion("Cálculos ISN de la semana " + df.format(cal.getFechaInicio()) + " al " + df.format(cal.getFechaFin()));
		b.setFecha(new Date());
		b.setUsuario(usuarioService.findByUsername(colaborador));
		bitacoraService.create(b);

		return null;
	}

	/**
	 * Revisa el archivo de carga para cálculos
	 * 
	 * @param archivo Archivo de carga
	 * @return true - Archivo válido false - Archivo inválido
	 * @throws IOException
	 */
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

	/**
	 * Verifica si el periodo ya ha sido calculado
	 * 
	 * @param fechaInicio Fecha de inicio de cálculo IMSS
	 * @param fechaFin    Fecha final de cálculo IMSS
	 * @param periodo     Tipo Periodo
	 * @return true - El periodo ya existe false - EL periodo no ha sido calculado
	 */
	@RequestMapping(value = "/checkPeriodo", method = RequestMethod.POST)
	public @ResponseBody Boolean revisarPeriodo(@RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("periodo") String periodo) {

		TipoPeriodo periodoImss = getPeriodoByString(periodo);

		return imssService.periodoExiste(fechaInicio, fechaFin, periodoImss);
	}

	/**
	 * Recalcula el periodo indicado
	 * 
	 * @param fechaInicio Fecha de inicio de cálculo IMSS
	 * @param fechaFin    Fecha final de cálculo IMSS
	 * @param periodo     Tipo Periodo
	 * @return true - Se realizó el recálculo
	 */
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

	/**
	 * Obtiene las semanas de un calendario
	 * 
	 * @param anio Año del calendario
	 * @return Lista de semanas
	 */
	@RequestMapping(value = "/getSemanas", method = RequestMethod.GET)
	public @ResponseBody List<Calendario> getSemanas(@RequestParam Integer anio) {
		return calendarioService.getCalendarioPorAnio(anio);
	}

	/**
	 * Obtiene las tasas de tipo Total a Pagar
	 * 
	 * @param fechaInicio Fecha de inicio de cálculo IMSS
	 * @param fechaFin    Fecha final de cálculo IMSS
	 * @param periodo     Tipo Periodo
	 * @return Lista de Tasas sobre Nómina
	 */
	@RequestMapping(value = "/getTAP", method = RequestMethod.GET)
	public @ResponseBody List<TasaSobreNomina> getTAP(@RequestParam("fechaInicio") Date fechaInicio,
			@RequestParam("fechaFin") Date fechaFin, @RequestParam("periodo") String periodo) {

		List<TasaSobreNomina> tasasTAP = new ArrayList<TasaSobreNomina>();

		// TipoPeriodo periodoImss = getPeriodoByString(periodo);
		// List<DatosCarga> datos = datosService.findDatosByPeriodo(fechaInicio,
		// fechaFin, periodoImss);

		String oficina = "VER";
		TasaSobreNomina tasa = tasaService.getTasaByOficina(oficina);

		String oficina2 = "QRO";
		TasaSobreNomina tasa2 = tasaService.getTasaByOficina(oficina2);

		// Si la Tasa es Total a Pagar y está habilitada
		if (tasa.getTipoVariable().equals(TipoVariableTasa.TAP) && tasa.getEstatus()) {
			tasasTAP.add(tasa);
		}

		if (tasa2.getTipoVariable().equals(TipoVariableTasa.TAP) && tasa2.getEstatus()) {
			tasasTAP.add(tasa2);
		}

		/*
		 * for(DatosCarga d: datos) { //Obtener localidad del agente
		 * 
		 * //Si la localidad es total a pagar y está habilitada
		 * 
		 * }
		 */

		return tasasTAP;
	}

	/**
	 * Registra el monto de Total a Pagar de las tasas
	 * 
	 * @param tap     Objeto Total A Pagar
	 * @param request
	 * @return true - Se realizó la actualizaciónd de la tasa
	 */
	@RequestMapping(value = "/registrarTAP", method = RequestMethod.POST)
	public @ResponseBody Boolean registrarTAP(@RequestBody List<TotalAPagar> tap, HttpServletRequest request) {

		for (TotalAPagar t : tap) {
			TasaSobreNomina tasa = tasaService.findTasaByEstado(t.getEstado());
			tasa.setTotalAPagar(t.getValor().setScale(2, BigDecimal.ROUND_HALF_UP));
			tasaService.update(tasa);
		}

		return true;
	}
	
	/**
	 * Obtiene Cálculo IMSS único
	 * @param id Identificador de cálculo
	 * @return Cálculo IMSS
	 */
	@RequestMapping(value = "/getCalculo", method = RequestMethod.GET)
	public @ResponseBody CalculosImssDTO obtenerCalculo(@RequestParam("id") Integer id) {
		return imssService.consultarInfoCalculo(id);
	}
	
	/**
	 * Obtener Cálculo ISN único
	 * @param id Identificador de cálculo
	 * @return
	 */
	@RequestMapping(value = "/getCalculoIsn", method = RequestMethod.GET)
	public @ResponseBody CalculoIsnDTO obtenerCalculoIsn(@RequestParam("id") Integer id, 
			@RequestParam("mes") String mes) {
		return isnService.consultarInfoCalculo(id, mes);
	}

	/**
	 * Descarga del Layout del archivo de carga
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/layout", method = RequestMethod.GET)
	public void descargarLayout(HttpServletRequest request, HttpServletResponse response) {
		imssService.descargarXLSX(request, response);
	}

	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}

	/**
	 * Carga datos para formulario IMSS
	 * @param model
	 * @throws ParseException
	 */
	public void feedDetallesIMSS(ModelMap model) throws ParseException {
		model.addAttribute("tipoPeriodo", TipoPeriodo.valuesMenu());
		model.addAttribute("tipoNomina", TipoNominaIMSS.valuesMenu());

		List<PeriodoVariable> periodos = variablesService.getPeriodos();
		model.addAttribute("periodos", periodos);
	}

	/**
	 * Carga datos para formulario ISN
	 * @param model
	 * @param cif
	 */
	public void feedDetallesISN(ModelMap model, CalculoIsnForm cif) {
		model.addAttribute("isnForm", cif);
		model.addAttribute("anios", calendarioService.obtenerAnios());
	}

	/**
	 * Obtiene el ENUM del periodo
	 * @param periodo Código String del periodo
	 * @return ENUM TipoPeriodo
	 */
	public TipoPeriodo getPeriodoByString(String periodo) {

		for (TipoPeriodo t : TipoPeriodo.values()) {
			if (t.getValue().equalsIgnoreCase(periodo)) {
				return t;
			}
		}

		return null;
	}

	/**
	 * Llena listado de Cálculos IMSS de acuerdo a filtros
	 * @param ci Calculo IMSS
	 * @param model
	 */
	public void filtrarCalculosImss(CalculoIMSS ci, ModelMap model) {
		if (ci != null) {
			List<CalculosImssDTO> acciones = imssService.getCalculoIMSSPorBusqueda(ci);
			model.put("acciones", acciones);
			
			if(acciones.isEmpty()) {
				model.put("infomsg", "<strong>No se encontraron coincidencias con los criterios de búsqueda.</strong>");
			}
		}
	}
	
	/**
	 * Llena listados de vista Consulta IMSS
	 * @param model
	 */
	public void fillListsImss(ModelMap model) {
		
		List<CalculoIMSS> fecha = imssService.getFechaCalculo();
		model.put("fecha", fecha);
		
		List<CalculoIMSS> calculo = imssService.getUsuarios();
		model.put("usuario", calculo);
	}
	
	/**
	 * Llena listados de vista Consulta ISN
	 * @param model
	 * @param c
	 */
	public void fillListsIsn(ModelMap model) {
		List<CalculoISN> clave = isnService.getAllAgente();
		model.put("clave", clave);
		
		List<CalculoISN> fechaCalculo = isnService.getAllFechaCalculo();
		model.put("fechaCalculo", fechaCalculo);
		
		List<CalculoISN> usuario = isnService.getAllColaborador();
		model.put("usuario", usuario);
		
		List<Calendario> calendario = calendarioService.findAll();
		model.put("semanas", calendario);
		
	}
	
	/**
	 * Filtra búsqueda de Cálculos ISN
	 * @param model
	 * @param c
	 */
	public void filtrarCalculosIsn(ModelMap model, CalculoISN c) {
		if (c != null) {
			List<CalculoIsnDTO> acciones = isnService.getCalculosISNPorBusqueda(c);
			model.put("acciones", acciones);
		}
	}

}
