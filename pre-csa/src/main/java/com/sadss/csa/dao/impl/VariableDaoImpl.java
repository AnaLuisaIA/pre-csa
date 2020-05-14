package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.VariableDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.Variable;

@Repository
public class VariableDaoImpl extends AbstractHibernateDao<Variable> implements VariableDAO{
	public VariableDaoImpl() {
		super();
		setClazz(Variable.class);
	}
	
	@Override
	public List<Variable> findVariables() {
		StringBuilder queryTxt = new StringBuilder("from Variables");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

}
