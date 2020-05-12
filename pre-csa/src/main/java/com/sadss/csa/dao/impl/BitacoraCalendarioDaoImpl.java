package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.BitacoraCalendarioDTO;
import com.sadss.csa.dao.BitacoraCalendarioDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.BitacoraCalendario;

@Repository
public class BitacoraCalendarioDaoImpl extends AbstractHibernateDao<BitacoraCalendario> implements BitacoraCalendarioDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BitacoraCalendarioDTO> getRegistros() {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.BitacoraCalendarioDTO(b.accion, b.fechaAccion, u.numColaborador, "
				+ "u.nombres, u.aPaterno, u.aMaterno) "
				+ "from BitacoraCalendario b "
				+ "join b.usuario u "
				+ "order by b.fechaAccion desc");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

}
