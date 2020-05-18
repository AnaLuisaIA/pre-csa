package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.VariableDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.Variable;


@Repository
public class VariableDaoImpl extends AbstractHibernateDao<Variable> implements VariableDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public VariableDaoImpl() {
		super();
		setClazz(Variable.class);
	}
	
	/*Metodo Lista de variables
	 * 
	 * */
	@Override
	public List<Variable> findVariables() {
		StringBuilder queryTxt = new StringBuilder("from Variables");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}

	/*
	 *Metodo Modificar Estado 
	 * */
	@Override
	public Variable updateVariable(int id) {
        /**String hql = "UPDATE com.sadss.csa.modelo.entidad.Variable SET estado = 0 WHERE id = :id";
        Query query =  getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
        
        query.list().get(0);**/
        
		return null;
		
		
	}
	
}

	
	

