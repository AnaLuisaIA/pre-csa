package com.sadss.csa.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.PeriodoVariableDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.PeriodoVariable;

@Repository
public class PeriodoVariableDAOImpl extends AbstractHibernateDao<PeriodoVariable> implements PeriodoVariableDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public PeriodoVariableDAOImpl() {
		super();
		setClazz(PeriodoVariable.class);
	}
	

}
