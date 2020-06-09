package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.BitacoraTasaDTO;
import com.sadss.csa.dao.BitacoraTasaDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.BitacoraTasas;

@Repository
public class BitacoraTasaDaoImpl extends AbstractHibernateDao<BitacoraTasas> implements BitacoraTasaDAO{
	
	public BitacoraTasaDaoImpl() {
		super();
		setClazz(BitacoraTasas.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BitacoraTasaDTO> getRegistros() {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.BitacoraTasaDTO(b.accion, b.fecha , u.numColaborador, "
				+ "u.nombres, u.aPaterno, u.aMaterno, b.justificacion) "
				+ "from com.sadss.csa.modelo.entidad.BitacoraTasas b "
				+ "join b.usuario u "
				+ "order by b.fecha desc");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

}
