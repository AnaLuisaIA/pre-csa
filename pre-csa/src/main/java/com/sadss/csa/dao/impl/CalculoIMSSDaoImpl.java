package com.sadss.csa.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.CalculoIMSSDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.CalculoIMSS;
import com.sadss.csa.util.enums.TipoPeriodo;

@Repository
public class CalculoIMSSDaoImpl extends AbstractHibernateDao<CalculoIMSS> implements CalculoIMSSDao{

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

}
