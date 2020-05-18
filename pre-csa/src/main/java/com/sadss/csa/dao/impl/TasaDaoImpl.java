package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.TasaDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.TasaSobreNomina;

@Repository
public class TasaDaoImpl extends AbstractHibernateDao<TasaSobreNomina> implements TasaDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public TasaDaoImpl() {
		super();
		setClazz(TasaSobreNomina.class);
	}
	
	
	/**
	 * Metodo Listado de Tasa Sobre Nomina
	 * */
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

}
