package com.sadss.csa.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.CalendarioDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.Calendario;

@Repository
public class CalendarioDaoImpl extends AbstractHibernateDao<Calendario> implements CalendarioDao {
	
	public CalendarioDaoImpl() {
		super();
		
		setClazz(Calendario.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> obtenerAnios() {
		StringBuilder queryTxt = new StringBuilder("Select anio from Calendario group by anio");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Calendario> getCalendarioPorAnio(Integer anio) {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.CalendarioDTO(c.id, c.fechaInicio, "
				+ "c.fechaFin, c.trimestre, c.anio, c.semana, c.mes) "
				+ "from Calendario c "
				+ "where c.anio = :anio");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("anio", anio);
		
		return query.list();
	}

	@Override
	public Integer getNumeroSemanasByMes(String mes) {
		StringBuilder queryTxt = new StringBuilder("Select count(c) "
				+ "from Calendario c "
				+ "where c.mes = :mes");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("mes", mes);
		
		return ((Long) query.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getSemanasIniciales() {
		StringBuilder queryTxt = new StringBuilder("Select fechaInicio "
				+ "from Calendario");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getSemanasFinales() {
		StringBuilder queryTxt = new StringBuilder("Select fechaFin "
				+ "from Calendario");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}
	
	
}
