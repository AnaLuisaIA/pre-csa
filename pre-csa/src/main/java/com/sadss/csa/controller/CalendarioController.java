package com.sadss.csa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import com.sadss.csa.controller.beans.BitacoraCalendarioDTO;
import com.sadss.csa.controller.beans.BusquedaCalendarioForm;
import com.sadss.csa.controller.beans.CalendarioForm;
import com.sadss.csa.modelo.entidad.Calendario;
import com.sadss.csa.service.BitacoraCalendarioService;
import com.sadss.csa.service.CalendarioService;
import com.sadss.csa.util.FtpUtil;
import com.sadss.csa.util.SecurityUtils;
import com.sadss.csa.controller.beans.generic.FechaEditor;

@Controller
@RequestMapping("calendario")
public class CalendarioController {

	private static final Logger LOG = LoggerFactory.getLogger(CalendarioController.class);
	
	@Autowired
	private CalendarioService calendarioService;
	
	@Autowired
	private BitacoraCalendarioService bcService;
	
	/**
	 * Vista principal de Catálogo de Calendario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String verCalendarios(ModelMap model) {

		feedDetalles(model);
		
		model.addAttribute("buscarCalendario", new BusquedaCalendarioForm());

		return "catalogo/calendario/calendario";
	}

	/**
	 * Búsqueda específica de Calendario
	 * @param bc BusquedaCalendarioForm
	 * @param result
	 * @param ra
	 * @return Vista filtrada de Calendario
	 */
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public ModelAndView buscarCalendario(@Valid @ModelAttribute("buscarCalendario") BusquedaCalendarioForm bc,
			BindingResult result, RedirectAttributes ra) {

		ModelMap model = new ModelMap();

		if (result.hasErrors()) {
			model.put("buscarCalendario", bc);
			feedDetalles(model);
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/calendario/calendario", model);
		}

		busquedaCalendario(model, bc.getFiltroAnio());
		feedDetalles(model);

		return new ModelAndView("catalogo/calendario/calendario", model);
	}

	/**
	 * Alta de Calendario
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public String agregarCalendario(Model model) {
		CalendarioForm cf = new CalendarioForm();
		model.addAttribute("calendario", cf);

		return "catalogo/calendario/alta_calendario";
	}

	/**
	 * Proceso de Alta de Calendario
	 * @param calendarioForm
	 * @param result
	 * @param ra
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addCalendario(@Valid @ModelAttribute("calendario") CalendarioForm calendarioForm,
			BindingResult result, RedirectAttributes ra) throws IOException, ParseException {

		ModelMap model = new ModelMap();
		
		if (result.hasErrors()) {
			model.put("calendario", calendarioForm);
			model.put("errmsg", "El archivo cargado tiene errores. Favor de verificar.");
			LOG.debug("Errores: " + result.getAllErrors());
			return new ModelAndView("catalogo/calendario/alta_calendario", model);
		}
		
		//SI EXISTE CALENDARIO, ELIMINAMOS REGISTROS
		if(!calendarioForm.isNuevo()) {
			try {
				calendarioService.deleteCalendarioPorAnio(calendarioForm.getAnio());
				FtpUtil.eliminarCalendario(calendarioForm.getFileName());
			} catch (ConstraintViolationException e) {
				model.put("errmsg", "El Calendario "+ calendarioForm.getAnio() +" ya está siendo utilizado en cálculos del sistema. "
						+ "No puede ser actualizado.");
				model.put("calendario", calendarioForm);
				return new ModelAndView("catalogo/calendario/alta_calendario", model);
			}

		}

		String colaborador  = SecurityUtils.getCurrentUser();
		
		InputStream file = calendarioForm.getArchivo().getInputStream();

		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(file);

		Sheet sheet = workbook.getSheetAt(0);

		for (int r = 2; r < sheet.getPhysicalNumberOfRows(); r++) {

			Row row = sheet.getRow(r);
			Calendario cal = new Calendario();

			for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {

				switch (col) {
				case 0:
					cal.setSemana((int) row.getCell(col).getNumericCellValue());
					break;
				case 1:
					cal.setFechaInicio(row.getCell(col).getDateCellValue());
					break;
				case 2:
					cal.setFechaFin(row.getCell(col).getDateCellValue());
					break;
				case 3:
					if(row.getCell(col).getStringCellValue().length() > 10) {
						ra.addFlashAttribute("errmsg", "El valor de mes de la fila: " 
								+ r + "excede los 10 caracteres permitidos" );
						return new ModelAndView("redirect:/calendario/agregar");
					} 
					cal.setMes(row.getCell(col).getStringCellValue().toUpperCase());
					break;
				case 4:
					cal.setTrimestre((int) row.getCell(col).getNumericCellValue());
					break;
				default:
					break;
				}

			}
			cal.setAnio(calendarioForm.getAnio());
			cal.setArchivo(calendarioForm.getFileName());

			calendarioService.create(cal);
		}
		
		calendarioService.cargarArchivo(calendarioForm.getFileName(), calendarioForm.getArchivo().getBytes());
		calendarioService.registrarAccionBitacora("Creación de Calendario " + calendarioForm.getAnio(), new Date(), colaborador );
		
		ra.addFlashAttribute("succmsg", "Los registros del calendario se ha registrado correctamente.");
		return new ModelAndView("redirect:/calendario/");
	}

	/**
	 * Descargar archivo Excel de layout de carga de Calendario
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/layout", method = RequestMethod.GET)
	public void descargarLayout(HttpServletRequest request, HttpServletResponse response) {
		calendarioService.descargarCalendarioXLSX(request, response);
	}
	
	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new FechaEditor(new SimpleDateFormat("dd/MM/yyyy")));
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/checkCalendario", method = RequestMethod.POST)
	public @ResponseBody Integer validarArchivo(@RequestParam("archivo") MultipartFile archivo, 
			@RequestParam("anio") Integer anio) throws IOException {
		
		List<Calendario> cal = calendarioService.getCalendarioPorAnio(anio);
		
		if(cal.isEmpty()) {
			
			InputStream file = archivo.getInputStream();

			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			
			if (sheet.getSheetName().equalsIgnoreCase("CALENDARIO")) {
				return 3;
			} else {
				
				try {
					CellReference cellRef = new CellReference("A2");
					XSSFRow row = (XSSFRow) sheet.getRow(cellRef.getRow());
					XSSFCell cell = row.getCell(cellRef.getCol());

					if (cell.getStringCellValue().equalsIgnoreCase("Semana")) {
						return 3;
					}
				} catch (NullPointerException e) {
					return 2;
				}
			}
			
		} else {
			return 1;
		}
			
		return 2;
	}

	/**
	 * Inserta datos en vista Calendario
	 * @param model
	 */
	public void feedDetalles(ModelMap model) {
		List<Integer> aniosCalendario = calendarioService.obtenerAnios();
		model.addAttribute("anios", aniosCalendario);
		
		List<BitacoraCalendarioDTO> bitacoraCalendario = bcService.getRegistros();
		model.put("acciones", bitacoraCalendario);
	}

	/**
	 * Búsqueda filtrada de Calendario
	 * @param model
	 * @param anio
	 */
	public void busquedaCalendario(ModelMap model, Integer anio) {
		List<Calendario> calendario = calendarioService.getCalendarioPorAnio(anio);
		model.put("fechas", calendario);
	}

}
