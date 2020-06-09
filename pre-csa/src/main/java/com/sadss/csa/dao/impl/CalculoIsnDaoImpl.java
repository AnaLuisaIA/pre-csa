package com.sadss.csa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.calculoIsnDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.CalculoISN;

@Repository
public class CalculoIsnDaoImpl extends AbstractHibernateDao<CalculoISN> implements calculoIsnDAO{

	public CalculoIsnDaoImpl() {
		super();
		setClazz(CalculoISN.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoISN> getAllCalculoISN() {
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.CalculoIsnDTO(ci.claveAgente,c.fechaInicio,c.fechaFin,ci.localidad,ci.tasa,ci.baseGravable,ci.isnMensual,ci.isnSemanal,u.nombres,u.aPaterno,u.aMaterno,ci.fechaCalculo) from com.sadss.csa.modelo.entidad.CalculoISN ci join ci.usuarioCalculo u join ci.calendario c order by ci.fechaCalculo desc");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}
	
	private String getFiltros(CalculoISN c,Map<String, Object> params) {
		String query = "";
		if(c.getFechaI() != null) {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += "(c.fechaInicio = :f1 and c.fechaFin = :f2 and ci.claveAgente = :claveAgente and ci.fechaCalculo = :fechaCalculo and u.numColaborador = :numColaborador) ";
			params.put("f1", c.getFechaI());
			params.put("f2", c.getFechaF());
			params.put("claveAgente", c.getClaveAgente());
			params.put("fechaCalculo", c.getFechaCalculo());
			params.put("numColaborador", c.getNumColaborador());
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoISN> getAllCalculoISNPorBusqueda(CalculoISN c) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.CalculoIsnDTO(ci.claveAgente,c.fechaInicio,c.fechaFin,ci.localidad,ci.tasa,ci.baseGravable,ci.isnMensual,ci.isnSemanal,u.nombres,u.aPaterno,u.aMaterno,ci.fechaCalculo) from com.sadss.csa.modelo.entidad.CalculoISN ci join ci.usuarioCalculo u join ci.calendario c ");
		String queryFiltros = getFiltros(c, params);
		queryTxt.append(queryFiltros);
		queryTxt.append(" order by ci.fechaCalculo desc");
		Query queryFinal = getCurrentSession().createQuery(queryTxt.toString());
		
		for(String p : queryFinal.getNamedParameters()) {
			queryFinal.setParameter(p, params.get(p));
		}
		return queryFinal.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoISN> getAllAgente() {
		StringBuilder queryTxt = new StringBuilder("Select distinct new com.sadss.csa.controller.beans.CalculoIsnDTO(ci.claveAgente) from com.sadss.csa.modelo.entidad.CalculoISN ci ");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoISN> getAllFechaCalculo() {
		StringBuilder queryTxt = new StringBuilder("Select distinct new com.sadss.csa.controller.beans.CalculoIsnDTO(ci.fechaCalculo) from com.sadss.csa.modelo.entidad.CalculoISN ci ");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoISN> getAllColaborador() {
		StringBuilder queryTxt = new StringBuilder("Select distinct new com.sadss.csa.controller.beans.CalculoIsnDTO(u.numColaborador,u.nombres,u.aPaterno, u.aMaterno) from com.sadss.csa.modelo.entidad.CalculoISN ci join ci.usuarioCalculo u ");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

}
