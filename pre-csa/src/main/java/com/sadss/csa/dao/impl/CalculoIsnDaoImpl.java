package com.sadss.csa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.CalculoIsnDTO;
import com.sadss.csa.dao.CalculoIsnDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.CalculoISN;

@Repository
public class CalculoIsnDaoImpl extends AbstractHibernateDao<CalculoISN> implements CalculoIsnDAO{

	public CalculoIsnDaoImpl() {
		super();
		setClazz(CalculoISN.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoISN> getAllCalculoISN() {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalculoIsnDTO(ci.claveAgente,c.fechaInicio,c.fechaFin,"
				+ "ci.localidad,ci.tasa,ci.baseGravable,ci.isnMensual,ci.isnSemanal,u.nombres,u.aPaterno,"
				+ "u.aMaterno,ci.fechaCalculo) from CalculoISN ci "
				+ "join ci.usuarioCalculo u "
				+ "join ci.calendario c "
				+ "order by ci.fechaCalculo desc");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}
	
	private String getFiltros(CalculoISN c,Map<String, Object> params) {
		String query = "";
		
		if(c.getIdSemana() != null) {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += "c.id = :idSemana";
			params.put("idSemana", c.getIdSemana());
		}
		
		if(c.getClaveAgente() != null) {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += "ci.claveAgente = :ca";
			params.put("ca", c.getClaveAgente());
		}
		
		if(c.getFechaCalculo() != null) {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += "ci.fechaCalculo = :fc";
			params.put("fc", c.getFechaCalculo());
		}
		
		if(c.getNumColaborador() != "") {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += "u.numColaborador = :colaborador";
			params.put("colaborador", c.getNumColaborador());
		}
		
		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalculoIsnDTO> getAllCalculoISNPorBusqueda(CalculoISN c) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalculoIsnDTO(ci.id,ci.claveAgente,c.fechaInicio,c.fechaFin,"
				+ "ci.localidad,ci.tasa,ci.baseGravable,ci.isnMensual,ci.isnSemanal,u.nombres,u.aPaterno,"
				+ "u.aMaterno,ci.fechaCalculo, c.mes) from CalculoISN ci "
				+ "join ci.usuarioCalculo u "
				+ "join ci.calendario c ");
		
		String queryFiltros = getFiltros(c, params);
		queryTxt.append(queryFiltros);
		queryTxt.append(" order by ci.fechaCalculo desc ");
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
	
	@Override
	public CalculoIsnDTO consultarInfoCalculo(Integer id, String mes) {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalculoIsnDTO(c.id, c.claveAgente, cal.fechaInicio, cal.fechaFin, "
				+ "c.localidad, c.tasa, c.baseGravable, c.isnMensual, c.isnSemanal, u.numColaborador, "
				+ "u.nombres, u.aPaterno, u.aMaterno, c.fechaCalculo, "
				+ "(Select count(c) from Calendario c where c.mes = :mes), cal.mes) from CalculoISN c "
				+ "join c.usuarioCalculo u "
				+ "join c.calendario cal "
				+ "where c.id = :id");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("id", id);
		query.setParameter("mes", mes);
		
		return (CalculoIsnDTO) query.uniqueResult();
	}

}
