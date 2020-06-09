package com.sadss.csa.service;

import java.util.Date;
import java.util.List;

import com.sadss.csa.controller.beans.generic.CrudService;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.util.enums.TipoPeriodo;

public interface DatosCargaService extends CrudService<DatosCarga>{

	public List<DatosCarga> findDatosByPeriodo(Date fechaInicio, Date fechaFin, TipoPeriodo periodo);
}
