package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.BitacoraVariableDTO;
import com.sadss.csa.dao.BitacoraVariableDAO;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.BitacoraVariables;


@Repository
public class BitacoraVariableDaoImpl extends AbstractHibernateDao<BitacoraVariables> implements BitacoraVariableDAO{


	
	@SuppressWarnings("unchecked")
	@Override
	public List<BitacoraVariableDTO> getRegistros() {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.BitacoraVariableDTO(b.accion, b.fechaAccion , u.numColaborador, "
				+ "u.nombres, u.aPaterno, u.aMaterno, b.justificacion) "
				+ "from com.sadss.csa.modelo.entidad.BitacoraVariables b "
				+ "join b.usuario u "
				+ "order by b.fechaAccion desc");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

	
}
