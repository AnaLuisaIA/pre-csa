package com.sadss.csa.service.impl;

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

import com.sadss.csa.dao.CalendarioDao;
import com.sadss.csa.modelo.entidad.BitacoraCalendario;
import com.sadss.csa.modelo.entidad.Calendario;
import com.sadss.csa.modelo.generic.IOperations;
import com.sadss.csa.service.BitacoraCalendarioService;
import com.sadss.csa.service.CalendarioService;
import com.sadss.csa.service.UsuarioService;
import com.sadss.csa.service.generic.AbstractService;
import com.sadss.csa.util.FillManager;
import com.sadss.csa.util.Layouter;
import com.sadss.csa.util.Writer;

@Service
public class CalendarioServiceImpl extends AbstractService<Calendario> implements CalendarioService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CalendarioDao dao;
	
	@Autowired
	private BitacoraCalendarioService bitCalendarioService;
	
	@Override
	public void validateBeforeCreate(Calendario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeUpdate(Calendario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateBeforeDelete(Calendario entity, BindingResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void descargarCalendarioXLSX(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("CALENDARIO");

		int inicioRowIndex = 0;
		int inicioColIndex = 0;	
		
		Layouter.buildReport(sheet, inicioRowIndex, inicioColIndex);
		
		FillManager.fillReport(sheet, inicioRowIndex, inicioColIndex);
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
		cal.setTime(new Date());
		
		String fileName = "Calendario_"+ String.valueOf(cal.get(Calendar.YEAR)) + 
				".xlsx";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		
		response.setContentType("application/vnd.ms-excel");
		
		Writer.write(response, sheet);
	}

	@Override
	protected IOperations<Calendario> getDao() {
		return dao;
	}

	@Override
	public List<Integer> obtenerAnios() {
		return dao.obtenerAnios();
	}

	@Override
	public List<Calendario> getCalendarioPorAnio(Integer anio) {
		return dao.getCalendarioPorAnio(anio);
	}

	@Override
	public void registrarAccionBitacora(String accion, Date fecha, String user) {
		
		BitacoraCalendario bc = new BitacoraCalendario();
		bc.setAccion(accion);
		bc.setFechaAccion(fecha);
		bc.setUsuario(usuarioService.findByUsername(user));
		
		bitCalendarioService.create(bc);
		
	}

	@Override
	public Integer getNumeroSemanasByMes(String mes) {
		return dao.getNumeroSemanasByMes(mes);
	}

}
