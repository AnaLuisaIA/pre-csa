package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.TasaDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;

@Repository
public class TasaDaoImpl extends AbstractHibernateDao<TasaSobreNomina> implements TasaDAO{
	
	public TasaDaoImpl() {
		super();
		setClazz(TasaSobreNomina.class);
	}
	
	
	/**
	 * Metodo Listado de Tasa Sobre Nomina
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<TasaSobreNomina> findTasas() {
		StringBuilder queryTxt = new StringBuilder("from com.sadss.csa.modelo.entidad.TasaSobreNomina");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

	@Override
	public TasaSobreNomina updateTasa(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TasaSobreNomina getTasaByOficina(String oficina) {
		StringBuilder queryTxt = new StringBuilder("from TasaSobreNomina t "
				+ "where t.oficina like :oficina");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("oficina", MatchMode.ANYWHERE.toMatchString(oficina));
		
		return (TasaSobreNomina) query.list().get(0);
	}


	@Override
	public TasaSobreNomina findTasaByEstado(String estado) {
		StringBuilder queryTxt = new StringBuilder("from TasaSobreNomina t "
				+ "where t.estado like :e");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("e", MatchMode.ANYWHERE.toMatchString(estado));
		
		return (TasaSobreNomina) query.list().get(0);
	}

}
