package com.sadss.csa.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.CalculosImssDTO;
import com.sadss.csa.dao.CalculoIMSSDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.util.enums.TipoPeriodo;

@Repository
public class CalculoIMSSDaoImpl extends AbstractHibernateDao<CalculoIMSS> implements CalculoIMSSDao{

	public CalculoIMSSDaoImpl() {
		super();
		setClazz(CalculoIMSS.class);
	}
	
	@Override
	public Boolean periodoExiste(Date fechaInicio, Date fechaFin, TipoPeriodo periodo) {
		StringBuilder queryTxt = new StringBuilder("from CalculoIMSS "
				+ "where periodoInicio = :f1 and periodoFin = :f2 "
				+ "and tipoPeriodo = :p");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("f1", fechaInicio);
		query.setParameter("f2", fechaFin);
		query.setParameter("p", periodo);
		
		Boolean resultado = (query.list().isEmpty() ? false : true);
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoIMSS> getRecalcular(Date fechaInicio, Date fechaFin, TipoPeriodo periodo) {
		StringBuilder queryTxt = new StringBuilder("from CalculoIMSS "
				+ "where periodoInicio = :f1 and periodoFin = :f2 "
				+ "and tipoPeriodo = :p");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("f1", fechaInicio);
		query.setParameter("f2", fechaFin);
		query.setParameter("p", periodo);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoIMSS> getAllCalculo() {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalculosImssDTO(c.totalPatron, c.totalTrabajador, c.totalIMSS, "
				+ "c.infonavitPatron, c.infonavitTrabajador, c.totalInfonavit, c.fechaCalculo, c.periodoInicio, "
				+ "c.periodoFin, u.nombres, u.aPaterno, u.aMaterno) "
				+ "from CalculoIMSS c "
				+ "join c.usuarioCalculo u "
				+ "order by c.fechaCalculo desc");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

	private String getFiltros(CalculoIMSS ci, Map<String, Object> params) {
		String query = "";
		
		if(ci.getFechaInicio() != null) {
			if(query.isEmpty()) query += "where"; else query += "and";
			
			query += " c.periodoInicio >= :f1 ";
			params.put("f1", ci.getFechaInicio());
		}
		
		if(ci.getFechaFin() != null) {
			if(query.isEmpty()) query += "where"; else query += "and";
			
			query += " c.periodoFin <= :f2 ";
			params.put("f2", ci.getFechaFin());
		}
		
		if(ci.getNumColaborador() != "") {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += " u.numColaborador = :colab ";
			params.put("colab", ci.getNumColaborador());
		}
		
		if(ci.getFechaCalculo() != null) {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += " c.fechaCalculo = :f_calculo ";
			params.put("f_calculo", ci.getFechaCalculo());
		}
		
		return query;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculosImssDTO> getCalculoIMSSPorBusqueda(CalculoIMSS ci) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalculosImssDTO(c.id, c.totalPatron, c.totalTrabajador, c.totalIMSS, "
				+ "c.infonavitPatron, c.infonavitTrabajador, c.totalInfonavit, c.fechaCalculo, c.periodoInicio, c.periodoFin, "
				+ "u.nombres,u.aPaterno,u.aMaterno) from CalculoIMSS c "
				+ "join c.usuarioCalculo u ");
		
		String queryFiltros = getFiltros(ci, params);
		queryTxt.append(queryFiltros);
		queryTxt.append(" order by c.periodoInicio desc");
		
		Query queryFinal = getCurrentSession().createQuery(queryTxt.toString());
		
		for(String p : queryFinal.getNamedParameters()) {
			queryFinal.setParameter(p, params.get(p));
		}
		return queryFinal.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoIMSS> getUsuarios() {
		StringBuilder queryTxt = new StringBuilder("Select distinct new "
				+ "com.sadss.csa.controller.beans.CalculosImssDTO(u.numColaborador,u.nombres,u.aPaterno, u.aMaterno) "
				+ "from CalculoIMSS c "
				+ "join c.usuarioCalculo u ");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoIMSS> getFechaCalculo() {
		StringBuilder queryTxt = new StringBuilder("Select distinct new "
				+ "com.sadss.csa.controller.beans.CalculosImssDTO(c.fechaCalculo) "
				+ "from CalculoIMSS c "
				+ "join c.usuarioCalculo u ");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

	@Override
	public CalculosImssDTO consultarInfoCalculo(Integer id) {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalculosImssDTO(c.id, c.claveAgente, c.cuotaFijaP, c.excedenteP, c.prestacionesP, "
				+ "c.gastosMedP, c.RTP, c.guarderiaP, c.invVidaP, c.totalPatron, c.excedenteT, c.prestacionesT, c.gastosMedicosT, "
				+ "c.invVidaT, c.totalTrabajador, c.totalIMSS, c.infonavitPatron, c.infonavitTrabajador, c.totalInfonavit, "
				+ "c.fechaCalculo, c.periodoInicio, c.periodoFin, u.nombres, u.aPaterno, u.aMaterno, u.numColaborador) "
				+ "from CalculoIMSS c "
				+ "join c.usuarioCalculo u "
				+ "where c.id = :id");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("id", id);
		
		return (CalculosImssDTO) query.uniqueResult();
	}

}
