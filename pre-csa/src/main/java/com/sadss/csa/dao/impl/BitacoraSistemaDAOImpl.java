package com.sadss.csa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.BitacoraSistemaDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.Bitacora;

@Repository
public class BitacoraSistemaDAOImpl  extends AbstractHibernateDao<Bitacora> implements BitacoraSistemaDAO{

	public BitacoraSistemaDAOImpl() {
		super();
		setClazz(Bitacora.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bitacora> getAllBitacoras() {
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.BitacoraSistemaDTO(u.numColaborador,b.accion, b.fecha) ");
		queryTxt.append("from com.sadss.csa.modelo.entidad.Bitacora b join b.usuario u ");
		queryTxt.append("order by b.fecha desc");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

	private String getFiltros(Bitacora b, Map<String, Object> params) {
		String query = "";
		
		if(b.getFechaInicio() != null) {
			if(query.isEmpty()) query += " where "; else query += " and ";
			
			query += " (b.fecha >= :f1 and b.fecha <= :f2)";
			params.put("f1", b.getFechaInicio());
			params.put("f2", b.getFechaFin());
		}
		
		return query;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bitacora> getBitacorasPorBusqueda(Bitacora b) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.BitacoraSistemaDTO(u.numColaborador,b.accion, b.fecha) ");
		queryTxt.append("from com.sadss.csa.modelo.entidad.Bitacora b join b.usuario u");
		
		String queryFiltros = getFiltros(b, params);
		queryTxt.append(queryFiltros);
		queryTxt.append(" order by b.fecha desc");
		
		Query queryFinal = getCurrentSession().createQuery(queryTxt.toString());
		for(String p : queryFinal.getNamedParameters()) {
			queryFinal.setParameter(p, params.get(p));
		}
		
		return queryFinal.list();
	}

}
