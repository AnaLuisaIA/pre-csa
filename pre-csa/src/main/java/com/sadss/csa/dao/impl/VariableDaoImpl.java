package com.sadss.csa.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.VariablesDTO;
import com.sadss.csa.dao.VariableDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.PeriodoVariable;
import com.sadss.csa.modelo.entidad.Variable;


@Repository
public class VariableDaoImpl extends AbstractHibernateDao<Variable> implements VariableDAO{
	
	public VariableDaoImpl() {
		super();
		setClazz(Variable.class);
	}
	
	/*Metodo Lista de variables
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<Variable> findVariables() {
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.PeriodoVariablesDTO(P.id,V.id,V.nombre,V.descripcion,V.estado,P.valor,V.tipo,P.fechaAplicacion,P.fechaTermino) from com.sadss.csa.modelo.entidad.PeriodoVariable P JOIN P.variable V");
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


	@Override
	public PeriodoVariable findVariablesID(Integer id) {
		StringBuilder queryTxt = new StringBuilder("Select new com.sadss.csa.controller.beans.PeriodoVariablesDTO(P.id,V.id,P.valor,P.fechaAplicacion) from com.sadss.csa.modelo.entidad.PeriodoVariable P JOIN P.variable V WHERE V.id = :id");
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("id", id);
		
		return (PeriodoVariable) (query.list().isEmpty() ? null : query.list().get(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PeriodoVariable> getPeriodos() {
		StringBuilder queryTxt = new StringBuilder("from PeriodoVariable "
				+ "group by fechaAplicacion, fechaTermino");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VariablesDTO> getVariablesCalculo(Date fechaAplicacion, Date fechaTermino) {
		
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.VariablesDTO(v.id, v.nombre, pv.valor, v.tipo) "
				+ "from PeriodoVariable pv "
				+ "join pv.variable v "
				+ "where v.estado = :e "
				+ "and pv.fechaAplicacion >= :f1 ");
		
		if(fechaTermino != null) {
			queryTxt.append("and pv.fechaTermino <= :f2");
		}
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("e", true);
		query.setParameter("f1", fechaAplicacion);
		
		if(fechaTermino != null) {
			query.setParameter("f2", fechaTermino);
		}
		
		return query.list();
	}


	
}

	
	

