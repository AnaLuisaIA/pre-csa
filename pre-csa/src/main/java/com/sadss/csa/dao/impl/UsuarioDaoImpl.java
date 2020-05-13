package com.sadss.csa.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sadss.csa.dao.UsuarioDao;
import com.sadss.csa.dao.generic.AbstractHibernateDao;
import com.sadss.csa.modelo.entidad.Usuario;

@Repository
public class UsuarioDaoImpl extends AbstractHibernateDao<Usuario> implements UsuarioDao {

    public UsuarioDaoImpl() {
        super();

        setClazz(Usuario.class);
    }
    
	@Override
	public Usuario findByUsername(String username) {
		StringBuilder queryTxt = new StringBuilder("from Usuario where numColaborador = :num");
		
		Query query = getCurrentSession().createQuery(queryTxt.toString());
		query.setParameter("num", username);
		
		Usuario perfil = (Usuario) query.uniqueResult();
	

		return perfil;
	}

}
