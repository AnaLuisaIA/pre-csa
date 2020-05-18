package com.sadss.csa.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.controller.beans.BitacoraUsuarioDTO;
import com.sadss.csa.dao.BitacoraUsuarioDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.BitacoraUsuario;

@Repository
public class BitacoraUsuarioDaoImpl extends AbstractHibernateDao<BitacoraUsuario> implements BitacoraUsuarioDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BitacoraUsuarioDTO> getRegistros() {
		StringBuilder queryTxt = new StringBuilder("Select new "
				+ "com.sadss.csa.controller.beans.BitacoraUsuarioDTO(b.accion, b.fechaAccion, u.numColaborador, "
				+ "u.nombres, u.aPaterno, u.aMaterno) "
				+ "from BitacoraUsuario b "
				+ "join b.usuario u "
				+ "order by b.fechaAccion desc");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		
		return query.list();
	}

}
