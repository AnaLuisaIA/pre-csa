package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.VariablesForm;
import com.sadss.csa.dao.VariableDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
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
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.PeriodoVariablesDTO(P.id,V.id,V.nombre,V.descripcion,V.estado,P.valor,V.tipo,P.fechaAplicacion,P.fechaTermino) from com.sadss.csa.modelo.entidad.PeriodoVariable P JOIN P.variable V");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		return query.list();
	}


	@Override
	public PeriodoVariable findVariablesID(Integer id) {
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.PeriodoVariablesDTO(P.id,V.id,P.valor,P.fechaAplicacion) from com.sadss.csa.modelo.entidad.PeriodoVariable P JOIN P.variable V WHERE V.id = :id");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("id", id);
		
		return (PeriodoVariable) (query.list().isEmpty() ? null : query.list().get(0));
	}

	@Override
	public Variable updateVariable(int id) {
		return null;
	}
	
}

	
	

