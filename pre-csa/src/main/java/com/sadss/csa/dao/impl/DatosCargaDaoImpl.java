package com.sadss.csa.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.DatosCargaDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.DatosCarga;
import com.sadss.csa.util.enums.TipoPeriodo;

@Repository
public class DatosCargaDaoImpl extends AbstractHibernateDao<DatosCarga> implements DatosCargaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<DatosCarga> findDatosByPeriodo(Date fechaInicio, Date fechaFin, TipoPeriodo periodo) {
		StringBuilder queryTxt = new StringBuilder("Select d from CalculoIMSS c "
				+ "join c.datos d "
				+ "where c.tipoPeriodo = :p "
				+ "and c.periodoInicio = :f1 "
				+ "and c.periodoFin = :f2");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("p", periodo);
		query.setParameter("f1", fechaInicio);
		query.setParameter("f2", fechaFin);
		
		return query.list();
	}

}
